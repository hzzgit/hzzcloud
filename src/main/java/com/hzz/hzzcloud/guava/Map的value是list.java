package com.hzz.hzzcloud.guava;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/1 17:18
 */
public class Map的value是list {
    public static void main(String[] args) {
        Multimap<String,Integer> map = ArrayListMultimap.create();
        map.put("aa", 1);
        map.put("aa", 2);
        for (Integer integer : map.get("aa")) {
            System.out.println(integer);
        }

    }
}
