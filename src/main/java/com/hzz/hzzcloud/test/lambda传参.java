package com.hzz.hzzcloud.test;

import java.util.function.Consumer;
import java.util.function.Function;

public class lambda传参 {


    public static<T> T te(Function<T,T> function,T co){

        T apply = function.apply(co);
        return  apply;
    }

    public static <T> void te2(Consumer<String> consumer){
        consumer.accept("ma,e");

    }

    public static void main(String[] args) {

        lambda传参.te2(te->{
            String substring = te.substring(0, 1);
            System.out.println(substring);
        });
        int name= lambda传参.te(te->{
            int substring = te+1;

            return substring;
        },1);

        System.out.println(name);
    }
}
