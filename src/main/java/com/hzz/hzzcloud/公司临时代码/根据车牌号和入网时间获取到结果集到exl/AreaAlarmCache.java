package com.hzz.hzzcloud.公司临时代码.根据车牌号和入网时间获取到结果集到exl;

import com.hzz.hzzcloud.公司临时代码.根据车牌号和入网时间获取到结果集到exl.金融报表根据车牌号找到对应的经纬度和时间.LocationVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.List;

public class AreaAlarmCache {


    private static final Logger log = LoggerFactory.getLogger(AreaAlarmCache.class);
    private static final   String cachename="locationcache.cache";




    //这边是读取文件的缓存
    public static List<LocationVo> loadCache() {
        try {
            long s = System.currentTimeMillis(); //获取开始时间

            File f = new File(cachename);
            if (f.exists()) {
                FileInputStream fis = new FileInputStream(f);
                ObjectInputStream ois = new ObjectInputStream(fis);
                Object obj = ois.readObject();
                ois.close();
                List<LocationVo> gpsMessages = (List<LocationVo>) obj;
                long e = System.currentTimeMillis(); //获取结束时间
                log.debug( "本地缓存读取时间为" + (e - s) + "ms");
                return gpsMessages;
            }
        } catch (Exception e) {
            log.error("读取保单进出围栏缓存失败", e);
            return null;
        }

        return null;
    }


    public static void saveCache(List<LocationVo> CrossMap) {
        try {
            long l1 = System.currentTimeMillis();
            FileOutputStream fos = new FileOutputStream(cachename);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(CrossMap);
            oos.flush();
            oos.close();
            long l2 = System.currentTimeMillis();
            log.debug( "保单本地缓存写入时间为" + (l2 - l1) + "ms");
        } catch (Exception e) {
            log.error("保存保单进出围栏缓存失败", e);
        }

    }

    public static  void deletecache(){
        File f = new File(cachename);
        if (f.exists()) {
            f.delete();
        }
    }

    public static void main(String[] args){


    }


}
