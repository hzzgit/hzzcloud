package com.hzz.hzzcloud.公司临时代码.插入到配置表以执行金融报表生成;


import com.hzz.hzzjdbc.jdbcutil.util.ConverterUtils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AccsReportwebapiApplicationTests {




    public static void main(String[] args) {
        for (int i = 1; i <22 ; i++) {
            String time=getdatebyMonth(i);
            String getlastmonthbyday = getlastmonthbyday(time);
            String sql="INSERT INTO `subiaodb`.`journey_config`(`id`, `createDate`, `isPross`, `updateDate_data`, `updateDate_report`, `remark`) " +
                    "VALUES (0, '"+getlastmonthbyday+"', 1, '2021-01-12 16:29:31', '2021-01-12 16:29:32', NULL); ";
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
