package com.hzz.hzzcloud.test;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.SystemUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

public class 点到线段的距离 {

    public static void main(String[] args) {
        double bufferLen = 12;
        double a = 6;
        double b = 6;
        double c = 10;
        double p = (a + b + c) / 2;//这边是获取三角形的半周长
        System.out.println("三角形半周长等于"+p);
        double s = Math.sqrt(p * (p - a) * (p - b) * (p - c));//(周长*(周长减去各边长的)的平方根,也就是这个点和这条线最近的点的距离,也就是线宽偏差值
        System.out.println("三角形面积="+s);
        //海伦公式:在计算三角形面积的时候经常会用到一种公式，即S=√￣(q*(q-x)*(q-y)*(q-z))；这里的q就是半周长，而x,y,z分别为三边的边长，q=(x+y+z)/2。
        if(a+b>c&&a+c>b&&b+c>a){
            System.out.println("任意两边之和大于第三边,是个三角形");
        }
        double h = 2 * s / c;//这个其实就是求高度
        //if (h > bufferLen)
        System.out.println("h="+h);
        System.out.println("bufferLen"+bufferLen);
        double t1 = b * b + c * c - a * a;//两边平方之和大于第三边平方,才能判断为三角形
        double t2 = a * a + c * c - b * b;
        if (t1 < 0 || t2 < 0) {
            System.out.println("这个不是一个三角形");
        }else{
            System.out.println("这个是一个三角形");
            System.out.println("t1="+t1);
            System.out.println("t2="+t2);
        }
        double v = RandomUtils.nextDouble(122.22, 1111.2);
        System.out.println(SystemUtils.JAVA_RUNTIME_VERSION );

    }




//    1 、在平面上三角形的内角和等于180°（内角和定理）。
//            2 、在平面上三角形的外角和等于360° (外角和定理)。
//            3、 在平面上三角形的外角等于与其不相邻的两个内角之和。
//    推论：三角形的一个外角大于任何一个和它不相邻的内角。
//            4、 一个三角形的三个内角中最少有两个锐角。
//            5、 在三角形中至少有一个角大于等于60度，也至少有一个角小于等于60度。
//            6 、三角形任意两边之和大于第三边，任意两边之差小于第三边。
//            7、 在一个直角三角形中，若一个角等于30度，则30度角所对的直角边是斜边的一半。
//            8、直角三角形的两条直角边的平方和等于斜边的平方（勾股定理）。
//            *勾股定理逆定理：如果三角形的三边长a，b，c满足a²+b²=c² ，那么这个三角形是直角三角形。
//            9、直角三角形斜边的中线等于斜边的一半。
//            10、三角形的三条角平分线交于一点，三条高线的所在直线交于一点，三条中线交于一点。
//            11、三角形三条中线的长度的平方和等于它的三边的长度平方和的3/4。
//            12、 等底同高的三角形面积相等。
//            13、 底相等的三角形的面积之比等于其高之比，高相等的三角形的面积之比等于其底之比。
//            14、三角形的任意一条中线将这个三角形分为两个面积相等的三角形。
//            15、等腰三角形顶角的角平分线和底边上的高、底边上的中线在一条直线上（三线合一）。
//            16、 在同一个三角形内，大边对大角，大角对大边。
//    在三角形中 ,其中角α,β,γ分别对着边a,b,c。
//            17、 在斜△ABC中恒满足： 。
//            18、△ABC中恒有 。
//            19、三角形具有稳定性。

}
