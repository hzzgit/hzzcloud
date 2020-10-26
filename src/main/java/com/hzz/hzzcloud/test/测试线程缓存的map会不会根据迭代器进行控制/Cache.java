package com.hzz.hzzcloud.test.测试线程缓存的map会不会根据迭代器进行控制;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/10/19 14:43
 */
public class Cache {

    private ConcurrentHashMap<String, Client> clientConcurrentHashMap = new ConcurrentHashMap<>();


    public void init() {
        Client client = new Client("测试1");
        Client client2 = new Client("测试2");
        client.init();
        client2.init();

        clientConcurrentHashMap.put("1", client);
        clientConcurrentHashMap.put("2", client2);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Iterator<Map.Entry<String, Client>> iterator = clientConcurrentHashMap.entrySet().iterator();

        while (iterator.hasNext()) {

            Map.Entry<String, Client> next = iterator.next();
            String key = next.getKey();
            Client value = next.getValue();
            value.close();
            clientConcurrentHashMap.remove(key);
        }

        System.out.println(1);
             new Thread(()->{
                             while (true){
                                 try {
                                     Thread.sleep(1000);
                                 } catch (InterruptedException e) {
                                     e.printStackTrace();
                                 }
                             }
                     }).start();
    }

    public static void main(String[] args) {
        new Cache().init();


    }
}
