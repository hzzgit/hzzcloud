package com.hzz.hzzcloud.并行流式运算;

import com.hzz.hzzcloud.并行流式运算.vo.MapVo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/11 14:24
 */
public class groupingbyTest {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(11, 33, 33, 8, 4, 1);
        List<List<MapVo>> collect = integers.parallelStream()
                .map(new groupingbyTest()::get)
                .collect(Collectors.groupingByConcurrent(mapVo -> mapVo.getId()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey() == 33)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
       // collect.forEach(p-> p.forEach(p1-> System.out.println(p1.toString())));



        integers.parallelStream()
                .map(new groupingbyTest()::get)
                .forEach(p-> System.out.println(p));


    }

    public MapVo get(Integer integer){
        MapVo mapVo = new MapVo();
        mapVo.setId(integer);
        mapVo.setName("你好"+integer);
        return mapVo;
    }
}
