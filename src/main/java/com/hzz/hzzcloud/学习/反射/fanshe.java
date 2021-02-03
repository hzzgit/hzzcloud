package com.hzz.hzzcloud.学习.反射;

import java.lang.reflect.Field;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/2/3 17:23
 */
public class fanshe {
    public static void main(String[] args) {
        try {
            Class<?> aClass = Class.forName("com.hzz.hzzcloud.学习.反射.Cat");
            Field name = aClass.getDeclaredField("name");

            Object o = aClass.newInstance();
            name.setAccessible(true);
            name.set(o,"test");

            Object o1 = name.get(o);
            System.out.println(o1);
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
