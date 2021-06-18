package com.hzz.hzzcloud.并行流式运算;

import com.hzz.hzzcloud.并行流式运算.vo.MapVo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/15 11:28
 */
public class filterTest {
    public static void main(String[] args) {

        List<Integer> integers = Arrays.asList(1, 23, 45, 77);
        List<MapVo> collect = integers.parallelStream().map(p -> {
            return new filterTest().get(p);
        }).collect(Collectors.toList());


        try {

            collect.stream().filter(p-> p.getValidFlag()).findAny().orElseThrow(() -> new Exception("没有该系统权限"));
            MapVo mapVo = collect.stream().filter(p -> p.getValidFlag()).findAny().orElse(new MapVo());
            boolean present = collect.stream().filter(p -> p.getValidFlag()).findAny().isPresent();
            MapVo mapVo1 = collect.stream().filter(p -> p.getValidFlag()).findAny().orElseGet(() -> new filterTest().get(1));
            System.out.println(1);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public MapVo get(Integer id){
        MapVo mapVo=new MapVo();
        mapVo.setId(id);
        mapVo.setName(""+id);
        mapVo.setValidFlag(false);
        if(id==1){
            mapVo.setValidFlag(true);
        }
        return mapVo;
    }

}
