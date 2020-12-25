package com.hzz.hzzcloud.公司临时代码.根据车牌号和入网时间获取到结果集到exl.金融报表根据车牌号找到对应的经纬度和时间;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Calendar;
import java.util.Date;

/**
 * java 8  LocalDateTime 时间转换工具类
 *
 * @author xiaowen
 * @date 2016年11月1日 @ version 1.0
 */
public  final class TimeUtils {


    // 无参数的构造函数,防止被实例化
    private TimeUtils() {};



    /**
     * 获取当前时间
     *

     *            时间格式
     * @return
     */
    public static String getCurrentDateTime() {
        Date date=new Date();
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        return  format.format(calendar.getTime());
    }


    public static LocalDate datetolocaldate(Date dates){
        Instant instant = dates.toInstant();
        ZoneId zoneId = ZoneId.systemDefault();
        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        return  localDate;
    }

    public static String getbeforeDateTime(int beforeday){
        Date date=new Date();
        Calendar calendar= Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -beforeday);
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        return  format.format(calendar.getTime());
    }

    /**
     * Date转换为LocalDateTime
     * @param date
     */
    public static LocalDateTime date2LocalDateTime(Date date){
        Instant instant = date.toInstant();//An instantaneous point on the time-line.(时间线上的一个瞬时点。)
        ZoneId zoneId = ZoneId.systemDefault();//A time-zone ID, such as {@code Europe/Paris}.(时区)
        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        return  localDateTime;
    }



    /**
     * LocalDateTime转换为Date
     * @param localDateTime
     */
    public static Date localDateTime2Date( LocalDateTime localDateTime){
        ZoneId zoneId = ZoneId.systemDefault();
        ZonedDateTime zdt = localDateTime.atZone(zoneId);//Combines this date-time with a time-zone to create a  ZonedDateTime.
        Date date = Date.from(zdt.toInstant());
        return  date;
    }





    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @param
     * @return
     */
    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd ");
        String dateString = formatter.format(dateDate);
        return dateString;
    }


    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd h24:mm:ss
     *
     * @param dateDate
     * @param
     * @return
     */
    public static String dateTodetailStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }


    public static Long dateToyyyymmddStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(dateDate);
        return ConverterUtils.toLong(dateString);
    }

    public static  Long timestamapToyyyymmddlong(Long time){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(new Date(time));
        return ConverterUtils.toLong(dateString);
    }




    //将字符串的转成时间类型
    public static Date date(String date_str) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");//改为需要的东西
            Date date=formatter.parse(date_str);
            return date;
        }
        catch (NullPointerException e) {
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }


    //将字符串的转成时间类型
    public static Date todatetime(String date_str) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date=sdf.parse(date_str);
            return date;
        }
        catch (NullPointerException e) {
            return null;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }



    /**
     * 判断当前时间是否在[startTime, endTime]区间，注意时间格式要一致
     *
     * @param nowTime 当前时间
     * @param startTime 开始时间
     * @param endTime 结束时间
     * @return
     * @author jqlin
     */
    public static boolean isEffectiveDate(Date nowTime, Date startTime, Date endTime) {
        if (nowTime.getTime() == startTime.getTime()
                || nowTime.getTime() == endTime.getTime()) {
            return true;
        }

        Calendar date = Calendar.getInstance();
        date.setTime(nowTime);

        Calendar begin = Calendar.getInstance();
        begin.setTime(startTime);

        Calendar end = Calendar.getInstance();
        end.setTime(endTime);

        if (date.after(begin) && date.before(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * date2比date1多的天数
     * @param date1
     * @param date2
     * @return
     */
    public static int differentDays(Date date1,Date date2)
    {
        Calendar cal1 = Calendar.getInstance();
        cal1.setTime(date1);

        Calendar cal2 = Calendar.getInstance();
        cal2.setTime(date2);
        int day1= cal1.get(Calendar.DAY_OF_YEAR);
        int day2 = cal2.get(Calendar.DAY_OF_YEAR);

        int year1 = cal1.get(Calendar.YEAR);
        int year2 = cal2.get(Calendar.YEAR);
        if(year1 != year2)   //同一年
        {
            int timeDistance = 0 ;
            for(int i = year1 ; i < year2 ; i ++)
            {
                if(i%4==0 && i%100!=0 || i%400==0)    //闰年
                {
                    timeDistance += 366;
                }
                else    //不是闰年
                {
                    timeDistance += 365;
                }
            }

            return timeDistance + (day2-day1) ;
        }
        else    //不同年
        {
            return day2-day1;
        }
    }



    private static Date getchangdaydate(Date date,int day){
        Calendar c=Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, day);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        Date date1=c.getTime();
        return  date1;
    }


    /**
     * 时间戳转时间
     * @param timeStamp
     * @return 字符串类型的时间
     */
    public static String timeStamptotime(long timeStamp) throws  Exception{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//这个是你要转成后的时间的格式
        Date da= new Date(timeStamp);
        return sdf.format(da);
    }
    /**
     * 时间戳转时间
     * @param timeStamp
     * @return 字符串类型的时间
     */
    public static String timeStamptotimebyday(long timeStamp) throws  Exception{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");//这个是你要转成后的时间的格式
        Date da= new Date(timeStamp);
        return sdf.format(da);
    }
    /**
     * 时间戳转时间
     * @param timeStamp
     * @return 字符串类型的时间
     */
    public static String timeStamptotimeday(long timeStamp) throws  Exception{
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//这个是你要转成后的时间的格式
        Date da= new Date(timeStamp);
        return sdf.format(da);
    }

    /**
     * 时间转时间戳
     * @param time
     * @return
     */
    public static Long timetotimeStamp(Date time) {
        Long it = time.getTime();
        return  it;
    }


    public static void main(String[] args) {
//        long timeStamp = System.currentTimeMillis();
//        timeStamp=TimeUtils.timetotimeStamp(new Date());
//        String da = TimeUtils.timeStamptotime(timeStamp);
//        System.out.println(da);

    }













}
