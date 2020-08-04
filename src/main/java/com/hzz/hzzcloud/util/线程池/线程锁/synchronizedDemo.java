package com.hzz.hzzcloud.util.线程池.线程锁;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/31 16:54
 */
public class synchronizedDemo {


    public static void main(String[] args) {

        Object lock = new Object();

        for (int i = 0; i < 2; i++) {
            int finalI = i;
            new Thread(() -> {
                if (finalI == 0) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                if (finalI == 1) {
                    try {
                        System.out.println("这边进入等待" + finalI);
                        synchronized (lock) {
                            lock.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if (finalI == 0) {
                    System.out.println("这边释放等待" + finalI);
                    synchronized (lock) {
                        lock.notifyAll();
                    }
                }
                System.out.println(finalI);

            }).start();
        }


    }
}
