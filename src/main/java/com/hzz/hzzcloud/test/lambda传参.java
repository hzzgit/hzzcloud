package com.hzz.hzzcloud.test;

import java.util.function.Consumer;
import java.util.function.Function;

public class lambda传参 {


    public static<T> T te(Function<String,T> function,String co){

        T apply = function.apply(co);
        return  apply;
    }

    public static <T> void te2(Consumer<String> consumer){


    }

    public static void main(String[] args) {
        Integer te = te(p -> {
            Integer c=0;
            try {
                c=Integer.valueOf(p)*2121;
            } catch (NumberFormatException e) {

            }
            return c;
        },"33");
        System.out.println(te);
    }
}
