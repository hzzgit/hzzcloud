package com.hzz.hzzcloud.test.集合反转;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/1/28 16:34
 */
public class Collectionsreverse {
    public static void main(String[] args) {
        List<String> searchSubmeter =new ArrayList<>();
        searchSubmeter.add("1");
        searchSubmeter.add("2");
        searchSubmeter.add("3");
        searchSubmeter.add("4");

        Collections.reverse(searchSubmeter);





        List<Integer> ids=new ArrayList<>();
        ids.add(1);
        ids.add(13);
        ids.add(131);
        ids.add(132);
        ids.add(1113);
        ids.add(4);
        ids.add(5);

        Collections.reverse(ids);


        System.out.println(1);
    }
}
