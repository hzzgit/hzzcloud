package com.hzz.hzzcloud.test.测试线程缓存的map会不会根据迭代器进行控制;


/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/10/19 14:42
 */
public class Client {


    private String name="";


    public void close(){
        isuse=false;
    }

    public Client(String name) {
        this.name = name;
    }

    private Boolean isuse=true;

    public void init(){
                 new Thread(()->{
                                 while (isuse){
                                     System.out.println("这个线程一直在执行:"+name);
                                     try {
                                         Thread.sleep(1000);
                                     } catch (InterruptedException e) {
                                         e.printStackTrace();
                                     }
                                 }
                         }).start();

    }
}
