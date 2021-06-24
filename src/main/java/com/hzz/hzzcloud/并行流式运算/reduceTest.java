package com.hzz.hzzcloud.并行流式运算;

import com.hzz.hzzcloud.并行流式运算.vo.MapVo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：hzz
 * @description：进行集合的相加相减等计算操作
 * @date ：2021/6/23 14:24
 */
public class reduceTest {

    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(11, 33, 33, 8, 4, 1);

        //对整个集合进行加操作
        MapVo mapVo = integers.stream().map(new reduceTest()::get)
                .reduce((v1, v2) -> {
                    v1.setId(v1.getId()+v2.getId());
                    v1.setName(v1.getName()+v2.getName());
                    return v1;
                }).orElse(new MapVo());

        integers.stream().map(new reduceTest()::get)
                .forEachOrdered(
                        p->
                                System.out.println(p)
                );

        Double collect = integers.stream().collect(Collectors.averagingDouble(p->p));
        Double collect1 = integers.stream().map(new reduceTest()::get).collect(Collectors.averagingDouble(MapVo::getId));
        System.out.println(mapVo);
    }


    public MapVo get(Integer integer){
        MapVo mapVo = new MapVo();
        mapVo.setId(integer);
        mapVo.setName("你好"+integer);
        return mapVo;
    }
}
