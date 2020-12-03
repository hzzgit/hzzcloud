package com.hzz.hzzcloud.test.静态内部类初始化情况;

/**
 * @author ：hzz
 * @description：静态内部类，创建时间为调用时，并非初始化实例
 * 这也算是饿汉模式中的双锁模式
 * @date ：2020/12/2 10:01
 */
public class NeibuLei {

    public static  NeibuLei neibuLei=new NeibuLei();

    static {

        System.out.println("静态方法");
    }


    public NeibuLei(){
        System.out.println("构造方法");
    }

    private static  class neibu{
         private static final NeibuLei neibuLei=new NeibuLei();
         static {
             System.out.println("静态内部类静态方法");
             neibu neibu=new neibu();
         }
         private neibu(){
             System.out.println("静态内部类构造方法");
         };
         public static NeibuLei getNeibuLei(){
             return neibuLei;
         }


    }

    public static NeibuLei getinstance(){
        return neibu.getNeibuLei();
    }

    public static void main(String[] args) {
        System.out.println("我啥都没做");
        NeibuLei getinstance = NeibuLei.getinstance();

    }

}
