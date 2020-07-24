package com.hzz.hzzcloud.util.线程池;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/15 10:28
 */
public class volatile修饰符  implements Runnable{
    private volatile int active;
    @Override
    public void run()
    {
        for (int i = 0; i < 200; i++) {
            synchronized (this) {
                active++;
            }
        }
    }
    public void say(){
        System.out.println(active);
    }

    public static void main(String[] args) {
        AtomicBoolean atomicBoolean=new AtomicBoolean(true);
        atomicBoolean.set(true);
        volatile修饰符 volatile修饰符 = new volatile修饰符();

        for (int i = 0; i <100 ; i++) {
            new Thread(volatile修饰符).start();
        }
        while (Thread.activeCount()>1){
            Thread.yield();
        }
        volatile修饰符.say();


    }
}
