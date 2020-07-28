package com.hzz.hzzcloud.设计模式.代理模式;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 17:55
 */
public class Proxy implements Sourceable {

    private Source source;
    public Proxy(){
        super();
        this.source = new Source();
    }
    @Override
    public void method() {
        before();
        source.method();
        atfer();
    }
    private void atfer() {
        System.out.println("after proxy!");
    }
    private void before() {
        System.out.println("before proxy!");
    }
}
