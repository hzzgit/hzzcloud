package com.hzz.hzzcloud.时间相关的学习;


import org.joda.time.LocalDateTime;

import java.util.Date;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/23 13:40
 */
public class LocalDateTimeDemo {

    public static void main(String[] args) {

        LocalDateTime payTime=LocalDateTime.fromDateFields(new Date());
        //13
        System.out.println(payTime.toString("HH"));
        //2021-06-23
        System.out.println(payTime.toString("YYYY-MM-dd"));
        //2021-06-23 13
        System.out.println(payTime.toString("YYYY-MM-dd HH"));

    }
}
