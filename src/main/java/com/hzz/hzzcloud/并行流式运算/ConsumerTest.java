package com.hzz.hzzcloud.并行流式运算;

import java.util.function.Consumer;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/11 14:56
 */
public class ConsumerTest {
    public static void main(String[] args) {
        new ConsumerTest().test(22,p-> System.out.println(p));
        new ConsumerTest().test(22, System.out::println);

        //如果是Consumer，加不加{ } 都一样，加了就是好看点
        new ConsumerTest().test(22,p->{
            System.out.println(p);
        });

    }

    public void test(Integer age,Consumer <Integer> consumer){
        consumer.accept(age);
    }
}
