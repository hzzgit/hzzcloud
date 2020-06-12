package com.hzz.hzzcloud.serialize;

import com.hzz.serialize.entity.AlarmConfig;
import com.hzz.serialize.entity.GpsRealDataRest;
import com.hzz.serialize.util.KryoUtil;
import net.fxft.cloud.redis.RedisUtil;
import org.apache.lucene.util.RamUsageEstimator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Service
public class kryotet {

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    private void test() {
        AlarmConfig alarmConfig = new AlarmConfig();
        alarmConfig.setDepid(122);

        redisUtil.execute(jedis -> {
            jedis.set("alarm".getBytes(), KryoUtil.object2clsbyte(alarmConfig));
        });

        AlarmConfig alarmConfig2 = (AlarmConfig) redisUtil.execute(jedis -> {
            byte[] bytes = jedis.get("alarm".getBytes());
            return KryoUtil.<AlarmConfig>clsbyte2object(bytes);
        });
        System.out.println(1);
        try {
            while (true) {
                try {
                    long s = System.currentTimeMillis();   //获取开始时间
                    byte[] bytes = restTemplate.getForObject("http://localhost:8089/testuser/searchkryo.action", byte[].class);
                    GpsRealDataRest gpsRealDataRest = KryoUtil.clsbyte2object(bytes);
                    long e = System.currentTimeMillis(); //获取结束时间
                    System.out.println("内存humanSizeOf:" + RamUsageEstimator.humanSizeOf(gpsRealDataRest));

                    System.out.println("用时：" + (e - s) + "ms");
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
