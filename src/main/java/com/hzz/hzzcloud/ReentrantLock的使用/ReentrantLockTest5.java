package com.hzz.hzzcloud.ReentrantLock的使用;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/10/10 14:21
 */
public class ReentrantLockTest5 {

    private ReentrantLock lock = new ReentrantLock();

    public void tryLockInterruptTest() {
        long beginTime = System.currentTimeMillis();
        while(System.currentTimeMillis() - beginTime <100) {}
        try {
            while (true) {
                if (lock.tryLock(5, TimeUnit.SECONDS)) {
                    try {
                        System.out.println(Thread.currentThread().getName() + " tryLock get lock");
                    } finally {
                        lock.unlock();
                    }
                    break;
                }
                System.out.println("等待超时");
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            System.out.println(Thread.currentThread().getName() + " was interrupted");
        }
    }


    public void lockTest() {
        try{
            // 当前线程在锁可用时直接获得锁，锁不可用时阻塞当前线程
            lock.lock();
            System.out.println(Thread.currentThread().getName() + " lock get lock");
            long beginTime = System.currentTimeMillis();
            while(System.currentTimeMillis() - beginTime <10000) {}
        } finally {
            lock.unlock();
            System.out.println(Thread.currentThread().getName() + " lock release lock");
        }

    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        final ReentrantLockTest5 test =  new ReentrantLockTest5();
        Thread thread_tryLock = new Thread(new Runnable(){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                test.tryLockInterruptTest();
            }
        },"tryLockInterruptTest");
        Thread thread_lock = new Thread(new Runnable(){
            @Override
            public void run() {
                // TODO Auto-generated method stub
                test.lockTest();
            }
        },"lockTest");
        thread_tryLock.start();
        thread_lock.start();
        try {
            Thread.sleep(6000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            System.out.println("main thread was interrupted");
        }
        thread_tryLock.interrupt();
    }

}

