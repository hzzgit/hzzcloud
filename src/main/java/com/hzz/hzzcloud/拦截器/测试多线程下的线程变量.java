package com.hzz.hzzcloud.拦截器;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/27 15:50
 */
public class 测试多线程下的线程变量 implements Runnable {
    AtomicInteger atomicInteger=new AtomicInteger(1);
    int a=1;
    MyLocalThreadbyinterceptor myLocalThreadbyinterceptor=new MyLocalThreadbyinterceptor();
    @Override
    public void run() {
        myLocalThreadbyinterceptor.add();
        atomicInteger.incrementAndGet();
        a++;
    }


    public static void main(String[] args) {
        测试多线程下的线程变量 a=new 测试多线程下的线程变量();
        ExecutorService executorService = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
        for (int i = 0; i <111111 ; i++) {
            executorService.execute(a);
        }
        executorService.shutdown();
        try {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(a.myLocalThreadbyinterceptor.get());
        System.out.println(a.atomicInteger.get());
        System.out.println(a.a);
    }

}
