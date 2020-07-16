package com.hzz.hzzcloud.util.线程池;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/15 10:42
 */
public class 基础循环 {
    public static void main(String[] args) {
        ConcurrentHashMap concurrentHashMap=new ConcurrentHashMap();
        concurrentHashMap.put(2121,313);
        concurrentHashMap.get(1);
        String[]datas=new String[]{"1","2"};
//        for (int i=1;i<=1;) {
//            System.out.println(datas);
//        }
        System.out.println(99&100);
        HashMap ce=new HashMap();
        ce.put(321421424,2);
        ce.put(3,2);
        ce.put(2,2);
        ce.put(4,2);
        Set set = ce.entrySet();

        Integer a=321421424;
        int i = a.hashCode();
       int b= i>>>16;
      int c= i^b;
        ce.forEach((p,v)->{
            System.out.println(p);
        });
    }
}
