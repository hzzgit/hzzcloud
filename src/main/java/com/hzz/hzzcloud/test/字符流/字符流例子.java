package com.hzz.hzzcloud.test.字符流;

import java.io.*;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/21 9:23
 */
public class 字符流例子 {
    public static void main(String[] args) {
        try {
            InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream("C:\\Users\\fxft\\Desktop\\1.txt"));
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            StringBuilder stringBuilder=new StringBuilder();
            String te="";

            while ((te=bufferedReader.readLine())!=null){
                stringBuilder.append(te+"\n");
            }
            System.out.println(stringBuilder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
