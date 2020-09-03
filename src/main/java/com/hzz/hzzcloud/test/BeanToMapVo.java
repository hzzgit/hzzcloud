package com.hzz.hzzcloud.test;

import java.util.Date;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/31 17:36
 */
public class BeanToMapVo {
    private Date startTime;
    private Integer co;
    private List<String> alarmType;
    private String[] alarmsource;
    private String name;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Integer getCo() {
        return co;
    }

    public void setCo(Integer co) {
        this.co = co;
    }

    public List<String> getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(List<String> alarmType) {
        this.alarmType = alarmType;
    }

    public String[] getAlarmsource() {
        return alarmsource;
    }

    public void setAlarmsource(String[] alarmsource) {
        this.alarmsource = alarmsource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
