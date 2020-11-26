package com.hzz.hzzcloud.test.java8新特性.interfacefunciton;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/24 14:20
 */
public class main {

    public applyinter test(){
        return dbtb->{
            dbtb.setName("我就是个名字。我真的没干嘛，我就是单纯的想要测试下快速返回一个接口对应的类的语法罢了，真的");
            System.out.println(1);
        };
    }

    public static void main(String[] args) {

        main main=new main();
        applyinter  test = main.test();
        DbTb dbTb=new DbTb();

        test.test(dbTb);

        String name = dbTb.getName();
        System.out.println(name+"函数式接口其实就是Lamda表达式");
    }
}
