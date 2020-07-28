package com.hzz.hzzcloud.设计模式.装饰模式;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 17:47
 */
public class Source implements Sourceable {

    @Override
    public void method() {
        System.out.println("the original method!");
    }
}
