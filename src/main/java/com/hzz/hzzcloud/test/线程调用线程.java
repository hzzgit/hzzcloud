package com.hzz.hzzcloud.test;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/9/4 15:37
 */
public class 线程调用线程 {

    class thread1 implements Runnable{

        @Override
        public void run() {
            System.out.println(1);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new Thread(new thread1()).start();
        }
    }

    class thread2 implements Runnable{
        private int co=0;
        @Override
        public void run() {
        while (true){
            System.out.println(co);
            co=co+1;
            if(co>5){
            break;
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        }
    }


    public thread2 getthread2(){
        return  new thread2();
    }

    public thread1 getthread(){
        return  new thread1();
    }

    public static void main(String[] args) {
        线程调用线程 test=new 线程调用线程();
        thread1 getthread = test.getthread();
        new Thread(getthread).start();

        thread2 getthread2 = test.getthread2();
        new Thread(getthread2).start();
    }
}
