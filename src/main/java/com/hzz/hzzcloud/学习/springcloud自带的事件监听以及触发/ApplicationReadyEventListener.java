package com.hzz.hzzcloud.学习.springcloud自带的事件监听以及触发;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;

/**
 * @author ：hzz
 * @description：在应用程序和命令行运行器（command-line runner）被调用之后，将发出 ApplicationReadyEvent，该事件用于通知应用已经准备处理请求。
 * @date ：2021/2/6 14:44
 */
@Service
public class ApplicationReadyEventListener implements ApplicationListener<ApplicationReadyEvent> {
    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {

        System.out.println("ApplicationReadyEvent:"+event);
    }
}
