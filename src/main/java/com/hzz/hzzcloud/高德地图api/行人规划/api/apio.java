package com.hzz.hzzcloud.高德地图api.行人规划.api;

import com.alibaba.fastjson.JSON;
import com.hzz.hzzcloud.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzcloud.jdbcutil.jdkjdbc.JdkDataSource;
import com.hzz.hzzcloud.jdbcutil.util.TimeUtils;
import com.hzz.hzzcloud.高德地图api.行人规划.Vo.JsonsRootBean;
import com.hzz.hzzcloud.高德地图api.行人规划.Vo.Paths;
import com.hzz.hzzcloud.高德地图api.行人规划.Vo.Steps;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class apio {
    static double pi = 3.14159265358979324;

    //
    // Krasovsky 1940
    //
    // a = 6378245.0, 1/f = 298.3
    // b = a * (1 - f)
    // ee = (a^2 - b^2) / a^2;
    static double a = 6378245.0;
    static double ee = 0.00669342162296594323;

    public static void main(String[] args) {

        String key="a7c538c85b1d65c958a20a46761a1db1";
        RestTemplate restTemplate=new RestTemplate();
        String forObject = restTemplate.getForObject("https://restapi.amap.com/v3/direction/walking?origin=" +
                "119.286387,26.047639&destination=119.289027,26.051379&key=" + key, String.class);
        JsonsRootBean j= JSON.parseObject(forObject,JsonsRootBean.class);
        System.out.println(1);
        ArrayList<String> datas=new ArrayList<>();
        for (Paths path : j.getRoute().getPaths()) {
            List<Steps> steps = path.getSteps();
            for (Steps step : steps) {
                String polyline = step.getPolyline();
                String[] split = polyline.split(";");
                datas.addAll(Arrays.asList(split));
            }
        }
        System.out.println(j);
        JdkDataSource.jdkmysql();
        MysqlDao mysqlDao=  JdkDataSource.mysqldb;
        String simNo="012805920825";
        Long vehicleId =new Long(36408);
        for (String data : datas) {

            String longtidute=data.split(",")[0];
            String latitude=data.split(",")[1];
//            String name=apio.get(Double.parseDouble(latitude),Double.parseDouble(longtidute));
//            String[] split = name.split(",");
//             longtidute=split[0];
//             latitude=split[1];
            Date date = new Date();
            String s = TimeUtils.dateTodetailStr(date);
            String sql="INSERT INTO `gps_hisdata`.`gpsinfo_20200701`(`gpsId`, `uuid`, `simNoHash`, `simNo`, `vehicleId`, `createDate`," +
                    " `sendTime`, `alarmState`, `altitude`, `direction`, `gas`, `latitude`, `longitude`, `mileage`," +
                    " `recordVelocity`, `status`, `valid`, `velocity`, `signalState`, `abnormalType`, `fromFlag`," +
                    " `extendVersion`, `extendJson`) VALUES (0, 8106481254648860618, ?, ?," +
                    " ?, ?, ?, 0, 27.00, 280, 0.00, ?," +
                    " ?, 610200.00, 90.00, 786627, b'1', 83.00, 3, 0, 1, 0, NULL)";
            mysqlDao.excutesql(sql,simNo.hashCode(),simNo,vehicleId,date,date,latitude,longtidute);

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }


    public static String get(double wgLat, double wgLon){

            double mgLat = 0;
            double mgLon = 0;

            double dLat = transformLat(wgLon - 105.0, wgLat - 35.0);
            double dLon = transformLon(wgLon - 105.0, wgLat - 35.0);
            double radLat = wgLat / 180.0 * pi;
            double magic = Math.sin(radLat);
            magic = 1 - ee * magic * magic;
            double sqrtMagic = Math.sqrt(magic);
            dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
            dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
            mgLat = wgLat + dLat;
            mgLon = wgLon + dLon;
            String data=mgLon+","+mgLat;
            return data;


    }

    private static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    private static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0
                * pi)) * 2.0 / 3.0;
        return ret;
    }
}
