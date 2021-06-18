package com.hzz.hzzcloud.并行流式运算;

import com.hzz.hzzcloud.并行流式运算.vo.MapVo;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/11 14:13
 */
public class mapTest2 {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(11, 33, 22, 8, 4, 1);
        integers.parallelStream()
                .map(new mapTest2()::get)
                .sorted(Comparator.comparing(MapVo::getId))
                .collect(Collectors.toList())
                .forEach(p-> System.out.println(p));
        String collect = integers.parallelStream()
                .map(new mapTest2()::get)
                .map(MapVo::getName)
                .filter(p->p.equalsIgnoreCase("测试"))
                .distinct()
                .collect(Collectors.joining(","));

        Integer 额 = integers.parallelStream().filter(p -> p > 1).findAny().orElseThrow(() -> new RuntimeException("额"));

        System.out.println(collect);
    }

    public MapVo get(Integer integer){
        MapVo mapVo = new MapVo();
        mapVo.setId(integer);
        mapVo.setName("测试");
        return mapVo;
    }
}
