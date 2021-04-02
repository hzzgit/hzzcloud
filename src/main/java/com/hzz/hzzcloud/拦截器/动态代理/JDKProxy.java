package com.hzz.hzzcloud.拦截器.动态代理;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/2/26 10:49
 */
public class JDKProxy implements InvocationHandler {
    private Object target;
    public JDKProxy(Object target) {
        this.target=target;
    }

    public <T> T getProxy(){
        return (T) Proxy.newProxyInstance(target.getClass().getClassLoader(),target.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("输出hello reflect之前");
        if(args!=null&&args[0]!=null){
            String name= String.valueOf(args[0]);
            name=name+"这边是动态代理加入进去的，修改了传入的参数值";
            args[0]=name;
        }

        if(args!=null&&args[1]!=null){
               int age= (int) args[1];
               age=age+2211111;
            args[1]=age;
        }

        Object result=method.invoke(target,args);
        System.out.println("输出hello reflect之后");
        return result;
    }
}
