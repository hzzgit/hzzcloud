package com.hzz.hzzcloud.test.测试下异常是否嵌套;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/2/22 10:39
 */
public class Excetion {

    public void test(){
        try {
            new Exnei().test1();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void main(String[] args) {
        new Excetion().test();
    }
}
