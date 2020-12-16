package templatestree.后台服务所用缓存.vehiclereport.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： 企业车辆信息表维护表主处理流程
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class VehiclereportService {

    @Autowired
    private VehiclereportDao vehiclereportDao;
    @Autowired
    private VehiclereportCache vehiclereportCache;
    @Value(" ${vehiclereportEnabled:false}")
    private Boolean vehiclereportEnabled = false;

    /**
    * 数据处理的队列
    */
    private ConcurrentLinkedQueue<VehiclereportCacheDO> dataQueue = new ConcurrentLinkedQueue();

    public void setVehiclereportEnabled(Boolean vehiclereportEnabled) {
        this.vehiclereportEnabled = vehiclereportEnabled;
    }

    /**
     * 数据处理
     */
    public void addQueue(String simNo, DeviceMsg dm){
        if(vehiclereportEnabled){
            try {
                VehiclereportCacheDO  vehiclereportCacheDO = vehiclereportCache.getconfig(simNo);
                if(vehiclereportCacheDO!=null){
                      dataQueue.add(vehiclereportCacheDO);
                }
            } catch (Exception e) {
                log.error("处理 企业车辆信息表维护表流程异常,simNo=" + simNo + ",devicemsg=" + dm, e);
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
            while (vehiclereportEnabled) {
                    VehiclereportCacheDO poll = dataQueue.poll();
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
    private void dataprocess(VehiclereportCacheDO poll){

    }


}