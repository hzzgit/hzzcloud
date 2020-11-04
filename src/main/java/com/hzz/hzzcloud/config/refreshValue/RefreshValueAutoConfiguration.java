package com.hzz.hzzcloud.config.refreshValue;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/3 9:45
 */
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties({RefreshValueConfig.class})
public class RefreshValueAutoConfiguration {
    public RefreshValueAutoConfiguration() {
    }

    @Bean(
            destroyMethod = "destroy"
    )
    public RefreshValueProcesser createAutoRefreshConfigManager(RefreshValueConfig refreshValueConfig) {
        return new RefreshValueProcesser(refreshValueConfig);
    }
}
