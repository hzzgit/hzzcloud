package com.hzz.hzzcloud.并行流式运算;

import com.hzz.hzzcloud.并行流式运算.vo.MapVo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

/**
 * @author ：hzz
 * @description：！！！hashMap想要用并行流式运算，就必须要转成Set
 * ！！！！！！反正流式运算只支持对于集合
 * @date ：2021/6/11 14:36
 */
public class HashMapToList {

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(11, 33, 22, 8, 4, 1,1);

        integers.parallelStream()
                .distinct()
                .collect(Collectors.toList()).forEach(p-> System.out.println(p));

        ConcurrentMap<Integer, List<MapVo>> collect = integers.parallelStream()
                .map(new HashMapToList()::get)
                .collect(Collectors.groupingByConcurrent(MapVo::getId));

        long count = collect.entrySet()
                .parallelStream()
                .count();
      //  System.out.println(count);
    }

    public MapVo get(Integer integer){
        MapVo mapVo = new MapVo();
        mapVo.setId(integer);
        return mapVo;
    }
}
