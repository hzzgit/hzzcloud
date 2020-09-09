package com.hzz.hzzcloud;

import com.hzz.hzzcloud.spring.SpringUtil;
import net.fxft.ascswebcommon.service.impl.UserVehicleRefCacheService;
import net.fxft.gateway.device.DeviceManager;
import net.fxft.gateway.device.IDeviceManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@MapperScan("com.hzz.hzzcloud.freemarker.main.maparea.dao")
public class HzzcloudApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx =  SpringApplication.run(HzzcloudApplication.class, args);
       SpringUtil.invokeAfterStartedRunner(ctx);
    }

    @Bean
    @ConditionalOnMissingBean
    public IDeviceManager createDeviceManager() {
        return new DeviceManager();
    }

    @Bean
    @ConditionalOnMissingBean
    public UserVehicleRefCacheService createUserVehicleRefCacheService() {
        return new UserVehicleRefCacheService();
    }

    @LoadBalanced
    @Bean
    @ConditionalOnMissingBean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

//    @Bean("pikaConfig")
//    @ConfigurationProperties(prefix = "fxft.pika")
//    @ConditionalOnMissingBean
//    public RedisConfig createPikaConfig() {
//        return new RedisConfig();
//    }
//
//    @Bean("pikaUtil")
//    @ConditionalOnMissingBean
//    public RedisUtil createPikaUtil(@Qualifier("pikaConfig") RedisConfig config) {
//        RedisAutoConfiguration rac = new RedisAutoConfiguration();
//        return rac.createRedisUtil(rac.createJedisPool(config));
//    }

}
