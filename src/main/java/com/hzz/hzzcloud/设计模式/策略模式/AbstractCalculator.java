package com.hzz.hzzcloud.设计模式.策略模式;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 20:15
 */
public abstract class AbstractCalculator {

    public int[] split(String exp,String opt){
        String array[] = exp.split(opt);
        int arrayInt[] = new int[2];
        arrayInt[0] = Integer.parseInt(array[0]);
        arrayInt[1] = Integer.parseInt(array[1]);
        return arrayInt;
    }
}