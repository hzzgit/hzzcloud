package com.hzz.hzzcloud.util.线程池.高性能消费者生产者;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/22 10:21
 */
public class 自己写一个最简单的消费者生产者 {
    private ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    private AtomicInteger co = new AtomicInteger(0);

    private final int capacity;

    public 自己写一个最简单的消费者生产者(int capacity) {
        this.capacity = capacity;
    }

    public Runnable commues() {
        return new AbstractConsumer() {
            @Override
            protected void consume() throws InterruptedException {
                // lock.lockInterruptibly();
                try {
                    synchronized (co) {
                        if (co.get() == 0) {

                            co.wait();
                        }

                        Thread.sleep(500 + (long) (Math.random() * 500));
                        System.out.println(queue.poll() + ",消费者取出来");
                        co.decrementAndGet();

                        co.notifyAll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //   lock.unlock();
                }
            }

        };
    }

    public Runnable product() {
        return new AbstractProducer() {
            @Override
            protected void produce() throws InterruptedException {
                //   lock.lockInterruptibly();
                try {
                    synchronized (co) {
                        if (co.get() == capacity) {

                            co.wait();

                        }

                        Thread.sleep(1000 + (long) (Math.random() * 500));
                        System.out.println(queue.add(1) + ",生产者加进去");
                        co.incrementAndGet();

                        co.notifyAll();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //  lock.unlock();
                }
            }

        };

    }

    public static void main(String[] args) {
        自己写一个最简单的消费者生产者 test = new 自己写一个最简单的消费者生产者(10);
        for (int i = 0; i < 5; i++) {
            new Thread(test.commues()).start();
            new Thread(test.product()).start();
        }
    }
}
