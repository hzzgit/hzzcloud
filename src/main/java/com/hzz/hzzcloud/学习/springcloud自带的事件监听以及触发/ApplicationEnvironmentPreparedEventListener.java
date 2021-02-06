package com.hzz.hzzcloud.学习.springcloud自带的事件监听以及触发;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationStartingEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author ：hzz
 * @description：当 Environment 被上下文使用，但是在上下文创建之前，将发送 ApplicationEnvironmentPreparedEvent。
 * @date ：2021/2/6 14:44
 */
@Service
public class ApplicationEnvironmentPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {

        System.out.println("ApplicationEnvironmentPreparedEvent:"+event);
    }
}
