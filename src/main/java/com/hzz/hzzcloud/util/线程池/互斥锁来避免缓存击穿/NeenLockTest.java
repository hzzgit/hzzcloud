package com.hzz.hzzcloud.util.线程池.互斥锁来避免缓存击穿;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/14 13:49
 */
public class NeenLockTest {

    private Map<String, List> ca = new ConcurrentHashMap<>();

    static Lock reenLock = new ReentrantLock();
    private AtomicInteger jisuco = new AtomicInteger(0);

    private AtomicInteger lockco = new AtomicInteger(0);
    private AtomicInteger readsucco = new AtomicInteger(0);

    public List<String> getData04(String name) throws InterruptedException {
        List<String> result = new ArrayList<String>();
        // 从缓存读取数据
        result = getDataFromCache(name);
        if (result == null) {
            lockco.addAndGet(1);
            if (lockco.get() < 100) {
                try {
                    readsucco.addAndGet(1);
                   // System.out.println("当前数量"+lockco.get());
                    // 从数据库查询数据
                    result = getDataFromDB();
                    // 将查询到的数据写入缓存
                    setDataToCache(result);
                } finally {
                    lockco.addAndGet(-1);
                }
            } else {
                result = getDataFromCache(name);// 先查一下缓存
                jisuco.addAndGet(1);
                if (result == null) {

                  //  Thread.sleep(100);// 小憩一会儿
                    return getData04(name);// 重试
                }
            }
        }
        return result;
    }

    public List getDataFromDB() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ArrayList<Object> objects = new ArrayList<>();
        objects.add("1");
        return objects;
    }

    public List getDataFromCache(String name) {

        return ca.get(name);
    }

    public void setDataToCache(List re) {
        ca.put("1", re);
    }

    public static void main(String[] args) {
        NeenLockTest neenLockTest = new NeenLockTest();
        AtomicInteger all=new AtomicInteger(0);
        for (int i = 0; i < 1000; i++) {
            new Thread(() -> {
                try {
                    List<String> data04 = neenLockTest.getData04("1");
                    if(data04.get(0)!=null){
                        all.addAndGet(1);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("等待的数量"+neenLockTest.jisuco.get());
        System.out.println("成功读取的数量"+neenLockTest.readsucco.get());
        System.out.println("全部读取到的数量"+all.get());
    }

}
