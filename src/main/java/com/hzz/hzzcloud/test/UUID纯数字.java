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

    }
}
