package com.hzz.hzzcloud.设计模式.观察者模式;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 20:33
 */
public class Observer3 implements Observer {

    @Override
    public void update(String name) {
        System.out.println("observer3 has received!    "+name);
    }
}