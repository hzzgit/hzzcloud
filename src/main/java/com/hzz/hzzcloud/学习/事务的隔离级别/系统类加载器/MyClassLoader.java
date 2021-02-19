package com.hzz.hzzcloud.学习.事务的隔离级别.系统类加载器;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/16 14:52
 */
public class MyClassLoader extends  ClassLoader {
    private String path;
    private Set<String> clazzs;

    public MyClassLoader(String path, String[] clazzs) {
        super(null); // 不使用父类实现
        this.clazzs = new HashSet<String>(Arrays.asList(clazzs));
        this.path = path;
        onBoot(clazzs);
    }

    private void onBoot(String[] clazzs) {
        for (String name : clazzs) {
            defClass(name);
        }
    }

    private Class<?> defClass(String name) {
        String diskUrl = path + name.replace('.', File.separatorChar) + ".class";
        Class<?> clazz = null;
        try {
            File clazzF = new File(diskUrl);
            FileInputStream stream = new FileInputStream(clazzF);
            int len = (int) clazzF.length();
            byte[] b = new byte[len];
            stream.read(b);
            stream.close();
            int off = 0;
            clazz = defineClass(name, b, off, b.length);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return clazz;
    }

    /**
     * 读取类
     */
    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = findLoadedClass(name);
        if (!this.clazzs.contains(name) && clazz == null) {
            clazz = getSystemClassLoader().loadClass(name);
        }
        if (clazz == null) {
            throw new ClassNotFoundException(name);
        }
        if (resolve == true) {
            resolveClass(clazz);
        }
        return clazz;
    }

    /**
     * 加载类
     */
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        //一些外部类的加载可以重写这个方法，此处略。
        return super.findClass(name);
    }

    public static void main(String[] args) {
        try {

//从application.properties文件中获取用到的参数;
            Resource resource1 = new ClassPathResource("application.properties");
            Properties props1 = PropertiesLoaderUtils.loadProperties(resource1);
            String host = props1.getProperty("mail.host");

            Class<?> myClassLoader = ClassLoader.getSystemClassLoader().loadClass("com.hzz.hzzcloud.学习.事务的隔离级别.系统类加载器.MyClassLoader");
            URL systemResource = ClassLoader.getSystemResource("application.properties");
            URL bootResource = ClassLoader.getSystemResource("bootstrap.properties");
            String path = systemResource.getPath();
            String path1 = bootResource.getPath();

            //从application.properties文件中获取用到的参数;
            Resource resource = new ClassPathResource(path);
            Properties props = new Properties();
            props.load(new FileInputStream(path));

            //有参数需要使用Constructor类对象
            //这种方式和下面这种方式都行，注意这里的参数类型是 new Class[]
            //Constructor ct=c.getDeclaredConstructor(int.class,String.class);
            Constructor ct=myClassLoader.getDeclaredConstructor(new Class[]{String.class,String[].class});
            ct.setAccessible(true);
            String [] name=new String[1];
            String[] objects = Arrays.asList("ce").toArray(name);
            MyClassLoader o = (MyClassLoader) ct.newInstance("test", Arrays.asList("ce").toArray());
            System.out.println(1);
            //这种方式和下面这种方式都可以：注意这里的参数类型是 new Object[]
            //TestB b2=(TestB) ct.newInstance(1,"2");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
