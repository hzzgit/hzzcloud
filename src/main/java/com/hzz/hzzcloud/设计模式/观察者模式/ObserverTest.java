package com.hzz.hzzcloud.设计模式.观察者模式;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 20:34
 */
public class ObserverTest {

    public static void main(String[] args) {
        Subject sub = new MySubject("腾");
        Observer1 observer1 = new Observer1();
        sub.add(observer1);
        sub.add(new Observer2());
        sub.add(new Observer3());

        sub.del(observer1);
        sub.operation();
    }

}