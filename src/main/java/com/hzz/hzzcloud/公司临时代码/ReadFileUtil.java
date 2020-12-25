package com.hzz.hzzcloud.公司临时代码;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Consumer;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/25 15:17
 */
public class ReadFileUtil {

    public static void readfile(Consumer<String> data){
        String filepath="C:\\Users\\fxft\\Desktop\\platenototime.txt";
        File file=new File(filepath);
        BufferedReader reader = null;

        boolean arg=true;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                data.accept(tempStr);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }
}
