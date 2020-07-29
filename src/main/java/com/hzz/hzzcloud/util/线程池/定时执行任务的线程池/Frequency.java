package com.hzz.hzzcloud.util.线程池.定时执行任务的线程池;

import net.fxft.common.tpool.FrequencyPoolExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/29 14:23
 */
public class Frequency {

    public static void main(String[] args) {
        FrequencyPoolExecutor frequencyPoolExecutor=new
                FrequencyPoolExecutor<String>(1,5,1,"11",list->{
            System.out.println(list);
        });

        List<String> names=new ArrayList<>();
        for (int i = 0; i <11 ; i++) {
            names.add("11"+i);
        }

        try {
            frequencyPoolExecutor.blockedExecute(names,10, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}
