package com.hzz.hzzcloud.设计模式.桥接模式;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 18:02
 */
public class MyBridge extends Bridge {
    @Override
    public void method(){
        getSource().method();
    }
}