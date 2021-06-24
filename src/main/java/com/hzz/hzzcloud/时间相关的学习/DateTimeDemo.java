package com.hzz.hzzcloud.时间相关的学习;


import org.joda.time.DateTime;

import java.util.Date;
import java.util.Locale;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/23 13:40
 */
public class DateTimeDemo {

    public static void main(String[] args) {
        DateTime dateTime=new DateTime(new Date());
        //加一天操作
        dateTime= dateTime.withMonthOfYear(3);
        dateTime= dateTime.withDayOfMonth(31);
        String dd = dateTime.toString("yyyy-M-d");
        System.out.println(dd);

        DateTime dateTime1 = dateTime.plusYears(1);
        System.out.println(dateTime1);

        boolean leap = dateTime.year().isLeap();
        System.out.println(leap);

        String s = dateTime.toString();
        System.out.println(s);

        String month2 = dateTime.monthOfYear().getAsText();
        String day2 = dateTime.dayOfMonth().getAsShortText();
        String day3 = dateTime.dayOfWeek().getAsShortText(Locale.CHINESE); // 以指定格式获取
        System.out.println(month2);
        System.out.println(day2);
        System.out.println(day3);


// 某些属性进行置0操作。比如，我想得到当天的0点时刻。
        DateTime now = new DateTime();
        DateTime dateTime2 = now.dayOfWeek().roundCeilingCopy();
        DateTime dateTime3 = now.dayOfWeek().roundFloorCopy();
        DateTime dateTime4 = now.minuteOfDay().roundFloorCopy();
        DateTime dateTime5 = now.secondOfMinute().roundFloorCopy();

        System.out.println(dateTime2.toString("YYYY-MM-dd HH:mm:ss"));
        System.out.println(dateTime3.toString("YYYY-MM-dd HH:mm:ss"));
        System.out.println(dateTime4.toString("YYYY-MM-dd HH:mm:ss"));
        System.out.println(dateTime5.toString("YYYY-MM-dd HH:mm:ss"));

    }
}
