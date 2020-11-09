package com.hzz.hzzcloud.公司临时代码.用来插入对应厂家型号和设备编码的通道号.夺取源码;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/6 17:05
 */
public class main {

    public static void main(String[] args) {
        String path="D:\\hzzmysoft\\cloud源码\\net";
        File file=new File("D:\\hzzmysoft\\cloud源码\\net\\fxft\\cloud\\aliyun\\AliyunConfig.class");
        try {
            BufferedReader in = new BufferedReader(new FileReader(file));
            String str;
            while ((str = in.readLine()) != null) {
                System.out.println(str);
            }
            System.out.println(str);
        } catch (IOException e) {
        }

    }

    /**
     * TODO:递归扫描指定文件夹下面的指定文件
     *
     * @return ArrayList<Object>
     * @throws FileNotFoundException
     */
    public static List<String> scanFilesWithRecursion(String folderPath) {

        try {
            List<String> scanFiles = new ArrayList<>();
            File directory = new File(folderPath);
            if (!directory.isDirectory()) {
                throw new FileNotFoundException('"' + folderPath + '"' + " input path is not a Directory , please input the right path of the Directory. ^_^...^_^");
            }
            if (directory.isDirectory()) {
                File[] filelist = directory.listFiles();
                for (int i = 0; i < filelist.length; i++) {
                    /**如果当前是文件夹，进入递归扫描文件夹**/
                    if (filelist[i].isDirectory()) {
                        /**递归扫描下面的文件夹**/

                        scanFilesWithRecursion(filelist[i].getAbsolutePath());
                    }
                    /**非文件夹**/
                    else {
                        String filename=filelist[i].getName();
                        String filetype = filename.substring(filename.lastIndexOf("."));
                        if(".ftl".equalsIgnoreCase(filetype)){
                            scanFiles.add(filelist[i].getName());
                        }
                    }
                }
            }
            return scanFiles;
        } catch (FileNotFoundException e) {
            return null;
        }
    }

}
