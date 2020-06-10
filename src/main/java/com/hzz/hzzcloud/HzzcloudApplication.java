package com.hzz.hzzcloud;

import com.hzz.hzzcloud.spring.SpringUtil;
import net.fxft.ascswebcommon.service.impl.UserVehicleRefCacheService;
import net.fxft.gateway.device.DeviceManager;
import net.fxft.gateway.device.IDeviceManager;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

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


}
