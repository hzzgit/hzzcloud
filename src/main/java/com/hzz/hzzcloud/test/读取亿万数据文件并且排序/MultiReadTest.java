package com.hzz.hzzcloud.test.读取亿万数据文件并且排序;

import java.io.File;
import java.io.RandomAccessFile;
import java.net.URL;
import java.util.concurrent.CountDownLatch;

public class MultiReadTest {


    /**
     * 多线程读取文件测试
     *
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        final int DOWN_THREAD_NUM = 10;//起10个线程去读取指定文件
        URL systemResource = ClassLoader.getSystemResource("sort.txt");
        String OUT_FILE_NAME = systemResource.getPath();
         String keywords = "3";
        //jdk1.5线程辅助类，让主线程等待所有子线程执行完毕后使用的类，
        //另外一个解决方案：自己写定时器，个人建议用这个类
        CountDownLatch doneSignal = new CountDownLatch(DOWN_THREAD_NUM);
        RandomAccessFile[] outArr = new RandomAccessFile[DOWN_THREAD_NUM];
        try {
            long length = new File(OUT_FILE_NAME).length();
            System.out.println("文件总长度：" + length + "字节");
            //每线程应该读取的字节数
            long numPerThred = length / DOWN_THREAD_NUM;
            System.out.println("每个线程读取的字节数：" + numPerThred + "字节");
            //整个文件整除后剩下的余数
            long left = length % DOWN_THREAD_NUM;
            for (int i = 0; i < DOWN_THREAD_NUM; i++) {
                //为每个线程打开一个输入流、一个RandomAccessFile对象，

                //让每个线程分别负责读取文件的不同部分
                outArr[i] = new RandomAccessFile(OUT_FILE_NAME, "rw");
                if (i != 0) {
//
//                    isArr[i] = new FileInputStream("d:/勇敢的心.rmvb");
                    //以指定输出文件创建多个RandomAccessFile对象

                }
                if (i == DOWN_THREAD_NUM - 1) {
//                    //最后一个线程读取指定numPerThred+left个字节
//                  System.out.println("第"+i+"个线程读取从"+i * numPerThred+"到"+((i + 1) * numPerThred+ left)+"的位置");
                    new ReadThread(i * numPerThred, (i + 1) * numPerThred
                            + left, outArr[i], keywords, doneSignal).start();
                } else {
                    //每个线程负责读取一定的numPerThred个字节
//                  System.out.println("第"+i+"个线程读取从"+i * numPerThred+"到"+((i + 1) * numPerThred)+"的位置");
                    new ReadThread(i * numPerThred, (i + 1) * numPerThred,
                            outArr[i], keywords, doneSignal).start();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
//      finally{
//
//      }
        //确认所有线程任务完成，开始执行主线程的操作
        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int count = KeyWordsCount.getCountObject().getCount();
        System.out.println(count);

    }

}