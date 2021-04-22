package com.hzz.hzzcloud.设计模式;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 9:53
 */
public class a {

    public static void main(String[] args) throws FileNotFoundException {
        InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(new File("d://TEST")));
    }
}
