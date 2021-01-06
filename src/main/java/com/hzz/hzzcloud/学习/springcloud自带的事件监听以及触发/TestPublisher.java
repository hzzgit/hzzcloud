package com.hzz.hzzcloud.学习.springcloud自带的事件监听以及触发;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/1/6 15:51
 */
@Service
public class TestPublisher {

    @Autowired
    private CampPointPublisher campPointPublisher;

    @PostConstruct
    private void test(){
        campPointPublisher.publish("我特码的就是测试下");
    }
}
