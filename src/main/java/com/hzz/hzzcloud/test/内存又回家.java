package com.hzz.hzzcloud.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/9/7 9:52
 */
public class 内存又回家 {
    public static void main(String[] args) {
        Integer integer = Integer.valueOf(1);
        Boolean aTrue = Boolean.TRUE;
        Long aLong = Long.valueOf(1);
        StringBuilder stringBuilder=new StringBuilder();

        StringBuffer stringBuffer=new StringBuffer();
        stringBuffer.append("112");


        String [] startList=new String[]{"1","2","3"};
        String [] endList=new String[3];
        System.arraycopy(startList,0,endList,0,3);
        System.out.println(1);

        List<Integer> integerList=new ArrayList<>();
        List<Integer> integerList2=new ArrayList<>();

        integerList2.add(1);
        integerList2.add(1);
        integerList2.add(1);
        integerList2.add(1);
        integerList.addAll(integerList2);
        integerList2=null;
        System.out.println(1);




    }
}
