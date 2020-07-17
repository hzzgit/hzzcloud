package com.hzz.hzzcloud.util.插入实时数据;

import com.ltmonitor.entity.GPSRealData;
import net.fxft.cloud.redis.RedisUtil;
import net.fxft.gateway.util.KryoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/testuser")
public class testController {


    @Autowired
    private RedisUtil redisUtil;


    //@PostConstruct
    private void init(){

        List<GPSRealData> gpsRealDatas=new ArrayList<>();
        for (int i = 0; i <1000000 ; i++) {
            GPSRealData gpsRealData=new GPSRealData();
            gpsRealData.setId(i);
            gpsRealData.setLatitude(12212212.31331);
            gpsRealData.setAltitude(122122121.31331);
            gpsRealData.setAlarmState("21212133");
            gpsRealData.setOnline(true);
            gpsRealData.setSimNo("1213314"+i);
            gpsRealDatas.add(gpsRealData);
        }
        Map<String,byte[] > amap=new HashMap<>();
        for (GPSRealData gpsRealData : gpsRealDatas) {
                    amap.put(gpsRealData.getSimNo(), KryoUtil.object2clsbyte(gpsRealData) );
        }
          long s = System.currentTimeMillis();   //获取开始时间

        long e = System.currentTimeMillis(); //获取结束时间
        System.out.println( "写入文件用时：" + (e - s) + "ms");
             new Thread(()->{
                             while (true){
                                   long s1 = System.currentTimeMillis();   //获取开始时间

                                 String key="rd:";
                                 redisUtil.pipeline(pl -> {//这边计算正常点
                                     for (GPSRealData gpsRealData : gpsRealDatas) {
                                         pl.setex((key+gpsRealData.getSimNo()).getBytes(),1000,KryoUtil.object2byte(gpsRealData));
                                     }
                                 });
//                                 String key2="allrd:";
//                                 redisUtil.pipeline(pl -> {//这边计算正常点
//                                     for (GPSRealData gpsRealData : gpsRealDatas) {
//                                         pl.setex((key2+gpsRealData.getSimNo()),1000, JacksonUtil.toJsonString(gpsRealData));
//                                     }
//                                 });
                                 long e1 = System.currentTimeMillis(); //获取结束时间
                                 System.out.println("用时：" + (e1 - s1) + "ms");
                                 try {
                                     Thread.sleep(10000);
                                 } catch (InterruptedException e2) {

                                 }

                             }
                     }).start();



    }

    public static void main(String[] args) {
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
