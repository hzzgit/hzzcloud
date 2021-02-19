package com.hzz.hzzcloud.学习.springcloud自带的事件监听以及触发;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CampPointPublisher {

    @Autowired
    private ApplicationEventPublisher publisher;


    /**
     * 积分入账广播
     *
     * @param campPointRecordBO
     */
    public void publish(String campPointRecordBO) {
        try {
            publisher.publishEvent(new CampPointEvent(campPointRecordBO));
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
        }
    }
}