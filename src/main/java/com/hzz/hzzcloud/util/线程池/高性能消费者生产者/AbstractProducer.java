package com.hzz.hzzcloud.util.线程池.高性能消费者生产者;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/21 17:35
 */
public abstract class AbstractProducer implements Runnable {

    protected abstract void produce() throws InterruptedException;

    @Override
    public void run() {
        while (true) {
            try {
                produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}