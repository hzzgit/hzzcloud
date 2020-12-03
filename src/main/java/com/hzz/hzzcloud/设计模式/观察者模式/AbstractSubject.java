package com.hzz.hzzcloud.设计模式.观察者模式;

import java.util.Enumeration;
import java.util.Vector;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 20:33
 */
public abstract class AbstractSubject implements Subject {

    private Vector<Observer> vector = new Vector<Observer>();
    @Override
    public void add(Observer observer) {
        vector.add(observer);
    }

    @Override
    public void del(Observer observer) {
        vector.remove(observer);
    }

    @Override
    public void notifyObservers( String name) {
        Enumeration<Observer> enumo = vector.elements();
        while(enumo.hasMoreElements()){
            enumo.nextElement().update(name);
        }
    }
}
