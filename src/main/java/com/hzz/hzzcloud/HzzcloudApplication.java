package com.hzz.hzzcloud;

import com.hzz.hzzcloud.spring.SpringUtil;
import com.hzz.hzzcloud.学习.springcloud自带的事件监听以及触发.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableFeignClients
@EnableHystrix
//@MapperScan("com.hzz.hzzcloud.freemarker.main.maparea.dao")
public class HzzcloudApplication {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(HzzcloudApplication.class);
        springApplication.addListeners(new ApplicationEnvironmentPreparedEventListener());
        springApplication.addListeners(new ApplicationPreparedEventListener());
        springApplication.addListeners(new ApplicationReadyEventListener());
        springApplication.addListeners(new ApplicationStartedEventListener());
        springApplication.addListeners(new ApplicationStartingEventListener());
        ConfigurableApplicationContext run = springApplication.run(args);
        SpringUtil.invokeAfterStartedRunner(run);
     //   ConfigurableApplicationContext ctx =  SpringApplication.run(HzzcloudApplication.class, args);



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
