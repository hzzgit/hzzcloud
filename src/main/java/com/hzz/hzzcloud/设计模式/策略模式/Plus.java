package com.hzz.hzzcloud.设计模式.策略模式;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 20:15
 */
public class Plus extends AbstractCalculator implements ICalculator {

    @Override
    public int calculate(String exp) {
        int arrayInt[] = split(exp,"\\+");
        return arrayInt[0]+arrayInt[1];
    }
}
