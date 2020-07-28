package com.hzz.hzzcloud.设计模式.单例模式.懒汉模式;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/28 10:18
 */
public class singinneibu {

    private singinneibu(){

    }

    private static   class singinneibuclass{
        private static singinneibu singin=new singinneibu();
    }

    public static singinneibu getinit(){
        return singinneibuclass.singin;
    }

    /* 如果该对象被用于序列化，可以保证对象在序列化前后保持一致 */
    public Object readResolve() {
        return getinit();
    }
}
