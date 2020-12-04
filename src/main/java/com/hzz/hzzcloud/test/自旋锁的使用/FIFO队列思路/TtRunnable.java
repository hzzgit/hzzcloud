package com.hzz.hzzcloud.test.自旋锁的使用.FIFO队列思路;

import com.hzz.hzzcloud.test.自旋锁的使用.SpinLock;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/3 17:12
 */
public class TtRunnable implements Runnable {

    // 剩余的票数
    static int count = 10;
    // 抢到第几张票
    static int num = 0;
    // 是否售完票
    boolean flag = false;
    //自旋锁
    private SpinLock spinLock;

    //构造函数
    public TtRunnable() {
    }

    public TtRunnable(SpinLock lock) {
        this.spinLock = lock;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        // 票没有售完的情况下，继续抢票
        while (!flag) {
            sale();
        }
    }

    /**
     * 售票
     */
    private void sale() {
        try {
            //上锁
            spinLock.lock();
            if (count <= 0) {
                flag = true;
                return;
            }
            // 剩余的票数 减1
            count--;
            // 抢到第几张票 加1
            num++;
            System.out.println(Thread.currentThread().getName() + "抢到第" + num + "张票，剩余" + count + "张票。");
        }finally {
            //释放锁
            spinLock.unlock();
        }

    }
}