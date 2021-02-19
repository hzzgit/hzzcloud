package com.hzz.hzzcloud.学习.springcloud自带的事件监听以及触发;

import org.springframework.context.ApplicationEvent;

public class CampPointEvent extends ApplicationEvent {

    /**
     * 积分入账
     */
    private String campPoint;

    public String getCampPoint() {
        return campPoint;
    }

    public CampPointEvent(String source) {
        super(source);
        this.campPoint=source;
    }
}