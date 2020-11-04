package com.hzz.hzzcloud.common.el;

import net.fxft.common.el.CamelCaseUtil;
import net.fxft.common.el.StringConvert;
import net.fxft.common.entity.Result;
import net.fxft.common.util.LinkedIgnoreCaseMap;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/3 9:55
 */

public class ClassGetSetMethodCache {
    private Class cls;
    private LinkedIgnoreCaseMap<AttrMethods> attrMethedsMap = new LinkedIgnoreCaseMap();

    ClassGetSetMethodCache(Class cls) {
        this.cls = cls;
        this.loadAttrMethods(cls);
    }

    public Collection<String> getAllAttr() {
        return this.attrMethedsMap.keySet();
    }

    public List<AttrMethods> getAllAttrMethods() {
        return new ArrayList(this.attrMethedsMap.values());
    }

    public AttrMethods getAttrMethodsByAttr(String attr) {
        return (AttrMethods)this.attrMethedsMap.get(attr);
    }

    public Class getCls() {
        return this.cls;
    }

    private void loadAttrMethods(Class cls) {
        Field[] farr = cls.getDeclaredFields();
        Method[] marr = cls.getDeclaredMethods();
        HashMap<String, Method> methodMap = new HashMap();
        Method[] var5 = marr;
        int var6 = marr.length;

        int var7;
        String key;
        for(var7 = 0; var7 < var6; ++var7) {
            Method m = var5[var7];
            String name = m.getName();
            key = null;
            if (name.startsWith("is") && m.getReturnType() == Boolean.TYPE && m.getParameterTypes().length == 0) {
                key = name + m.getReturnType().getName();
            } else if (name.startsWith("get") && m.getParameterTypes().length == 0) {
                key = name + m.getReturnType().getName();
            } else if (name.startsWith("set") && m.getParameterTypes().length == 1) {
                key = name + m.getParameterTypes()[0].getName();
            }

            if (key != null) {
                key = key.toLowerCase();
                if (methodMap.containsKey(key)) {
                    throw new RuntimeException("get/set方法出现重复，忽略了大小写！key=" + key);
                }

                methodMap.put(key, m);
            }
        }

        Field[] var12 = farr;
        var6 = farr.length;

        for(var7 = 0; var7 < var6; ++var7) {
            Field f = var12[var7];
            AttrMethods attrinfo = new AttrMethods();
            attrinfo.field = f;
            attrinfo.attr = f.getName().toLowerCase();
            attrinfo.refClass = cls;
            key = "get" + f.getName() + f.getType().getName();
            if (f.getType().equals(Boolean.TYPE)) {
                key = "is" + f.getName() + f.getType().getName();
            }

            key = key.toLowerCase();
            attrinfo.getMethod = (Method)methodMap.get(key);
            methodMap.remove(key);
            String setKey = "set" + f.getName() + f.getType().getName();
            setKey = setKey.toLowerCase();
            attrinfo.setMethod = (Method)methodMap.get(setKey);
            methodMap.remove(setKey);
            if (attrinfo.containsGetMethod() || attrinfo.containsSetMethod()) {
                this.attrMethedsMap.putIfAbsent(attrinfo.attr, attrinfo);
            }
        }

        methodMap.forEach((keyx, me) -> {
            AttrMethods attrinfo = new AttrMethods();
            attrinfo.refClass = cls;
            String mname = me.getName();
            if (mname.startsWith("is")) {
                attrinfo.attr = mname.substring(2).toLowerCase();
                attrinfo.getMethod = me;
            } else if (mname.startsWith("get")) {
                attrinfo.getMethod = me;
                attrinfo.attr = mname.substring(3).toLowerCase();
            } else if (mname.startsWith("set")) {
                attrinfo.setMethod = me;
                attrinfo.attr = mname.substring(3).toLowerCase();
            }

            AttrMethods exists = (AttrMethods)this.attrMethedsMap.get(attrinfo.attr);
            if (exists != null) {
                if (exists.refClass == attrinfo.refClass) {
                    if (attrinfo.getMethod != null && exists.getMethod == null) {
                        exists.getMethod = attrinfo.getMethod;
                    }

                    if (attrinfo.setMethod != null && exists.setMethod == null) {
                        exists.setMethod = attrinfo.setMethod;
                    }
                }
            } else {
                this.attrMethedsMap.put(attrinfo.attr, attrinfo);
            }

        });
        Class supercls = cls.getSuperclass();
        if (supercls != null && !supercls.equals(Object.class)) {
            this.loadAttrMethods(supercls);
        }

    }

    public Result getAttrValue(String attr, Object obj, boolean onExBackNull) throws Exception {
        obj = Result.toValue(obj);
        AttrMethods mlist = (AttrMethods)this.attrMethedsMap.get(attr);
        if (mlist != null && mlist.getMethod != null) {
            return Result.of(mlist.getMethod.invoke(obj));
        } else if (onExBackNull) {
            return null;
        } else {
            throw new Exception("没有找到属性的get方法！attr=" + attr + "; target=" + obj);
        }
    }

    public boolean setAttrValue(String attr, Object obj, Object value, boolean onExBackFalse) throws Exception {
        obj = Result.toValue(obj);
        AttrMethods mlist = (AttrMethods)this.attrMethedsMap.get(attr);
        if (mlist == null) {
            attr = CamelCaseUtil.underlineToCamel(attr);
            mlist = (AttrMethods)this.attrMethedsMap.get(attr);
        }

        if (mlist != null && mlist.setMethod != null) {
            Method setm = mlist.setMethod;
            return invokeSetMethod(setm, obj, value, onExBackFalse);
        } else if (onExBackFalse) {
            return false;
        } else {
            throw new Exception("没有找到属性的set方法！attr=" + attr + "; target=" + obj);
        }
    }

    public static boolean invokeSetMethod(Method setm, Object obj, Object value, boolean onExBackFalse) throws Exception {
        if (value == null) {
            setm.invoke(obj, value);
            return true;
        } else {
            Class pcls = setm.getParameterTypes()[0];
            if (pcls.isAssignableFrom(value.getClass())) {
                try {
                    setm.invoke(obj, value);
                    return true;
                } catch (Exception var6) {
                    throw new Exception("invokeSetMethod出错！pcls=" + pcls.getName() + "; obj=" + obj + "; value=" + value + "; valueCls=" + value.getClass());
                }
            } else {
                try {
                    Object cv = StringConvert.stringConvert(value.toString(), pcls);
                    setm.invoke(obj, cv);
                    return true;
                } catch (Exception var7) {
                    if (onExBackFalse) {
                        return false;
                    } else {
                        throw var7;
                    }
                }
            }
        }
    }

    public class AttrMethods {
        String attr;
        Field field;
        Method getMethod;
        Method setMethod;
        Class refClass;

        public AttrMethods() {
        }

        public boolean containsField() {
            return this.field != null;
        }

        public boolean containsSetMethod() {
            return this.setMethod != null;
        }

        public boolean containsGetMethod() {
            return this.getMethod != null;
        }

        public String getAttr() {
            return this.attr;
        }

        public Field getField() {
            return this.field;
        }

        public Method getGetMethod() {
            return this.getMethod;
        }

        public Method getSetMethod() {
            return this.setMethod;
        }

        public Class getRefClass() {
            return this.refClass;
        }
    }
}

