package com.hzz.hzzcloud.test;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/9/28 10:26
 */
public  class Final修饰符 {
    private final  int a1;

    public Final修饰符(int a1) {
        this.a1 = a1;
    }

    private static final class AnnotationCacheEntry {

        private final int name;

        private AnnotationCacheEntry(int name) {
            this.name = name;
        }

    }
}
