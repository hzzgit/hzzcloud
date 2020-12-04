package com.hzz.hzzcloud.test.自旋锁的使用;


import java.util.concurrent.atomic.AtomicReference;

/**
 * CAS算法：自旋锁
 * @author Administrator
 *
 */
public class SpinLock {
    /**
     * AtomicReference是对“对象”进行原子操作，用于规范原子变量的性质，可以保证你在修改对象引用时的线程安全性。
     * Atomic家族主要是保证多线程环境下的原子性，相比synchronized而言更加轻量级。
     */
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    public void lock() {
        // 获取当前线程
        Thread current = Thread.currentThread();
        // 循环，直到atomicReference不为空
        while (!atomicReference.compareAndSet(null, current)) {}
    }

    public void unlock() {
        // 获取当前线程
        Thread current = Thread.currentThread();
        // 设置atomicReference为空
        atomicReference.compareAndSet(current, null);
    }


    public static void main(String[] args) {
        SpinLock  spinLock =new SpinLock();
        spinLock.lock();
        spinLock.unlock();
    }

}