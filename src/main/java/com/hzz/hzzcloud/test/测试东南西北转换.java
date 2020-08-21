package com.hzz.hzzcloud.test;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/18 13:48
 */
public class 测试东南西北转换 {
    public static void main(String[] args) {
        String direction = "东";
        String direction1 = "西";
        String direction2 = "东北";
        String direction3 = "东南";

        测试东南西北转换 test=new 测试东南西北转换();

        System.out.println(test.replaceall(direction));
        System.out.println(test.replaceall(direction1));
        System.out.println(test.replaceall(direction2));
        System.out.println(test.replaceall(direction3));

    }

    public String replaceall(String direction){
        if(direction.indexOf("东")>-1){
            direction=  direction.replaceAll("东","西");
        }else if(direction.indexOf("西")>-1){
            direction= direction.replaceAll("西","东");
        }

        if(direction.indexOf("北")>-1){
            direction=direction.replaceAll("北","南");
        }else if(direction.indexOf("南")>-1){
            direction=direction.replaceAll("南","北");
        }
        return  direction;
    }
}
