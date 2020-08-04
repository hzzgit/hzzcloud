package com.hzz.hzzcloud.test;

import com.ltmonitor.jt808.tool.Tools;
import com.ltmonitor.util.StringUtil;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/4 14:22
 */
public class 数值转二进制 {
    public static void main(String[] args) {
        Integer  a=111;
        String s = Integer.toBinaryString(a);
        Integer b=0;
        b=Integer.parseInt(s,2);
        String s1 = Tools.ToHexString(b, 4);

        s = StringUtil.leftPad(s, 32, '0');
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            int m = 31;
            int c = chars[m - i] - 48;
            System.out.println(c==1 ? true : false);
        }
        System.out.println(s);
    }
}
