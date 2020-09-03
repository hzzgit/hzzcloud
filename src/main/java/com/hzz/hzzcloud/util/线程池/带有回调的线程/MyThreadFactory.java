package com.hzz.hzzcloud.util.线程池.带有回调的线程;

import java.util.concurrent.ThreadFactory;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/9/2 9:31
 */
public class MyThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        return new Thread(r,"测试");
    }
}
