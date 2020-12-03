package com.hzz.hzzcloud.test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/2 11:38
 */
public class 测试下map的集合循环 {
    public static void main(String[] args) {
        int parkingNum=0;
        Map<Long, Integer> vedepParkIngCountMap = new HashMap<>();
        for (Integer value : vedepParkIngCountMap.values()) {
            parkingNum=parkingNum+value;
        }
        System.out.println(parkingNum);
    }
}
