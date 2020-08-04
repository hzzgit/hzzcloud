package com.hzz.hzzcloud.util.线程池;

import java.util.concurrent.*;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/29 14:10
 */
public class 多种线程池 {

    public static void main(String[] args) {
        //这种线程池，无限队列，最大值即最大处理线程数，一般设置成int的最大值，也就是32位字节 2的31次方
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(0, 5,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());

        //这种线程池，是无界队列线程池，多出的部分会添加到队列中等待，后面慢慢执行
        ThreadPoolExecutor threadPoolExecutor1 = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>());

        //这种线程池，永远都只执行一个，按顺序
        ThreadPoolExecutor threadPoolExecutor2= new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<Runnable>(10));


        // LinkedBlockingQueue 其实也是有界但是默认值是int的最大值 这种是阻塞的队列，核心线程用完之后就进行阻塞队列中等待，如果阻塞队列有限制大小，那么达到长度之后，就会新建线程
        //SynchronousQueue 这种应该是完全同步锁的队列，容量是无限的，存完就会立刻被拿出来使用，所以这个和最大线程数直接挂钩
        //ArrayBlockingQueue 有界队列，必须定义队列大小
        //四种拒绝策略
        //1、直接丢弃，并抛出异常
        //2、直接丢弃，静默丢弃
        //3、喜新厌旧，舍弃最后的线程，并执行最新的
        //4、直接执行，但是会阻塞主线程
        for (int i = 0; i <13 ; i++) {
            threadPoolExecutor1.submit(new Runnable() {
                @Override
                public void run() {
                    System.out.println(1);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        System.out.println("结束");



    }
}
