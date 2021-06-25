package com.hzz.hzzcloud;

import com.hzz.hzzcloud.spring.SpringUtil;
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
        ConfigurableApplicationContext run = springApplication.run(args);
        SpringUtil.invokeAfterStartedRunner(run);
     //   ConfigurableApplicationContext ctx =  SpringApplication.run(HzzcloudApplication.class, args);

        System.out.println("分支2操作了");


    }

    @LoadBalanced
    @Bean
    @ConditionalOnMissingBean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }


}
