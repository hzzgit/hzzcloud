package com.hzz.hzzcloud.并行流式运算;

import java.util.function.Supplier;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/11 13:47
 */
public class SupplierTest {
    public static void main(String[] args) {

        Supplier<Integer> supplier=new Supplier<Integer>() {
            @Override
            public Integer get() {
                return new Integer(1);
            }
        };
        Supplier<String> supplier1=String::new;

        Supplier<String> supplier2=()->new String();


    }
}
