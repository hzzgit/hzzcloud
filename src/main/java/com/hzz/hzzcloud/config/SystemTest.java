package com.hzz.hzzcloud.config;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/3 10:53
 */
public class SystemTest {

    public static void main(String[] args) {
        String property = System.getProperty("os.name");
        System.out.println(property);
    }
}
