package com.hzz.hzzcloud.test;

import java.util.ArrayList;
import java.util.List;

public class wuliaotime {

    public static void main(String[] args) {

        List<Double> highvalList=new ArrayList<>();
        List<Double> lowvalList=new ArrayList<>();
        List<Boolean> disabledList=new ArrayList<>();
        double lat=26.078501;
        double lon=119.274447;
        int a=(lat+"").length()-(lat+"").indexOf(".")-1;
        int b=(lon+"").length()-(lon+"").indexOf(".")-1;
        System.out.println(a);
        System.out.println(b);

         
    }
}
