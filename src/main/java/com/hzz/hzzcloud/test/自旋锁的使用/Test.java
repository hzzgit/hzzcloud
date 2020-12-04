package com.hzz.hzzcloud.test.自旋锁的使用;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        /**
         * 创建线程对象
         */
        SpinLock lock = new SpinLock();
        TicketRunnable ticketRunnable = new TicketRunnable(lock);
        // 第1个人
        Thread t1 = new Thread(ticketRunnable, "张三");
        // 第2个人
        Thread t2 = new Thread(ticketRunnable, "李四");
        // 第3个人
        Thread t3 = new Thread(ticketRunnable, "王五");
        // 第4个人
        Thread t4 = new Thread(ticketRunnable, "赵六");
        // 第5个人
        Thread t5 = new Thread(ticketRunnable, "田七");
        //启动线程

        t2.start();
        t1.start();
        t3.start();
        t4.start();
        t5.start();
        /**
         *
         //创建一个固定可重用的线程池
         ExecutorService executorService = Executors.newFixedThreadPool(5);
         //添加5个线程到线程池里
         for (int i = 0; i < 5; i++) {
         executorService.execute(new TicketRunnable(lock));
         }
         executorService.shutdown();
         */
    }
}