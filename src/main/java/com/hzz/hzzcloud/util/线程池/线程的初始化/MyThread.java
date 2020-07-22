package com.hzz.hzzcloud.util.线程池.线程的初始化;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/21 11:45
 */
public class MyThread implements  Runnable{

    int idd=1;


    @Override
    public void run() {
        System.out.println(idd++);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

        MyThread myThread=new MyThread();
        new Thread(myThread).start();
        new Thread(myThread).start();


    }
}
