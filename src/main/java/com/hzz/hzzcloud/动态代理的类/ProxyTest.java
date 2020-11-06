package com.hzz.hzzcloud.动态代理的类;

import com.hzz.hzzcloud.test.Student;
import com.hzz.hzzcloud.test.StudentInter;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/5 9:22
 */
public class ProxyTest {
    public static void main(String[] args) {
        Student student =new Student();
        student.setAge(1L);
        Class<Student> aClass = Student.class;
        ClassLoader classLoader = aClass.getClassLoader();
        //获取被代理对象说实现的所有接口
        Class<?>[] interfaces = aClass.getInterfaces();
        //新建代理对象,里面参数需要(类加载器,一个对象所实现的接口,InvocationHandler接口类的对象)
        Object o = Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("之前");
                Object invoke = method.invoke(student, args);
                System.out.println("之中"+invoke);
                System.out.println("之后");
                return invoke;
            }
        });
        StudentInter student2= (StudentInter) o;

        long age = student2.getAge();
        System.out.println(age);
    }
}
