package com.hzz.hzzcloud.设计模式.单例模式.饿汉模式;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 10:03
 */
public class singincreate {
    private static singincreate singin;

    private singincreate(){

    }

    private static synchronized void init() {
        synchronized (singin) {//不要每次都启动同步锁
            if (singin == null) {
                singin = new singincreate();
            }
        }
    }

    public static singincreate getsingin() {
        if (singin == null) {
            init();
        }
        return singin;
    }


}
