package com.hzz.hzzcloud.test;

import java.time.LocalDate;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/1/27 11:23
 */
public class LocalDateTest {
    public static void main(String[] args) {
        LocalDate dataDay = LocalDate.now();
        LocalDate localDate = dataDay.withDayOfMonth(1);
        System.out.println(1);
    }
}
