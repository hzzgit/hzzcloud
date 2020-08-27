package com.hzz.hzzcloud.util.lamda表达式回调函数等;

import java.util.function.Consumer;
import java.util.function.DoubleFunction;
import java.util.function.Function;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/24 17:50
 */
public class Supplier {
    public String testsupplier(java.util.function.Supplier<String> test){

        return  test.get();
    }

    public String testfunction(Function<String,String> tst){
        String apply = tst.apply("212");
        return apply;
    }


    public Double testfunction(DoubleFunction<Double> tst){
        Double apply = tst.apply(11.1);
        return apply;
    }
    public void testcom(Consumer<String> tst){
      tst.accept("212");

    }


    public static void main(String[] args) {
        String testsupplier = new Supplier().testsupplier(() -> {
            return "1212";
        });


        new Supplier().testcom(s -> {
            System.out.println(s+"测试下"); ;
        });
        System.out.println(testsupplier);
    }
}
