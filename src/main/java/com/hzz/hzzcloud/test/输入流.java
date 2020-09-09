package com.hzz.hzzcloud.test;

import java.io.*;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/3 15:11
 */
public class 输入流 {
    public static void main(String[] args) {
        File file = new File("D://test.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            try {
                outputStreamWriter.write("测试测试");
                outputStreamWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    outputStreamWriter.close();
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
