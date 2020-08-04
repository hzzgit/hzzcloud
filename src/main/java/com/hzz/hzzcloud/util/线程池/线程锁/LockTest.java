package com.hzz.hzzcloud.util.线程池.线程锁;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：hzz
 * @description：wait() 1. wait()是Object超类中的方法，而await()是ConditionObject类里面的方法.
 *
 * 2. await会导致当前线程被阻塞，会释放锁，这点和wait是一样的
 *
 * 3. await中的lock不再使用synchronized把代码同步包装起来
 *
 * 4. await的阻塞需要另外的一个对象condition
 *
 * 5. notify是用来唤醒使用wait的线程；而signal是用来唤醒await线程。
 *
 * 6. 所在的超类不同使用场景也不同，wait一般用于Synchronized中，而await只能用于ReentrantLock锁中
 * @date ：2020/7/30 17:49
 */
public class LockTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
        Condition condition = lock.newCondition();
        for (int i = 0; i < 2; i++) {
            int finalI = i;
            new Thread(() -> {
                if(finalI==0){
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                lock.lock();
                try {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (finalI == 1) {
                        try {
                            System.out.println("这边进入等待"+finalI);
                            condition.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }else if(finalI==0){
                        System.out.println("这边释放等待"+finalI);
                        condition.signalAll();

                    }
                    System.out.println(finalI);
                } finally {
                     lock.unlock();
                }
            }).start();
        }
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
