package com.hzz.hzzcloud.controller.Validated相关验证测试;

import net.fxft.cloud.redis.RedisConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/11 11:21
 */
@Configuration
public class RedisConfigBySrpingBoot {


    @Bean
    @Primary
    @ConfigurationProperties(
            prefix = "fxft.redis"
    )
    public net.fxft.cloud.redis.RedisConfig createRedisConfig() {
        return new RedisConfig();
    }


}
