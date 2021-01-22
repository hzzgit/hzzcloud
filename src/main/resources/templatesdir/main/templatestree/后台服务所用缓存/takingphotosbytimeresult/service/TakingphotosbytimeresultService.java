package templatestree.后台服务所用缓存.takingphotosbytimeresult.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： 定时拍照结果表(总表)主处理流程
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class TakingphotosbytimeresultService {

    @Autowired
    private TakingphotosbytimeresultDao takingphotosbytimeresultDao;
    @Autowired
    private TakingphotosbytimeresultCache takingphotosbytimeresultCache;
    @Value(" ${takingphotosbytimeresultEnabled:false}")
    private Boolean takingphotosbytimeresultEnabled = false;

    /**
    * 数据处理的队列
    */
    private ConcurrentLinkedQueue<TakingphotosbytimeresultCacheDO> dataQueue = new ConcurrentLinkedQueue();

    public void setTakingphotosbytimeresultEnabled(Boolean takingphotosbytimeresultEnabled) {
        this.takingphotosbytimeresultEnabled = takingphotosbytimeresultEnabled;
    }

    /**
     * 数据处理
     */
    public void addQueue(String simNo, DeviceMsg dm){
        if(takingphotosbytimeresultEnabled){
            try {
                TakingphotosbytimeresultCacheDO  takingphotosbytimeresultCacheDO = takingphotosbytimeresultCache.getconfig(simNo);
                if(takingphotosbytimeresultCacheDO!=null){
                      dataQueue.add(takingphotosbytimeresultCacheDO);
                }
            } catch (Exception e) {
                log.error("处理 定时拍照结果表(总表)流程异常,simNo=" + simNo + ",devicemsg=" + dm, e);
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
            while (takingphotosbytimeresultEnabled) {
                    TakingphotosbytimeresultCacheDO poll = dataQueue.poll();
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
    private void dataprocess(TakingphotosbytimeresultCacheDO poll){

    }


}