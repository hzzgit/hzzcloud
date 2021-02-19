package com.hzz.hzzcloud.公司临时代码;

import com.hzz.hzzjdbc.jdbcutil.util.ConverterUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/2/4 15:02
 */
public class 插入rocksdb之前已经统计的月份 {
    public static void main(String[] args) {
        for (int i = 2; i < 24; i++) {
            String startTime = getdatebyMonth(i);//上个月的时间
            String endTime = getlastmonthbyday(startTime);//基于上个月的最后一天时间
            String sql="INSERT INTO `subiaodb`.`rocksreporttimeconfig`(`id`, `createTime`, `reportdate`) VALUES (0, '2021-02-04 11:33:44', '"+startTime+":"+endTime+"');";
            System.out.println(sql);
        }

    }

    /**
     * 获取当前时间的几个月前的日期
     *
     * @param month
     * @return
     */
    public static String getdatebyMonth(int month) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, -month);
        Date m = c.getTime();
        String mon = format.format(m);
        return mon + "-01";
    }

    /**
     * 获取这个月最后一天的日期
     *
     * @param time
     * @return
     */
    public static String getlastmonthbyday(String time) {
        int year = ConverterUtils.toInt(time.substring(0, 4), 0);
        int month = ConverterUtils.toInt(time.substring(5, 7), 0);
        Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year); // 2010年
        c.set(Calendar.MONTH, month - 1); // 6 月
        int days = c.getActualMaximum(Calendar.DAY_OF_MONTH);
        return year + "-" + time.substring(5, 7) + "-" + days;
    }
}
