package com.hzz.hzzcloud.并行流式运算;

import java.util.function.Predicate;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/11 14:30
 */
public class PredicateTest {

    public static void main(String[] args) {

        boolean predicate = new PredicateTest().predicate(1, na -> na > 1);
        System.out.println(predicate);
    }


    public boolean predicate(Integer age, Predicate<Integer> predicate) {
        return predicate.test(age);
    }

}
