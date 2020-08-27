package com.hzz.hzzcloud.util.线程池;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/27 16:00
 */
public class ThreadExHandler implements RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {

    }
}
