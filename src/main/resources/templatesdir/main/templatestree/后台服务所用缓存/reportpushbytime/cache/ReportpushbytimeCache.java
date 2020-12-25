package templatestree.后台服务所用缓存.reportpushbytime.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： 金融报表生成，用于控制每个用户每个月什么时间段生成指定车辆的数据 配置信息缓存
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class ReportpushbytimeCache {

    @Autowired
    private ReportpushbytimeDao reportpushbytimeDao;


    /*用于存放simNo对应的配置*/
    private ConcurrentHashMap<String, List<ReportpushbytimeCacheDO>> simNoConfigCache = new ConcurrentHashMap<>();


    @Value(" ${reportpushbytimeEnabled:false}")
    private Boolean reportpushbytimeEnabled = false;

    public void setReportpushbytimeEnabled(Boolean reportpushbytimeEnabled) {
        this.reportpushbytimeEnabled = reportpushbytimeEnabled;
    }


    /**
    * 根据simNo获取到配置
    */
    public List<ReportpushbytimeCacheDO> getconfig(String simNo){
        List<ReportpushbytimeCacheDO> dom=null;
        if(simNoConfigCache!=null&&simNoConfigCache.containsKey(simNo)){
            dom=simNoConfigCache.get(simNo);
        }
        return dom;
    }


    /**
     * 缓存启动方法
     */
    @PostConstruct
    private void init() {
        new Thread(() -> {
            while (true) {
                if (reportpushbytimeEnabled) {
                    configcache();
                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


   /**
    *进行配置的缓存
    */
    private void configcache() {
        ConcurrentHashMap<String,List<ReportpushbytimeCacheDO>> simNoConfigCacheTemp = new ConcurrentHashMap<>();
        List<ReportpushbytimeByVehicleDO> reportpushbytimeByVehicleDOS = reportpushbytimeDao.searchconfig();
        if (reportpushbytimeByVehicleDOS != null && reportpushbytimeByVehicleDOS.size() > 0) {
            for (ReportpushbytimeByVehicleDO reportpushbytimeByVehicleDO : reportpushbytimeByVehicleDOS) {
                String simNo = reportpushbytimeByVehicleDO.getSimNo();
                ReportpushbytimeCacheDO reportpushbytimeCacheDO =new ReportpushbytimeCacheDO();
                BeanUtils.copyProperties(reportpushbytimeByVehicleDO,reportpushbytimeCacheDO);
                 List<ReportpushbytimeCacheDO> reportpushbytimeCacheDOs=new ArrayList();
                if(simNoConfigCacheTemp.containsKey(simNo)){
                 reportpushbytimeCacheDOs=simNoConfigCacheTemp.get(simNo);
                 reportpushbytimeCacheDOs.add(reportpushbytimeCacheDO);
                }
                simNoConfigCacheTemp.put(simNo, reportpushbytimeCacheDOs);
            }
        }
        simNoConfigCache = simNoConfigCacheTemp;//将simNo每个对应转发的配置加入到其中
        log.debug("金融报表生成，用于控制每个用户每个月什么时间段生成指定车辆的数据缓存成功");
    }




}