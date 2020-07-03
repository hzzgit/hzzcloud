package com.hzz.hzzcloud.controller;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AlarmGranterEvent implements Serializable {

    private String plateNo;
    private String simNo;
    private String alarmType;
    private String alarmSource;
    private Date alarmTime;
    private double speed;
    private double latitude;
    private double longitude;
    private int plateColor;

    @Override
    public String toString() {
        return "AlarmGranterEvent{" +
                "plateNo='" + plateNo + '\'' +
                ", simNo='" + simNo + '\'' +
                ", alarmType='" + alarmType + '\'' +
                ", alarmSource='" + alarmSource + '\'' +
                ", alarmTime=" + alarmTime +
                ", speed=" + speed +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", plateColor=" + plateColor +
                '}';
    }
}
