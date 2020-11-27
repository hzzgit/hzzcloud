package com.hzz.hzzcloud.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/27 11:05
 */
public class 读取文本错误码 {
    public static void main(String[] args) {

        try {
            File file=new File("C:\\Users\\fxft\\Desktop\\cuowuma.txt");
            FileInputStream fileInputStream=new FileInputStream(file);
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream,"UTF-8");
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line = "";
            List<Map<String,String>> data=new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split("\t");
                System.out.println(line);
                String s1 = s[1];
                String s2 = s[2];
                String s3 = s[3];
                Map<String,String> d1=new HashMap<>();
                d1.put("code",s1);
                d1.put("message",s2);
                d1.put("result",s3);
                data.add(d1);
            }
            System.out.println(data.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
