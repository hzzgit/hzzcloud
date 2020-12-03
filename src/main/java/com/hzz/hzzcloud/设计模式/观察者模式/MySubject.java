package com.hzz.hzzcloud.设计模式.观察者模式;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 20:34
 */
public class MySubject extends AbstractSubject {


    private String name;

    public MySubject() {
    }

    public MySubject(String name) {
        this.name = name;
    }

    @Override
    public void operation() {
        System.out.println("update self!");
        notifyObservers(name);
    }

}
