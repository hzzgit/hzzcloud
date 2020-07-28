package com.hzz.hzzcloud.设计模式.适配器模式;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 17:31
 */
public class Wrapper implements Targetable {

    private Source source;

    public Wrapper(Source source){
        super();
        this.source = source;
    }
    @Override
    public void method2() {
        System.out.println("this is the targetable method!");
    }

    @Override
    public void method1() {
        source.method1();
    }
}
