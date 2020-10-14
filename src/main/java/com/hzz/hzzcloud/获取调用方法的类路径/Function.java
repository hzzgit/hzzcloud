package com.hzz.hzzcloud.获取调用方法的类路径;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/10/14 9:38
 */
public class Function {

    public void test(){
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String className = stackTrace[2].getClassName();
        String methodName = stackTrace[2].getMethodName();
        System.out.println(className);
        System.out.println(methodName);
    }
}
