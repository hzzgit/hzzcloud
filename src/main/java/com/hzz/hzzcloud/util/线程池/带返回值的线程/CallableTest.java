package com.hzz.hzzcloud.util.线程池.带返回值的线程;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/2/25 14:49
 */
public class CallableTest implements Callable {

    @Override
    public Object call() throws Exception {
        int a=0;
        for (int i = 0; i <100 ; i++) {
                a+=i;
        }
        Thread.sleep(2000);
        return  a;
    }

    public static void main(String[] args) {

        String s = DigestUtils.md5Hex("123456abc");
        System.out.println(s);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        CallableTest callableTest=new CallableTest();

        List<Future> futureList=new ArrayList<>();
        for (int i = 0; i <12 ; i++) {
            Future submit = executorService.submit(callableTest);
            futureList.add(submit);
        }

        for (Future future : futureList) {
            Object o = null;
            try {
                o = future.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
            System.out.println(o);
        }
        FutureTask futureTask=new FutureTask(callableTest);
        new Thread(futureTask).start();

        Object o = null;
        try {
            o = futureTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(o);

    }
}
