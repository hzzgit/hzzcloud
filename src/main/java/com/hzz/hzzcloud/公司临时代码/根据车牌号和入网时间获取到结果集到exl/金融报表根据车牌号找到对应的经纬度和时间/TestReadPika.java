package com.hzz.hzzcloud.公司临时代码.根据车牌号和入网时间获取到结果集到exl.金融报表根据车牌号找到对应的经纬度和时间;

import com.hzz.hzzcloud.公司临时代码.EasyExceltofileUtil;
import com.hzz.hzzcloud.公司临时代码.根据车牌号和入网时间获取到结果集到exl.AreaAlarmCache;
import com.hzz.hzzcloud.公司临时代码.根据车牌号和入网时间获取到结果集到exl.reportExlMobanVo;
import redis.clients.jedis.Jedis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Consumer;

/**
 * @author ：hzz
 * @description：一天一天去pika里面找最新的定位位置
 * @date ：2020/12/25 11:45
 */
public class TestReadPika {
    private Jedis jedis=new Jedis("172.16.8.114",9221);

    public Jedis getJedis() {
        return jedis;
    }

    public void readfile(Consumer<String> data){
        String filepath="C:\\Users\\fxft\\Desktop\\platenototime.txt";
        File file=new File(filepath);
        BufferedReader reader = null;

        boolean arg=true;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                data.accept(tempStr);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 得到几天前的时间
     * @param d
     * @param day
     * @return
     */
    public static Date getDateBefore(Date d, int day){
        Calendar now =Calendar.getInstance();
        now.setTime(d);
        now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
        return now.getTime();
    }

    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd
     *
     * @param dateDate
     * @param
     * @return
     */
    public static String dateToStr(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
        String dateString = formatter.format(dateDate);
        return dateString;
    }


    /**
     * 将短时间格式时间转换为字符串 yyyy-MM-dd HH:mm:ss
     *
     * @param dateDate
     * @param
     * @return
     */
    public static String dateToStryyyymmddhhmmss(Date dateDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(dateDate);
        return dateString;
    }

    public void  main() {

        

        TestReadPika testReadPika=new TestReadPika();
        Jedis jedis = testReadPika.getJedis();




        List<LocationVo> gpsMessages=new ArrayList<>();
        testReadPika.readfile(str->{
            String trim = str.trim();
            GpsMessage gpsMessage=null;
            LocationVo locationVo=null;
            if(str!=null&&!"".equalsIgnoreCase(trim)){
                for (int i = 0; i <200 ; i++) {
                    Date dateBefore = getDateBefore(new Date(), i);
                    String time = dateToStr(dateBefore);
                    String key="gzf:"+time+":"+trim;
                    Boolean exists = jedis.exists(key.getBytes());
                    if(exists){
                        Set<byte[]> zrange = jedis.zrevrange(key.getBytes(), 0, 1);
                        for (byte[] bytes : zrange) {
                            gpsMessage=new GpsMessage();
                            gpsMessage.jsontodata(new String (bytes));
                            double latitude = gpsMessage.getLatitude();
                            double longitude = gpsMessage.getLongitude();
                             locationVo =new LocationVo();
                            locationVo.setLat(latitude);
                            locationVo.setLon(longitude);
                            locationVo.setTime(dateToStryyyymmddhhmmss(gpsMessage.getTime()));
                            break;
                        }
                        break;
                    }
                }
                if(locationVo==null){
                    gpsMessages.add(new LocationVo());
                }else{
                    gpsMessages.add(locationVo);
                }
            }
        });
        EasyExceltofileUtil easyExceltofileUtil=new EasyExceltofileUtil();
        easyExceltofileUtil.exportfile("te","C:\\Users\\fxft\\Desktop", LocationVo.class);
        easyExceltofileUtil.write(gpsMessages);
        easyExceltofileUtil.finishwrite();

    }
}
