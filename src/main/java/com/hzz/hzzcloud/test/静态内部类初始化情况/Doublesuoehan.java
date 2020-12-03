package com.hzz.hzzcloud.test.静态内部类初始化情况;

import java.util.concurrent.*;

/**
 * @author ：hzz
 * @description：双重锁获单例饿汉
 * @date ：2020/12/2 10:10
 */
public class Doublesuoehan {

    private static Doublesuoehan doublesuoehan;

    private Doublesuoehan() {
        System.out.println("创建该对象");
    }

    public static Doublesuoehan getInstance() {
        if (doublesuoehan == null) {
            synchronized (Doublesuoehan.class) {
                if (doublesuoehan == null) {
                    doublesuoehan = new Doublesuoehan();
                    System.out.println("单例模式"+Thread.currentThread().getName());
                }
            }
        }
        return doublesuoehan;
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(30);// 线程池.大小根据有多少天
        for (int i = 0; i < 100; i++) {
            int finalI = i;
            executorService.submit(new Thread(new Runnable() {
                public void run() {
                    try {
                        Doublesuoehan instance = Doublesuoehan.getInstance();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }));
        }

        executorService.shutdown();
        try {
            executorService.awaitTermination(600, TimeUnit.SECONDS);
        } catch (InterruptedException e) {

            e.printStackTrace();
        }


    }
}
