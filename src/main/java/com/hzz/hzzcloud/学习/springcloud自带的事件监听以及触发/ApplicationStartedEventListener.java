package com.hzz.hzzcloud.学习.springcloud自带的事件监听以及触发;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author ：hzz
 * @description：在上下文刷新之后且所有的应用和命令行运行器（command-line runner）被调用之前发送 ApplicationStartedEvent。
 * @date ：2021/2/6 14:44
 */
@Service
public class ApplicationStartedEventListener implements ApplicationListener<ApplicationStartedEvent> {
    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {

        System.out.println("ApplicationStartedEvent:"+event);
    }
}
