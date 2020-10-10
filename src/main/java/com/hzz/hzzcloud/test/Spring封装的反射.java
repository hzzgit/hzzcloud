package com.hzz.hzzcloud.test;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/10/9 15:43
 */
public class Spring封装的反射 {
    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName("com.hzz.hzzcloud.test.JT_8607");
            Object o = aClass.newInstance();
            Field routesCount = aClass.getDeclaredField("routesCount");
            ReflectionUtils.makeAccessible(routesCount);
            routesCount.set(o,(byte)22);
            routesCount.setByte(o,(byte)123);
            System.out.println(1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
