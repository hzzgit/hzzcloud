package com.hzz.hzzcloud.并行流式运算;

import com.hzz.hzzcloud.并行流式运算.vo.MapVo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/21 9:22
 */
public class CollectorsMapping {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 3, 44, 22, 11,44);
        Map<Integer, List<String>> collect = integers.stream().map(CollectorsMapping::get)
                .collect(Collectors.groupingBy(MapVo::getId,
                        Collectors.mapping(MapVo::getName, Collectors.toList())
                ));
        System.out.println();

    }

    public static MapVo get(Integer a){
        MapVo mapVo=MapVo.builder().id(a).name("名字"+a).build();
        return mapVo;
    }
}
