package com.hzz.hzzcloud.util.线程池.CountDownLatchDemo锁例子等待任务完成后再执行;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/31 16:29
 */
public class CountDownLatchDemo {
    final static SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) throws InterruptedException {

        new Thread(()->{
            CountDownLatch latch=new CountDownLatch(2);//两个工人的协作
            Worker worker1=new Worker("zhang san", 5000, latch);
            Worker worker2=new Worker("li si", 8000, latch);
            worker1.start();//
            worker2.start();//
            try {
                latch.await();//等待所有工人完成工作
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("all work done at "+sdf.format(new Date()));
        }).start();

        System.out.println(111);

    }


    static class Worker extends Thread{
        String workerName;
        int workTime;
        CountDownLatch latch;
        public Worker(String workerName ,int workTime ,CountDownLatch latch){
            this.workerName=workerName;
            this.workTime=workTime;
            this.latch=latch;
        }
        @Override
        public void run(){
            System.out.println("Worker "+workerName+" do work begin at "+sdf.format(new Date()));
            doWork();//工作了
            System.out.println("Worker "+workerName+" do work complete at "+sdf.format(new Date()));
            latch.countDown();//工人完成工作，计数器减一

        }

        private void doWork(){
            try {
                Thread.sleep(workTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
