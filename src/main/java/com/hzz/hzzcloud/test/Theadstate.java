package com.hzz.hzzcloud.test;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/11 14:06
 */
public class Theadstate {
    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            try {
                while (true) {

                    System.out.println(1);
//                if (Thread.currentThread().isInterrupted()) {
//                    return;
//                }

                    Thread.sleep(1000);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        Thread thread1 = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("我就是一个守护线程");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread1.setDaemon(true);
        thread1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }
}
