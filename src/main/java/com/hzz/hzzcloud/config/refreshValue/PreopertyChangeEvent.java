package com.hzz.hzzcloud.config.refreshValue;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/3 9:47
 */

public class PreopertyChangeEvent {
    private String resourceName;
    private String prepName;
    private String oldValue;
    private String newValue;

    public PreopertyChangeEvent(String resourceName, String prepName, String oldValue, String newValue) {
        this.resourceName = resourceName;
        this.prepName = prepName;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public String getPrepName() {
        return this.prepName;
    }

    public String getOldValue() {
        return this.oldValue;
    }

    public String getNewValue() {
        return this.newValue;
    }

    public String getResourceName() {
        return this.resourceName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PreopertyChangeEvent{");
        sb.append("resourceName='").append(this.resourceName).append('\'');
        sb.append(", prepName='").append(this.prepName).append('\'');
        sb.append(", oldValue='").append(this.oldValue).append('\'');
        sb.append(", newValue='").append(this.newValue).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
