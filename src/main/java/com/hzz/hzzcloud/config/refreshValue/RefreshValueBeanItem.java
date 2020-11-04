package com.hzz.hzzcloud.config.refreshValue;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/3 9:46
 */

import com.hzz.hzzcloud.common.el.ClassGetSetMethodCache;
import com.hzz.hzzcloud.common.el.ClassMethodCacheMgr;
import com.hzz.hzzcloud.common.util.JacksonUtil;
import org.springframework.beans.factory.annotation.Value;

import java.util.*;

public class RefreshValueBeanItem {
    private String beanName;
    private Object bean;
    private ClassGetSetMethodCache getSetMethodCache;
    private List<RefreshValueBeanItem.RefreshValueFieldInfo> fieldList = new ArrayList();

    RefreshValueBeanItem(String beanName, Object bean) {
        this.beanName = beanName;
        this.bean = bean;
        this.loadValueFields();
    }

    int fieldSize() {
        return this.fieldList.size();
    }

    List<RefreshValueBeanItem.RefreshValueFieldInfo> getRefreshValueFieldList() {
        return this.fieldList;
    }

    private void loadValueFields() {
        this.getSetMethodCache = ClassMethodCacheMgr.getInstance().getClassGetSetMethodCache(this.bean.getClass());
        Iterator var1 = this.getSetMethodCache.getAllAttrMethods().iterator();

        while(var1.hasNext()) {
            ClassGetSetMethodCache.AttrMethods attr = (ClassGetSetMethodCache.AttrMethods)var1.next();
            if (attr.containsField() && attr.containsSetMethod()) {
                Value valueAnno = (Value)attr.getField().getAnnotation(Value.class);
                if (valueAnno != null) {
                    RefreshValueBeanItem.RefreshValueFieldInfo vfi = new RefreshValueBeanItem.RefreshValueFieldInfo();
                    vfi.attrMethods = attr;
                    String nameExp = valueAnno.value();
                    if (nameExp.startsWith("${")) {
                        int i = nameExp.indexOf(":");
                        if (i != -1) {
                            vfi.prepName = nameExp.substring(2, i);
                        } else {
                            int j = nameExp.indexOf("}");
                            vfi.prepName = nameExp.substring(2, j);
                        }
                    }

                    this.fieldList.add(vfi);
                }
            }
        }

    }

    @Override
    public String toString() {
        Map map = new LinkedHashMap();
        map.put("beanName", this.beanName);
        map.put("bean", this.bean.getClass().getName());
        List<Map> flist = new ArrayList();
        Iterator var3 = this.fieldList.iterator();

        while(var3.hasNext()) {
            RefreshValueBeanItem.RefreshValueFieldInfo vf = (RefreshValueBeanItem.RefreshValueFieldInfo)var3.next();
            flist.add(vf.toStringMap());
        }

        map.put("fieldList", flist);
        return JacksonUtil.toJsonString(map);
    }

    class RefreshValueFieldInfo {
        String prepName;
        ClassGetSetMethodCache.AttrMethods attrMethods;

        RefreshValueFieldInfo() {
        }

        public void setValue(String value) throws Exception {
            ClassGetSetMethodCache.invokeSetMethod(this.attrMethods.getSetMethod(), RefreshValueBeanItem.this.bean, value, false);
        }

        public Map toStringMap() {
            Map map = new LinkedHashMap();
            map.put("baenCls", RefreshValueBeanItem.this.bean.getClass().getName());
            map.put("prepName", this.prepName);
            map.put("setMethod", this.attrMethods.getSetMethod().getName());
            return map;
        }

        @Override
        public String toString() {
            return JacksonUtil.toJsonString(this.toStringMap());
        }
    }
}
