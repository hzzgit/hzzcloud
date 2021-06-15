package com.hzz.hzzcloud.并行流式运算.flatMap;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/11 16:40
 */
public class flatMapTest2 {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(11, 33, 33, 8, 4, 1);
        List<List<String>> collect = integers.parallelStream()
                .map(new flatMapTest2()::getList)
                .collect(Collectors.toList());

        List<String> collect1 = collect.parallelStream().flatMap(p -> p.stream())
                .distinct()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());


        System.out.println(1);


    }

    public List<String> getList(Integer id) {
        List<String> strings = Arrays.asList(String.valueOf(id), "2", "3",null);
        return strings;
    }
}

