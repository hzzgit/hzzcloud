package com.hzz.hzzcloud.并行流式运算;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/11 11:52
 */
public class parallelStreamTest {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(3, 1, 2, 11, 8);
       list.parallelStream().
                sorted(Comparator.comparing(Integer::intValue).reversed()).
                collect(Collectors.toList()).forEach(p-> System.out.println(p));





    }

}

