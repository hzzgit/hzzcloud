package com.hzz.hzzcloud.util.线程池.线程的初始化;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/21 17:00
 */
public class extendThrad    {
    int idd=1;

    class neiThread extends Thread{

        @Override
        public void run() {
            System.out.println(idd++);
        }
    }

    public Thread getneithread(){
        return  new neiThread();
    }

    public static void main(String[] args) {
        extendThrad extendThrad=  new extendThrad();
        Thread getneithread = extendThrad.getneithread();
        getneithread.start();
        Thread getneithread2 = extendThrad.getneithread();
        getneithread2.start();
    }
}
