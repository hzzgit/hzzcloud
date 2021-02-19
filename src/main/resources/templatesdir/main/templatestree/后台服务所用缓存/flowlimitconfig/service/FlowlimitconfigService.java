package templatestree.后台服务所用缓存.flowlimitconfig.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： 流量阈值配置表主处理流程
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class FlowlimitconfigService {

    @Autowired
    private FlowlimitconfigDao flowlimitconfigDao;
    @Autowired
    private FlowlimitconfigCache flowlimitconfigCache;
    @Value(" ${flowlimitconfigEnabled:false}")
    private Boolean flowlimitconfigEnabled = false;

    /**
    * 数据处理的队列
    */
    private ConcurrentLinkedQueue<FlowlimitconfigCacheDO> dataQueue = new ConcurrentLinkedQueue();

    public void setFlowlimitconfigEnabled(Boolean flowlimitconfigEnabled) {
        this.flowlimitconfigEnabled = flowlimitconfigEnabled;
    }

    /**
     * 数据处理
     */
    public void addQueue(String simNo, DeviceMsg dm){
        if(flowlimitconfigEnabled){
            try {
                FlowlimitconfigCacheDO  flowlimitconfigCacheDO = flowlimitconfigCache.getconfig(simNo);
                if(flowlimitconfigCacheDO!=null){
                      dataQueue.add(flowlimitconfigCacheDO);
                }
            } catch (Exception e) {
                log.error("处理 流量阈值配置表流程异常,simNo=" + simNo + ",devicemsg=" + dm, e);
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
            while (flowlimitconfigEnabled) {
                    FlowlimitconfigCacheDO poll = dataQueue.poll();
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
    private void dataprocess(FlowlimitconfigCacheDO poll){

    }


}