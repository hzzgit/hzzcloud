package ${packpath};

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： ${tableconment}主处理流程
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class ${entityName}Service {

    @Autowired
    private ${entityName}Dao ${tablename}Dao;
    @Autowired
    private ${entityName}Cache ${tablename}Cache;
    @Value(" <#noparse>${</#noparse>${tablename}Enabled:false}")
    private Boolean ${tablename}Enabled = false;

    /**
    * 数据处理的队列
    */
    private ConcurrentLinkedQueue<${entityName}CacheDO> dataQueue = new ConcurrentLinkedQueue();

    public void set${entityName}Enabled(Boolean ${tablename}Enabled) {
        this.${tablename}Enabled = ${tablename}Enabled;
    }

    /**
     * 数据处理
     */
    public void addQueue(String simNo, DeviceMsg dm){
        if(${tablename}Enabled){
            try {
                ${entityName}CacheDO  ${tablename}CacheDO = ${tablename}Cache.getconfig(simNo);
                if(${tablename}CacheDO!=null){
                      dataQueue.add(${tablename}CacheDO);
                }
            } catch (Exception e) {
                log.error("处理 ${tableconment}流程异常,simNo=" + simNo + ",devicemsg=" + dm, e);
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
            while (${tablename}Enabled) {
                    ${entityName}CacheDO poll = dataQueue.poll();
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
    private void dataprocess(${entityName}CacheDO poll){

    }


}