package com.hzz.hzzcloud.util.线程池;

import com.hzz.hzzcloud.util.线程池.线程的初始化.MyThread;

import java.util.concurrent.*;

public class Callback {
    public static void main(String[] args) {

        Callable myCallable = new Callable() {
            @Override
            public String call()  {
                System.out.println("calld方法执行了");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Integer.parseInt("1sss");
            return ":";
            }
        };

        //创建一个 ThreadPoolExecutor 对象
        ExecutorService executorService = new ThreadPoolExecutor(1,2,0,TimeUnit.SECONDS,
                new LinkedBlockingQueue<Runnable>(),new ThreadPoolExecutor.DiscardOldestPolicy());
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(2);
        scheduledExecutorService.scheduleAtFixedRate(new MyThread(),1,4,TimeUnit.SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(new MyThread(),1,4,TimeUnit.SECONDS);
        Future<String> future = executorService.submit(myCallable);
        executorService.submit(myCallable);
        ConcurrentLinkedQueue concurrentLinkedQueue=new ConcurrentLinkedQueue();
        try {
        String i= future.get();
            System.out.println(i);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
