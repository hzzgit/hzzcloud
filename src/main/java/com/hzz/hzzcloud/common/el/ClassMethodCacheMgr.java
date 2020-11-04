package com.hzz.hzzcloud.common.el;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/3 9:55
 */



import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClassMethodCacheMgr {
    private static ClassMethodCacheMgr mgr = new ClassMethodCacheMgr();
    private Map<String, ClassGetSetMethodCache> map = new ConcurrentHashMap();

    private ClassMethodCacheMgr() {
    }

    public static ClassMethodCacheMgr getInstance() {
        return mgr;
    }

    public ClassGetSetMethodCache getClassGetSetMethodCache(Class cls) {
        ClassGetSetMethodCache cache = (ClassGetSetMethodCache)this.map.get(cls.getName());
        if (cache == null) {
            synchronized(this.map) {
                cache = (ClassGetSetMethodCache)this.map.get(cls.getName());
                if (cache == null) {
                    cache = new ClassGetSetMethodCache(cls);
                    this.map.put(cls.getName(), cache);
                }
            }
        }

        return cache;
    }
}

