package com.hzz.hzzcloud.设计模式.模板方法模式;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 20:19
 */
public class StrategyTest {

    public static void main(String[] args) {
        String exp = "8+8";
        AbstractCalculator cal = new Plus();
        int result = cal.calculate(exp, "\\+");
        System.out.println(result);
    }
}