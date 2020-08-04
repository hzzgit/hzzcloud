package com.hzz.hzzcloud.byteUtils;

import com.ltmonitor.jt808.tool.Tools;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/3 14:14
 */
public class 字节占用 {
    public static void main(String[] args) {
        byte a= (byte) 250;
        int b =a;
        System.out.println(b);

        String s = Tools.ToHexString(Long.parseLong("0"), 1);
        System.out.println(s);
    }
}
