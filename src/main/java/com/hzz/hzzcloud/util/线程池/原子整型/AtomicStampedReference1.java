package com.hzz.hzzcloud.util.线程池.原子整型;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author ：hzz
 * @description：这种是保证了CAS 且保证了ABA的情况
 * @date ：2020/7/31 10:58
 */
//关键代码
public class AtomicStampedReference1<V> {

    private static AtomicStampedReference<Integer> atomicStampedRef =
            new AtomicStampedReference<>(1, 0);
    public static void main(String[] args){
//        int stamp1= atomicStampedRef.getStamp();
//        atomicStampedRef.compareAndSet(1,4,stamp1,stamp1+1);
//        Integer reference = atomicStampedRef.getReference();
//        System.out.println(reference);
        Thread main = new Thread(() -> {
            System.out.println("操作线程" + Thread.currentThread() +",初始值 a = " + atomicStampedRef.getReference());
            int stamp = atomicStampedRef.getStamp(); //获取当前标识别
            try {
                Thread.sleep(1000); //等待1秒 ，以便让干扰线程执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean isCASSuccess = atomicStampedRef.compareAndSet(1,4,stamp,stamp +1);  //此时expectedReference未发生改变，但是stamp已经被修改了,所以CAS失败
            while (!isCASSuccess){
                stamp = atomicStampedRef.getStamp();
                isCASSuccess = atomicStampedRef.compareAndSet(1,4,stamp,stamp +1);
            }

            System.out.println("操作线程" + Thread.currentThread() +",CAS操作结果: " + isCASSuccess);
            System.out.println("操作线程" + Thread.currentThread() +",值 = "+ atomicStampedRef.getReference());
        },"主操作线程");

        Thread other = new Thread(() -> {
            Thread.yield(); // 确保thread-main 优先执行
            atomicStampedRef.compareAndSet(1,2,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() +1);
            System.out.println("操作线程" + Thread.currentThread() +",【increment】 ,值 = "+ atomicStampedRef.getReference());
            atomicStampedRef.compareAndSet(2,1,atomicStampedRef.getStamp(),atomicStampedRef.getStamp() +1);
            System.out.println("操作线程" + Thread.currentThread() +",【decrement】 ,值 = "+ atomicStampedRef.getReference());
        },"干扰线程");

        main.start();
        other.start();
    }
}