package com.hzz.hzzcloud.学习.springcloud自带的事件监听以及触发;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author ：hzz
 * @description：在开始刷新之前，bean 定义被加载之后发送 ApplicationPreparedEvent。
 * @date ：2021/2/6 14:44
 */
@Service
public class ApplicationPreparedEventListener implements ApplicationListener<ApplicationPreparedEvent> {
    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {

        System.out.println("ApplicationPreparedEvent:"+event);
    }
}
