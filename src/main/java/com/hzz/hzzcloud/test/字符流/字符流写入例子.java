package com.hzz.hzzcloud.test.字符流;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/21 11:17
 */
public class 字符流写入例子 {
    public static void main(String[] args) {
        try {
            PrintWriter printWriter=new PrintWriter(new FileOutputStream("C:\\Users\\fxft\\Desktop\\1.txt"));
            printWriter.println("1`12");
            printWriter.println("1`12");
            printWriter.println("1`12");
            printWriter.flush();
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
