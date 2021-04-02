package templatestree.后台服务所用缓存.config_table_filed.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： 用于管理平台配置表的信息的表字段配置表主处理流程
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class Config_table_filedService {

    @Autowired
    private Config_table_filedDao config_table_filedDao;
    @Autowired
    private Config_table_filedCache config_table_filedCache;
    @Value(" ${config_table_filedEnabled:false}")
    private Boolean config_table_filedEnabled = false;

    /**
    * 数据处理的队列
    */
    private ConcurrentLinkedQueue<Config_table_filedCacheDO> dataQueue = new ConcurrentLinkedQueue();

    public void setConfig_table_filedEnabled(Boolean config_table_filedEnabled) {
        this.config_table_filedEnabled = config_table_filedEnabled;
    }

    /**
     * 数据处理
     */
    public void addQueue(String simNo, DeviceMsg dm){
        if(config_table_filedEnabled){
            try {
                Config_table_filedCacheDO  config_table_filedCacheDO = config_table_filedCache.getconfig(simNo);
                if(config_table_filedCacheDO!=null){
                      dataQueue.add(config_table_filedCacheDO);
                }
            } catch (Exception e) {
                log.error("处理 用于管理平台配置表的信息的表字段配置表流程异常,simNo=" + simNo + ",devicemsg=" + dm, e);
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
            while (config_table_filedEnabled) {
                    Config_table_filedCacheDO poll = dataQueue.poll();
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
    private void dataprocess(Config_table_filedCacheDO poll){

    }


}