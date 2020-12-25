package templatestree.后台服务所用缓存.reportpushbytime.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： 金融报表生成，用于控制每个用户每个月什么时间段生成指定车辆的数据主处理流程
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class ReportpushbytimeService {

    @Autowired
    private ReportpushbytimeDao reportpushbytimeDao;
    @Autowired
    private ReportpushbytimeCache reportpushbytimeCache;
    @Value(" ${reportpushbytimeEnabled:false}")
    private Boolean reportpushbytimeEnabled = false;

    /**
    * 数据处理的队列
    */
    private ConcurrentLinkedQueue<ReportpushbytimeCacheDO> dataQueue = new ConcurrentLinkedQueue();

    public void setReportpushbytimeEnabled(Boolean reportpushbytimeEnabled) {
        this.reportpushbytimeEnabled = reportpushbytimeEnabled;
    }

    /**
     * 数据处理
     */
    public void addQueue(String simNo, DeviceMsg dm){
        if(reportpushbytimeEnabled){
            try {
                ReportpushbytimeCacheDO  reportpushbytimeCacheDO = reportpushbytimeCache.getconfig(simNo);
                if(reportpushbytimeCacheDO!=null){
                      dataQueue.add(reportpushbytimeCacheDO);
                }
            } catch (Exception e) {
                log.error("处理 金融报表生成，用于控制每个用户每个月什么时间段生成指定车辆的数据流程异常,simNo=" + simNo + ",devicemsg=" + dm, e);
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
            while (reportpushbytimeEnabled) {
                    ReportpushbytimeCacheDO poll = dataQueue.poll();
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
    private void dataprocess(ReportpushbytimeCacheDO poll){

    }


}