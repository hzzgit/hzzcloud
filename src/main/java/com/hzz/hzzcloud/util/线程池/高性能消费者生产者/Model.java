package com.hzz.hzzcloud.util.线程池.高性能消费者生产者;

public interface Model {
    Runnable newRunnableConsumer();

    Runnable newRunnableProducer();
}