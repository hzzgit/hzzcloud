package com.hzz.hzzcloud.util.线程池;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/29 14:10
 */
public class 多种线程池 {

    public static void main(String[] args) {
        //这种线程池，无限队列，最大值即最大处理线程数，一般设置成int的最大值，也就是32位字节 2的31次方
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 5,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        //这种线程池，是有限制队列数量，对于线程的把控要求非常严格，超过10的部分会创建额外线程来进行处理
        ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(1, 3,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10));

        for (int i = 0; i <14 ; i++) {
            threadPoolExecutor.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(1);
                    try {
                        Thread.sleep(30000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }



    }
}
