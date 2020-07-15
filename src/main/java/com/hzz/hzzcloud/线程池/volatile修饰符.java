package com.hzz.hzzcloud.线程池;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/15 10:28
 */
public class volatile修饰符  implements Runnable{
    private volatile boolean active;
    @Override
    public void run()
    {
        active = true;
        while (active) // 第一行
        {
            System.out.println(1);
            // 代码
        }
    }
    public void stop()
    {
        active = false; // 第二行
    }

    public static void main(String[] args) {
        AtomicBoolean atomicBoolean=new AtomicBoolean(true);
        atomicBoolean.set(true);
        volatile修饰符 volatile修饰符 = new volatile修饰符();
        new Thread(volatile修饰符).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        volatile修饰符.stop();



    }
}
