package com.hzz.hzzcloud.并行流式运算;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/16 10:05
 */
public class forEachOrdered {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 12000; i++) {
            list.add(i);
        }

        list.parallelStream().forEachOrdered(p->{
            System.out.println(p+",线程为:"+Thread.currentThread().getId());
        });
    }
}
