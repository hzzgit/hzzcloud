package com.hzz.hzzcloud.学习.hash转红黑树;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/3/5 10:06
 */
public class HashMapTreeifyDemo {
    public static void main(String[] args) {
        Map<Key, Integer> map = new HashMap<>();
        for (int i = 0; i < 1000; i++) {
            map.put(new Key(), i);
        }

        // 断点打折这里
        System.out.println(map);
    }


    static class Key {
        @Override
        public int hashCode() {
            return 1;
        }
    }

}
