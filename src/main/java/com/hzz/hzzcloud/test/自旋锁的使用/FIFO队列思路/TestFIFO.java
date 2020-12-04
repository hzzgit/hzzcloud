package com.hzz.hzzcloud.test.自旋锁的使用.FIFO队列思路;
import com.hzz.hzzcloud.test.自旋锁的使用.SpinLock;
import com.hzz.hzzcloud.test.自旋锁的使用.TicketRunnable;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TestFIFO {

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

        try {
            // 基于内存的阻塞队列
            BlockingQueue<Thread> queue = new LinkedBlockingQueue<Thread>();
            // 加入队列,put(E e)将元素插入此队列的尾部，如果该队列已满，则一直阻塞
            queue.put(t1);
            queue.put(t2);
            queue.put(t3);
            queue.put(t4);
            queue.put(t5);
            // 执行队列
            while (!queue.isEmpty()) {
                //take()获取并移除此队列头元素，若没有元素则一直阻塞。
                queue.take().start();
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}