package com.hzz.hzzcloud.熔断降级;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/2/19 16:11
 */
@RequestMapping("hys")
@RestController
public class HystrixController {

    @HystrixCommand(fallbackMethod = "fallback")
    @RequestMapping("/hello")
    public String hello(){
        System.out.println("测试"+Thread.currentThread().getName());
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "hello";
    }

     String fallback(){
        return "繁忙";
     }
}
