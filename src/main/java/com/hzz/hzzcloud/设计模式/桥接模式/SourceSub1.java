package com.hzz.hzzcloud.设计模式.桥接模式;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 18:01
 */
public class SourceSub1 implements Sourceable {

    @Override
    public void method() {
        System.out.println("this is the first sub!");
    }
}
