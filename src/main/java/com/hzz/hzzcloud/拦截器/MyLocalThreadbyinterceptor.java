package com.hzz.hzzcloud.拦截器;

import lombok.extern.slf4j.Slf4j;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/27 15:35
 */
@Slf4j
public class MyLocalThreadbyinterceptor {
    private ThreadLocal<Integer> stringThreadLocal=new ThreadLocal(){
        @Override
        protected Object initialValue() {
           return 1;
        }
    };

    public void add( ){
        Integer integer = stringThreadLocal.get();
        stringThreadLocal.set(integer+1);
    }

    public Integer get(){
      return   stringThreadLocal.get();
    }
}
