package templatestree.后台服务所用缓存.config_table.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： 用于管理平台配置表的信息的表结构存储表主处理流程
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class Config_tableService {

    @Autowired
    private Config_tableDao config_tableDao;
    @Autowired
    private Config_tableCache config_tableCache;
    @Value(" ${config_tableEnabled:false}")
    private Boolean config_tableEnabled = false;

    /**
    * 数据处理的队列
    */
    private ConcurrentLinkedQueue<Config_tableCacheDO> dataQueue = new ConcurrentLinkedQueue();

    public void setConfig_tableEnabled(Boolean config_tableEnabled) {
        this.config_tableEnabled = config_tableEnabled;
    }

    /**
     * 数据处理
     */
    public void addQueue(String simNo, DeviceMsg dm){
        if(config_tableEnabled){
            try {
                Config_tableCacheDO  config_tableCacheDO = config_tableCache.getconfig(simNo);
                if(config_tableCacheDO!=null){
                      dataQueue.add(config_tableCacheDO);
                }
            } catch (Exception e) {
                log.error("处理 用于管理平台配置表的信息的表结构存储表流程异常,simNo=" + simNo + ",devicemsg=" + dm, e);
            }
        }
    }

    /**
    * 消费队列里面的数据并进行处理
    */
    @PostConstruct
    private void init(){

        new Thread(() -> {
         while (true) {
            while (config_tableEnabled) {
                    Config_tableCacheDO poll = dataQueue.poll();
                    while (poll != null) {
                        dataprocess(poll);
                        poll = dataQueue.poll();
                    }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
          }
        }).start();

    }

    /**
    * 数据实际处理流程
    */
    private void dataprocess(Config_tableCacheDO poll){

    }


}