package com.hzz.hzzcloud.拦截器.动态代理;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/2/26 10:49
 */
public class TestProxy {

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");//将生成的代理类的字节码文件保存到本地，后面分析原理会用到
        HelloReflect helloReflect1=new HelloReflectImpl();
        Class<?>[] arr=helloReflect1.getClass().getInterfaces();
        for(Class clazz:arr){
            System.out.println(clazz.getName());
        }
        HelloReflect helloReflect2=new JDKProxy(helloReflect1).getProxy();
        helloReflect2.helloReflect("测试",10);
        helloReflect2.helloReflect2();
    }
}
