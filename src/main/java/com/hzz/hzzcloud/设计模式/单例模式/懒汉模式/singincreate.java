package com.hzz.hzzcloud.设计模式.单例模式.懒汉模式;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 10:03
 */
public class singincreate {
    private singincreate singin = new singincreate();

    public singincreate getsingin() {
        return singin;
    }
}
