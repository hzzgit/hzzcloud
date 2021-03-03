package com.hzz.hzzcloud.米哈游;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/3/2 16:41
 */
public class A1 {
    private String name="test";

    public static void main(String[] args) {
            A1 a=new A1();
            a.name="33";
        Object clone = null;
        try {
            clone = a.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        System.out.println(clone);

    }
}
