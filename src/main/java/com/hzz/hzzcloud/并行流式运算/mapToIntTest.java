package com.hzz.hzzcloud.并行流式运算;

import java.util.Arrays;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/16 9:39
 */
public class mapToIntTest {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(11, 33, 22, 8, 4, 1);
        int asInt = integers.parallelStream().mapToInt(Integer::intValue).max().getAsInt();
        long count = integers.parallelStream().map(Integer::intValue).count();
        Integer max = integers.parallelStream().map(Integer::intValue).max(Integer::compareTo).get();
        Integer min = integers.parallelStream().map(Integer::intValue).min(Integer::compareTo).get();
        int sum = integers.parallelStream().mapToInt(Integer::intValue).sum();
        System.out.println(asInt);
        System.out.println(sum);
    }
}
