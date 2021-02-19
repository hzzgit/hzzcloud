package com.hzz.hzzcloud.test;


import java.util.UUID;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/12 17:46
 */
public class UUID纯数字  {


    public static Integer getUUIDInOrderId(){
        long leastSignificantBits = UUID.randomUUID().getLeastSignificantBits();
        System.out.println(leastSignificantBits);

        String s = UUID.randomUUID().toString();
        Integer orderId= s.hashCode();
        orderId = orderId < 0 ? -orderId : orderId; //String.hashCode() 值会为空
        return orderId;
    }

    public static void main(String[] args) {
        System.out.println(UUID纯数字.getUUIDInOrderId());
        String platenoOrsimno="123443c";
        platenoOrsimno=platenoOrsimno.trim();
        String searchbytype="";
        boolean arg=false;
        if ("A闽C".toLowerCase().indexOf(platenoOrsimno.toLowerCase()) > -1) {//如果车牌号和编号有一个等于这个缓存的车辆，那么查询结果就是对的
            searchbytype = "plateNo";
            arg = true;
        } else if ("a123443C".toLowerCase().indexOf(platenoOrsimno.toLowerCase()) > -1) {//如果车牌号和编号有一个等于这个缓存的车辆，那么查询结果就是对的
            searchbytype = "simNo";
            arg = true;
        } else if ("1111".toLowerCase().indexOf(platenoOrsimno.toLowerCase()) > -1) {//如果车牌号和编号有一个等于这个缓存的车辆，那么查询结果就是对的
            searchbytype = "termNo";
            arg = true;
        }
        System.out.println(arg);
        System.out.println(searchbytype);
    }
}
