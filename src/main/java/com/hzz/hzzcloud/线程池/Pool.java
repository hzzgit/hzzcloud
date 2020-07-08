package com.hzz.hzzcloud.线程池;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Pool {
    public static void main(String[] args) {
//        ThreadPoolExecutor executor;
//        executor = new ThreadPoolExecutor(3, 11, 3, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
//        for (int i = 0; i <6 ; i++) {
//            Runnable myRunnable = new Runnable() {
//                @Override
//                public void run() {
//                    try {
//                        Thread.sleep(2000);
//                        System.out.println(Thread.currentThread().getName() + " run");
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//            };
//            executor.execute(myRunnable);
//            executor.submit(myRunnable);
//        }

        ScheduledThreadPoolExecutor  scheduled = new ScheduledThreadPoolExecutor(10);
        for (int i = 0; i <10 ; i++) {
            scheduled.scheduleAtFixedRate(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+new Date());
                }
            }, 0, 4, TimeUnit.SECONDS);//0表示首次执行任务的延迟时间，40表示每次执行任务的间隔时间，TimeUnit.MILLISECONDS执行的时间间隔数值单位

        }


    }
}
