package com.hzz.hzzcloud.并行流式运算;

import com.hzz.hzzcloud.并行流式运算.vo.MapVo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/11 14:24
 */
public class groupingbyTest {

    private AtomicInteger count=new AtomicInteger(1);

    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(11, 33, 33, 8, 4, 1);
        groupingbyTest groupingbyTest = new groupingbyTest();
        List<MapVo> collect2 = integers.stream().map(groupingbyTest::get)
                .collect(Collectors.toList());
        //这个的分组就是按照顺序的链表方法进行的，像下面这个就是先按照name分组，然后再从分组中根据id计算总数
        ConcurrentMap<String, ConcurrentMap<Integer, Integer>> collect3 = collect2.stream().
                collect(Collectors.groupingByConcurrent(MapVo::getName, Collectors.groupingByConcurrent(MapVo::getId, Collectors.summingInt(MapVo::getId))));

        Map<Integer, Double> collect4 = collect2.stream().collect(Collectors.groupingBy(MapVo::getId, Collectors.averagingInt(MapVo::getId)));
        Map<Integer, List<MapVo>> collect5 = collect2.stream().collect(Collectors.groupingBy(MapVo::getId));


        ConcurrentMap<Integer, List<MapVo>> collect1 = integers.stream()
                .map(groupingbyTest::get)
                .collect(Collectors.groupingByConcurrent(mapVo -> mapVo.getId()));

        //超过3行，idea会进行提示，parallelStream这种并行流计算，
        // 如果要做处理，要用Concurrent相关方法保证线程安全
        List<List<MapVo>> collect = integers.stream()
                .map(groupingbyTest::get)
                .collect(Collectors.groupingByConcurrent(mapVo -> mapVo.getId()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getKey() == 33)
                .map(Map.Entry::getValue)
                .collect(Collectors.toList());
       // collect.forEach(p-> p.forEach(p1-> System.out.println(p1.toString())));



        integers.parallelStream()
                .map(groupingbyTest::get)
                .forEach(p-> System.out.println(p));


    }

    public MapVo get(Integer integer){
        MapVo mapVo = new MapVo();
        mapVo.setId(integer);
        mapVo.setName("你好"+integer);
        count.incrementAndGet();
        return mapVo;
    }
}
