package com.hzz.hzzcloud.config.refreshValue;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/3 9:43
 */

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(
        prefix = "hzz.refresh-value"
)
public class RefreshValueConfig {
    private int checkInterval = 30;
    private boolean enabled = true;

    public RefreshValueConfig() {
    }

    public int getCheckInterval() {
        return this.checkInterval;
    }

    public void setCheckInterval(int checkInterval) {
        this.checkInterval = checkInterval;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}

