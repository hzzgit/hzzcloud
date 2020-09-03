package com.hzz.hzzcloud.util.线程池.带有回调的线程;

import java.util.concurrent.*;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/29 10:48
 */
public class mainThread implements Callable<Integer> {


    @Override
    public Integer call() throws Exception {
        Integer a = 1;
        for (int i = 0; i < 10; i++) {
            a += 2;
        }
        System.out.println(Thread.currentThread().getName()+":"+a);
        Thread.sleep(3000);
        return a;
    }

    public static void main(String[] args) {
        mainThread mainThread = new mainThread();
        ExecutorService executorService =new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(),   new MyThreadFactory());
        Future<Integer> submit = executorService.submit(mainThread);
        final Integer[] integer = {null};
        new Thread(() -> {
            try {
                integer[0] = submit.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+integer[0]);
        },"第二").start();

        System.out.println(integer[0]);


    }
}
