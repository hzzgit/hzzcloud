package com.hzz.hzzcloud.redis使用;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/11 11:34
 */
public class RedisTest {

    public static Jedis jedis;

    static {
        jedis = new Jedis("localhost", 6379);

    }

    public void setnx(){
        jedis.setnx("name","a");
        jedis.expire("name",50);
        while (true){
            try {
                jedis.setnx("name","2");
                String s = jedis.get("name");
                if("2".equalsIgnoreCase(s)){
                    break;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        jedis.del("name");
    }

    public static void main(String[] args) {
    new RedisTest().setnx();

    }

    public void tereids(){
        //        jedis.lpush("list1","12112");
//        jedis.rpush("list2","12222");

        long s = System.currentTimeMillis();   //获取开始时间

//        for (int i = 0; i < 10000; i++) {
//            if(i%2==0){
//                jedis.lpush("list1", String.valueOf(i));
//            }else{
//                jedis.rpush("list1", String.valueOf(i));
//            }
//
//        }

        jedis.sort("list1");
        String list11 = jedis.lpop("list1");
        System.out.println(list11);
        Long list1 = jedis.llen("list1");
        System.out.println(list1);
        long e = System.currentTimeMillis(); //获取结束时间
        System.out.println("用时：" + (e - s) + "ms");
        jedis.set("te", String.valueOf(1));

        System.out.println(jedis.type("list1"));
        System.out.println(jedis.type("te"));

        jedis.zadd("test",1222,"data");

        Set<String> test = jedis.zrangeByScore("test", 1111, 1234);
        Set<String> test2 = jedis.zrange("test", 0, 2);
        Set<Tuple> test1 = jedis.zrangeByScoreWithScores("test", 1111, 1234);
        new RedisTest().te1();
    }

    public void te1() {
        new Thread(() -> {
            Pipeline pipelined = jedis.pipelined();
            for (int i = 0; i <1000 ; i++) {
                pipelined.rpop("list1");
            }
            List<Object> objects = pipelined.syncAndReturnAll();
            for (Object object : objects) {
                System.out.println(object);
            }
            
        }).start();
    }
}
