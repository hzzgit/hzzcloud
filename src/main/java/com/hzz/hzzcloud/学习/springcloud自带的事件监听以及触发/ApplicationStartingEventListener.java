package com.hzz.hzzcloud.学习.springcloud自带的事件监听以及触发;

import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author ：hzz
 * @description：在开始应用开始运行但还没有进行任何处理时（除了注册监听器和初始化器[initializer]），将发送 ApplicationStartingEvent。
 * @date ：2021/2/6 14:44
 */
@Service
public class ApplicationStartingEventListener implements ApplicationListener<ApplicationStartingEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartingEvent event) {

        System.out.println("ApplicationStartingEvent:"+event);
    }
}
