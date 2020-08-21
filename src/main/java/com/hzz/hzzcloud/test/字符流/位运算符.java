package com.hzz.hzzcloud.test.字符流;

import java.util.Random;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/20 14:54
 */
public class 位运算符 {
    public static void main(String[] args) {
        Random random=new Random();
        for (int i = 0; i < 10000; i++) {
            int i1 = random.nextInt(3);
            System.out.println(i1);
        }

    }
}
