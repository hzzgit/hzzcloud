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
        Integer a=1;
        for (int i = 0; i <10 ; i++) {
            a+=2;
        }
        Thread.sleep(3000);
        return a;
    }

    public static void main(String[] args) {
        mainThread mainThread = new mainThread();
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> submit = executorService.submit(mainThread);
        Integer integer = null;
        try {
            integer = submit.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(integer);


    }
}
