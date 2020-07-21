package com.hzz.hzzcloud.util.线程池.高性能消费者生产者;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/21 17:36
 */
public class Task {
    private int no;
    public Task(int no) {
        this.no = no;
    }

    public int getNo() {
        return no;
    }
}