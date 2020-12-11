package com.hzz.hzzcloud.公司临时代码.报文解析;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/11 16:34
 */
public class JT_0200mY {

        private int alarmstate;
        private int status;
        private int latitude;
        private int longtitu;
        private short high;
        private short speed;
        private byte fangxiang;
        private String time;

    public static void main(String[] args) {
         int status=-1073806268;

        String s = Integer.toBinaryString(status);
        System.out.println(s);


        String hex="10111111111111110000010001000100";
        Long i = Long.parseLong("10111111111111110000010001000100", 2);

        System.out.println(i);
    }



}
