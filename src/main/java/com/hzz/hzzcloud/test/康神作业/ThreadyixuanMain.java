package com.hzz.hzzcloud.test.康神作业;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hzz
 * @description：刷新已选面板的线程
 * @date ：2020/8/19 17:38
 */
public class ThreadyixuanMain implements  Runnable {

    private JFrame jFrame=new JFrame();

    /**
     * 如果是面板就传面板，反正就是你要刷新的图片所在面板
     * @param jFrame
     */
    public ThreadyixuanMain(JFrame jFrame) {
        this.jFrame = jFrame;
    }

    @Override
    public void run() {
        while (true){
            //这边就是加载你的面板
            List<String> data=new ArrayList<>();

            //然后将这些数据刷新面板
            System.out.println("已选面板刷新英雄");
            for (int i = 0; i < 5; i++) {
                data.add("英雄"+i);
                System.out.println("英雄"+i);
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
