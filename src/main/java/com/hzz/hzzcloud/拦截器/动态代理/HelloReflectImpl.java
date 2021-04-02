package com.hzz.hzzcloud.拦截器.动态代理;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/2/26 10:52
 */
public class HelloReflectImpl implements  HelloReflect {
    @Override
    public void helloReflect(String name,int age) {
        System.out.println("hello reflect,name:"+name+",age:"+age);
    }

    @Override
    public void helloReflect2() {
        System.out.println("hello reflect2");
    }
}
