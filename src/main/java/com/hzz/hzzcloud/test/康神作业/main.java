package com.hzz.hzzcloud.test.康神作业;

import javax.swing.*;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/19 17:42
 */
public class main {



    public static void main(String[] args) {
         JFrame jFrame=new JFrame();

         //初始化待选面板刷新线程
        ThreaddaixuanMain threaddaixuanMain=new ThreaddaixuanMain(jFrame);

        //初始化已选面板刷新线程
        ThreadyixuanMain threadyixuanMain=new ThreadyixuanMain(jFrame);

        Thread thread = new Thread(threaddaixuanMain);
        Thread thread2 = new Thread(threadyixuanMain);

        thread.start();
        thread2.start();

    }
}
