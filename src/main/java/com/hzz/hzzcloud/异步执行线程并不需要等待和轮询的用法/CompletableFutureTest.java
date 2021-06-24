package com.hzz.hzzcloud.异步执行线程并不需要等待和轮询的用法;

import java.util.concurrent.CompletableFuture;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/23 14:52
 */
public class CompletableFutureTest {

    public static void main(String[] args) throws Exception {
        // 创建异步执行任务:
        CompletableFuture<Double> cf = CompletableFuture.supplyAsync(CompletableFutureTest::fetchPrice);
        // 如果执行成功:
        cf.thenAccept((result) -> {
            System.out.println("price: " + result);
        });
        // 如果执行异常:
        cf.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
        for (int i = 0; i <10 ; i++) {
            System.out.println(i);
            Thread.sleep(2000);
        }
        
        // 主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:
        Thread.sleep(200);
    }

    static Double fetchPrice() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
        }
        if (Math.random() < 0.3) {
            throw new RuntimeException("fetch price failed!");
        }
        return 5 + Math.random() * 20;
    }

}
