package com.hzz.hzzcloud.freemarkcustom;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/27 11:40
 */
public class FreeMarkbyCustomMain {

    public static void main(String[] args) {

        try {
            File file=new File("C:\\Users\\fxft\\Desktop\\cuowuma.txt");
            FileInputStream fileInputStream=new FileInputStream(file);
            InputStreamReader inputStreamReader=new InputStreamReader(fileInputStream,"UTF-8");
            BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
            String line = "";
            List<ColmunVo> data=new ArrayList<>();
            while ((line = bufferedReader.readLine()) != null) {
                String[] s = line.split("\t");
                String s1 = s[1];
                String s2 = s[2];
                String s3 = s[3];
                ColmunVo colmunVo=new ColmunVo();
                colmunVo.setCode(s1);
                colmunVo.setMessage(s2);
                colmunVo.setResult(s3);
                data.add(colmunVo);
            }
            TableBase tableBase=new TableBase();
            tableBase.setColuvos(data);

            FreeMarkbyCustom freeMarkbyCustom = new FreeMarkbyCustom();
            freeMarkbyCustom.readTable(tableBase);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
