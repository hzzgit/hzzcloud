package com.hzz.hzzcloud.test.康神作业;

import net.fxft.cloud.metrics.Tps;

import java.io.IOException;
import java.util.Random;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/9/28 20:09
 */
public class 文字游戏 {

    public static void main(String[] args) throws IOException {

        Tps tps=new Tps();
        Tps tps2=new Tps();
        Tps tps3=new Tps();
        String[] data = new String[]{"锤子", "剪刀", "布"};
        Random random = new Random();
        for (int i1 = 0; i1 < 100; i1++) {
            new Thread(() -> {
                while (true) {
                    //Scanner sc = new Scanner(System.in);
                 //   System.out.println("请输入：1,锤子，2，剪刀，3布");
                    //    int name = sc.nextInt();
                    int name = random.nextInt(3) + 1;
                    if (name > 3 || name < 1) {
                        continue;
                    }
                    tps2.inc();
                    int i = random.nextInt(3) + 1;
                    if (i == name) {
                       // System.out.println("平手");
                    } else if (i == 1 && name == 2) {
                       // System.out.println("你输了");
                    } else if (i == 2 && name == 3) {
                       // System.out.println("你输了");
                    } else if (i == 3 && name == 1) {
                      //  System.out.println("你输了");
                    } else {
                        tps.inc();
                      //  System.out.println("你赢了");
                    }
                   // System.out.println("你出的是" + data[name - 1] + ",电脑出的是" + data[i - 1]);

                }
            }).start();
        }

             new Thread(()->{
                             while (true){
                                 System.out.println("胜:"+tps.getTps()+",总："+tps2.getTps());
                                 try {
                                     Thread.sleep(1000);
                                 } catch (InterruptedException e) {
                                     e.printStackTrace();
                                 }
                             }
                     }).start();



    }
}
