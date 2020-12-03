package com.hzz.hzzcloud.设计模式.装饰模式;

import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：hzz
 * @description：装饰模式，主要是为了给已有的类的某些方法的前后添加新的方法和功能
 * @date ：2020/7/28 17:48
 */
public class Decorator implements Sourceable {

    private Sourceable source;

    public Decorator(Sourceable source){
        super();
        this.source = source;
    }
    @Override
    public void method() {
        System.out.println("before decorator!");
        source.method();
        System.out.println("after decorator!");
    }
}