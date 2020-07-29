package com.hzz.hzzcloud.设计模式.策略模式;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 20:16
 */
public class StrategyTest {

    public static void main(String[] args) {
        String exp = "2+8";
        ICalculator cal = new Plus();
        int result = cal.calculate(exp);
        System.out.println(result);
    }
}