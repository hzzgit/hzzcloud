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

        Long msg=new Long(1603444771876L);
        msg=msg/1000*1000;
        Long msgend=new Long(1603444778876L);
        msgend=(msgend+1000)/1000*1000;

        System.out.println(msg);
        System.out.println(msgend);






    }
}
