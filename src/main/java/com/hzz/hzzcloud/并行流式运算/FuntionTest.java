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

        //这种语法相当于让 "3" 执行 Integer.parseInt
        System.out.println(new FuntionTest().get("3", Integer::parseInt));

        //有加 {} 括号的话，就要自己写return ，如果没有就自动return
        System.out.println(new FuntionTest().get("4", p->{ return  Integer.parseInt(p);}));
    }

    public Integer get(String name,Function<String,Integer> te){
        Integer apply = te.apply(name);
        return apply;
    }
}
