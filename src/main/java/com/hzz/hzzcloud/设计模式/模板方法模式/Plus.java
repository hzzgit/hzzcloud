package com.hzz.hzzcloud.设计模式.模板方法模式;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 20:19
 */
public class Plus extends AbstractCalculator {

    @Override
    public int calculate(int num1,int num2) {
        return num1 + num2;
    }
}