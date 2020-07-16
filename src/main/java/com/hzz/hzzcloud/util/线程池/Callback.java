package com.hzz.hzzcloud.util.线程池;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Callback {
    public static void main(String[] args) {

        Callable myCallable = new Callable() {
            @Override
            public String call()  {
                System.out.println("calld方法执行了");

                Integer.parseInt("1sss");
            return ":";
            }
        };

        //创建一个 ThreadPoolExecutor 对象
        ExecutorService executorService = Executors.newFixedThreadPool(1);

        Future<String> future = executorService.submit(myCallable);

        try {
        String i= future.get();
            System.out.println(i);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
