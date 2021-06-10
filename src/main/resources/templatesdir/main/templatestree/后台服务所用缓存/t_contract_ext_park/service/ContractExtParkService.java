package templatestree.后台服务所用缓存.t_contract_ext_park.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： 车场合同附加协议主处理流程
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class ContractExtParkService {

    @Autowired
    private ContractExtParkDao t_contract_ext_parkDao;
    @Autowired
    private ContractExtParkCache t_contract_ext_parkCache;
    @Value(" ${t_contract_ext_parkEnabled:false}")
    private Boolean t_contract_ext_parkEnabled = false;

    /**
    * 数据处理的队列
    */
    private ConcurrentLinkedQueue<ContractExtParkCacheDO> dataQueue = new ConcurrentLinkedQueue();

    public void setContractExtParkEnabled(Boolean t_contract_ext_parkEnabled) {
        this.t_contract_ext_parkEnabled = t_contract_ext_parkEnabled;
    }

    /**
     * 数据处理
     */
    public void addQueue(String simNo, DeviceMsg dm){
        if(t_contract_ext_parkEnabled){
            try {
                ContractExtParkCacheDO  t_contract_ext_parkCacheDO = t_contract_ext_parkCache.getconfig(simNo);
                if(t_contract_ext_parkCacheDO!=null){
                      dataQueue.add(t_contract_ext_parkCacheDO);
                }
            } catch (Exception e) {
                log.error("处理 车场合同附加协议流程异常,simNo=" + simNo + ",devicemsg=" + dm, e);
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
            while (t_contract_ext_parkEnabled) {
                    ContractExtParkCacheDO poll = dataQueue.poll();
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
    private void dataprocess(ContractExtParkCacheDO poll){

    }


}