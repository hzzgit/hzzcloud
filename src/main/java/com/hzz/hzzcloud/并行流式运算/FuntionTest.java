package com.hzz.hzzcloud.并行流式运算;

import java.util.function.Function;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/11 14:10
 */
public class FuntionTest {


    public static void main(String[] args) {

        System.out.println(new FuntionTest().get("2", p -> Integer.parseInt(p)));

        System.out.println(new FuntionTest().get("3", Integer::parseInt));
    }

    public Integer get(String name,Function<String,Integer> te){
        Integer apply = te.apply(name);
        return apply;
    }
}
