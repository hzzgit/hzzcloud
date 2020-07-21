package com.hzz.hzzcloud.test.文件流;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/20 17:50
 */
public class 文件复制 {

    public static void main(String[] args) {
        try {
            FileInputStream fileInputStream=new FileInputStream("D:\\hzzmysoft\\学习文档\\Kafkaapiwendang_downcc.zip");
            FileOutputStream fileOutputStream=new FileOutputStream("C:\\Users\\fxft\\Desktop\\Kafkaapiwendang_downcc2.zip");
            byte[] data=new byte[1024];
            int len=0;
            while ((len=fileInputStream.read(data))>0){
                fileOutputStream.write(data);
            }
            fileOutputStream.flush();
            fileInputStream.close();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
