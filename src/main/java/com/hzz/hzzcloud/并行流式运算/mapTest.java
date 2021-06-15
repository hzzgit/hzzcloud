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
public class mapTest {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(11, 33, 22, 8, 4, 1);
        integers.parallelStream()
                .map(new mapTest()::get)
                .sorted(Comparator.comparing(MapVo::getId))
                .collect(Collectors.toList())
                .forEach(p-> System.out.println(p));
    }

    public MapVo get(Integer integer){
        MapVo mapVo = new MapVo();
        mapVo.setId(integer);
        return mapVo;
    }
}
