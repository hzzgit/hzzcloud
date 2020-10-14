package com.hzz.hzzcloud.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.fxft.ascswebcommon.web.util.JsonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
@Slf4j
@RequestMapping("/test")

public class VehicleAction extends GeneAction{

    @Autowired
    private ApplicationContext applicationContext;


    public static void main(String[] args) {
        Number altitude;
        short a=1;
        altitude=a;
        System.out.println(altitude);
    }

    @PostConstruct
    private void init(){
             new Thread(()->{
                             while (true){
                                 try {
                                     String name=applicationContext.getEnvironment().getRequiredProperty("name");
                                     System.out.println("动态刷新值："+name);
                                     Thread.sleep(1000);
                                 } catch (InterruptedException e) {
                                     e.printStackTrace();
                                 }
                             }
                     }).start();
    }

    /**
     * 我就是测试下
     */
    @Value("${config.jwtSecret:}")
    private String jwtSecret;

    @Autowired
    protected HttpServletRequest request;

    @ResponseBody
    @RequestMapping("/abc.action")
    public JsonMessage abc() {
        String userName = request.getHeader("userName")+"5"+jwtSecret;
        return  new JsonMessage(true,userName);
    }

    @ResponseBody
    @RequestMapping("/alarm.action")
    public ResultVo alarm(
            @RequestBody(required = false) AlarmGranterEvent alarmGranterEvent
    ) {
        Map params = getParams();
        System.out.println(JSON.toJSONString(params));
        ResultVo resultVo = new ResultVo();
        resultVo.setMsg("请求成");
        resultVo.setStatus(1);
        return  resultVo;
    }
}
