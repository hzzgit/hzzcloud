package com.hzz.hzzcloud.util.线程池.内部类测试;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/15 15:50
 */
public class 内部类调用里面的参数 {
    private int a = 1;
    private int a1 = 2;

    private class enty implements entyinteger {
        @Override
        public int get() {
            return a;
        }
    }

    public static class enty2{
        public int get(){
            return  new 内部类调用里面的参数().a1;
        }
    }

    public entyinteger getenty(){
         entyinteger en= new enty();
         return  en;
    }

    public static void main(String[] args) {

        内部类调用里面的参数 s=new 内部类调用里面的参数();


        entyinteger getenty = s.getenty();
        int i = getenty.get();
        System.out.println(i);
    }
}
