package com.hzz.hzzcloud.学习.父子类构造方法应用;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/5/18 15:46
 */
public class fuClass {

    private String name ;

    public fuClass(String name) {
        this.name = name;
        System.out.println("我是父类构造，带参数");
    }

    public fuClass() {
        System.out.println("我是父类构造");
    }
}
