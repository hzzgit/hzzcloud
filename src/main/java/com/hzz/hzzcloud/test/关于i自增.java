package com.hzz.hzzcloud.test;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/9/8 11:22
 */
public class 关于i自增 {
    public static void main(String[] args) {
        int i=2;
        i= ++i* i++;
        System.out.println(i);
        int a=0x1231;
        int b=022;
        System.out.println(a);
        System.out.println(b);
        short s=1;
        s+=1;
        s= (short) (s+1);
        System.out.println(s);
        int a1=5;
        System.out.println("value is "+((a1<=5)?10:9.1));


        Long v1=new Long(2121);
        String v2="2121";
        System.out.println(v1.equals(v2));

    }
}
