package com.hzz.hzzcloud.学习.springcloud自带的事件监听以及触发;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/1/6 15:50
 */
@Component
@Slf4j
public class CampPointListener implements ApplicationListener<CampPointEvent> {



    @Override
    public void onApplicationEvent( CampPointEvent campPointEvent) {

        System.out.println("监听到数据"+campPointEvent.getCampPoint());
    }



}