package com.hzz.hzzcloud.并行流式运算;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/11 11:52
 */

/**
 * parallelStream 采用多线程运算
 * stream则是单线程
 */
public class parallelStreamTest {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 12000; i++) {
            list.add(i);
        }
        long s = System.currentTimeMillis();   //获取开始时间
        list.parallelStream().
//                sorted(Comparator.comparing(Integer::intValue).reversed()).
                forEach(p -> {
                    System.out.println(p+",线程为:"+Thread.currentThread().getId());
                });
        long e = System.currentTimeMillis(); //获取结束时间
        System.out.println("用时：" + (e - s) + "ms");

        long s1 = System.currentTimeMillis();   //获取开始时间
        list.stream().
                //sorted(Comparator.comparing(Integer::intValue).reversed()).
                forEach(p -> {
                    //System.out.println(p+",线程为:"+Thread.currentThread().getId());
                });
        long e1 = System.currentTimeMillis(); //获取结束时间
        System.out.println("用时2：" + (e1 - s1) + "ms");

    }

}

