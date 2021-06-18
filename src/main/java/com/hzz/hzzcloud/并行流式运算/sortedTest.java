package com.hzz.hzzcloud.并行流式运算;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/11 13:53
 */
public class sortedTest {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(11, 33, 22, 8, 4, 1);

        //这种是直接使用List自带的排序，会让这个集合直接发生排序变化
        integers.sort(Integer::compareTo);
        //这种则是使用流式循环计算排序
        integers.parallelStream().sorted(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1-o2;
            }
        }).map(integer -> integer.intValue()).collect(Collectors.toList());

        integers.parallelStream().sorted(Integer::compareTo).
                map(integer -> integer.intValue()).
                collect(Collectors.toList());

        List<Integer> collect = integers.parallelStream().
                sorted(Comparator.comparingInt(Integer::intValue)
                        .reversed()).collect(Collectors.toList());

        List<Integer> collect1 = integers.parallelStream().sorted(Comparator.comparing(Integer::intValue).reversed()).
                map(integer -> integer.intValue()).
                collect(Collectors.toList());
        System.out.println();


    }
}
