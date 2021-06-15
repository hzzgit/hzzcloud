package com.hzz.hzzcloud.并行流式运算;

import com.google.common.collect.Maps;
import com.hzz.hzzcloud.并行流式运算.vo.MapVo;

import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/11 15:00
 */
public class toMapTest {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(11, 33, 33, 8, 4, 1);
        List<Integer> collect3 = integers.parallelStream()
                .sorted(Comparator.comparing(Integer::intValue).reversed())
                .collect(Collectors.toList());

        for (Integer integer : collect3) {
            System.out.println(integer);
        }


        Map<Integer, MapVo> collect1 = integers.stream()
                .distinct()
                .sorted(Comparator.comparing(Integer::intValue).reversed())
                .map(new toMapTest()::get)
                .collect(Collectors.toMap(MapVo::getId, Function.identity()));


        Map<Integer, MapVo> collect = integers.stream().
                distinct().
                map(new toMapTest()::get)
                .collect(Collectors.toMap(MapVo::getId, t -> t));

        Map<Integer, String> collect2 = integers.stream().
                distinct().
                map(new toMapTest()::get)
                .
                collect(Collectors.toMap(MapVo::getId, MapVo::getName));


        HashMap<Object, Object> objectObjectHashMap = Maps.newHashMapWithExpectedSize(integers.size());


    }


    public MapVo get(Integer integer){
        MapVo mapVo = new MapVo();
        mapVo.setId(integer);
        mapVo.setName("你好"+integer);
        return mapVo;
    }


}
