package com.hzz.hzzcloud.test;

import java.io.UnsupportedEncodingException;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/2 15:36
 */
public class 字符串编码 {

    public static void main(String[] args) {
        String name="���ϵ�����";
        byte[] bytes = name.getBytes();
        try {
            String s = new String(bytes, "UTF-8");
            System.out.println(s);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
