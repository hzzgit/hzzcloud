package templatestree.后台服务所用缓存.t_contract_park.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： 车场合同主处理流程
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class ContractParkService {

    @Autowired
    private ContractParkDao t_contract_parkDao;
    @Autowired
    private ContractParkCache t_contract_parkCache;
    @Value(" ${t_contract_parkEnabled:false}")
    private Boolean t_contract_parkEnabled = false;

    /**
    * 数据处理的队列
    */
    private ConcurrentLinkedQueue<ContractParkCacheDO> dataQueue = new ConcurrentLinkedQueue();

    public void setContractParkEnabled(Boolean t_contract_parkEnabled) {
        this.t_contract_parkEnabled = t_contract_parkEnabled;
    }

    /**
     * 数据处理
     */
    public void addQueue(String simNo, DeviceMsg dm){
        if(t_contract_parkEnabled){
            try {
                ContractParkCacheDO  t_contract_parkCacheDO = t_contract_parkCache.getconfig(simNo);
                if(t_contract_parkCacheDO!=null){
                      dataQueue.add(t_contract_parkCacheDO);
                }
            } catch (Exception e) {
                log.error("处理 车场合同流程异常,simNo=" + simNo + ",devicemsg=" + dm, e);
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
            while (t_contract_parkEnabled) {
                    ContractParkCacheDO poll = dataQueue.poll();
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
    private void dataprocess(ContractParkCacheDO poll){

    }


}