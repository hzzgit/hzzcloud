package com.hzz.hzzcloud.controller.测试自动修改配置参数;

import com.hzz.hzzcloud.config.refreshValue.RefreshValue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/3 9:59
 */
@Service
@RefreshValue
public class AutoValueService {

    @Value("${name}")
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    //@PostConstruct
    private void init(){
             new Thread(()->{
                             while (true){
                                 System.out.println("自动修改的参数name为:"+name);
                                 try {
                                     Thread.sleep(1000);
                                 } catch (InterruptedException e) {
                                     e.printStackTrace();
                                 }
                             }
                     }).start();
    }
}
