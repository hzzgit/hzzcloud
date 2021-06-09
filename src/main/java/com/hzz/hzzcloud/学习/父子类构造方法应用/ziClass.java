package com.hzz.hzzcloud.学习.父子类构造方法应用;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/5/18 15:47
 */
public class ziClass extends  fuClass {

    public ziClass(String name) {
        super(name);
    }

    public ziClass() {

    }

    public static void main(String[] args) {
        ziClass z=new ziClass("名字");
    }
}
