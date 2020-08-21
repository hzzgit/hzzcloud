package com.hzz.hzzcloud.test;

import java.util.UUID;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/11 20:38
 */
public class 守护线程 {

    public test gettest(){
        return  new test();
    }
    class test implements Thread.UncaughtExceptionHandler {

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(t.getId());
            System.out.println(e.getMessage());
            StackTraceElement[] stackTrace = e.getStackTrace();
            for (StackTraceElement stackTraceElement : stackTrace) {
                System.out.println(stackTraceElement.toString());
            }
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);


                  //  Integer a = Integer.parseInt("测试");

                    守护线程 A = null;
                    test gettest = A.gettest();

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        test gettest = new 守护线程().gettest();
        thread.setUncaughtExceptionHandler(gettest);
        thread.start();

    }

}
