package com.hzz.hzzcloud.util.Lists工具类;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/9/28 14:43
 */
public class 集合自动分页 {
    public static void main(String[] args) {
        List<Integer> integerList=new ArrayList<>();
        for (int i = 0; i < 1011; i++) {
                    integerList.add(i);
        }
        List<List<Integer>> partition = Lists.partition(integerList, 200);
        for (List<Integer> integers : partition) {
            System.out.println(integers.size());
        }
        List<Integer> reverse = Lists.reverse(integerList);
        System.out.println(1);
    }
}
