package com.hzz.hzzcloud.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalExample {

    public static class MyRunnable implements Runnable {

        private ThreadLocal<Integer> threadLocal =

                new ThreadLocal<Integer>();
        int local = 0;

        @Override
        public void run() {

            threadLocal.set((int) (Math.random() * 100D));
            local = local + 1;

//            try {   //如果在休眠的过程中使用了interrupt(),是无法停止线程的
//
//                Thread.sleep(1000);
//
//            } catch (InterruptedException e) {
//
//            }
            while (true) {
            if(Thread.currentThread().isInterrupted()){
            break;
            }
                System.out.println("threadLocal:" + threadLocal.get());
                System.out.println("local:" + local);
            }
        }

    }


    public static void main(String[] args) throws InterruptedException {

        MyRunnable sharedRunnableInstance = new MyRunnable();

        Thread thread1 = new Thread(sharedRunnableInstance);

        Thread thread2 = new Thread(sharedRunnableInstance);

        thread1.start();

        thread2.start();
        Thread.sleep(1000);
        if(!thread1.isInterrupted()){
            thread1.interrupt();
        }
        if(!thread2.isInterrupted()){
            thread2.interrupt();
        }
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } // wait for thread 1 to terminate

        // wait for thread 2 to terminate

    }

}