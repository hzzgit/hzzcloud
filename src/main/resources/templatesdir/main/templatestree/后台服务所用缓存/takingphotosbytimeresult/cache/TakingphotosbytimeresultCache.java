package templatestree.后台服务所用缓存.takingphotosbytimeresult.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： 定时拍照结果表(总表) 配置信息缓存
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class TakingphotosbytimeresultCache {

    @Autowired
    private TakingphotosbytimeresultDao takingphotosbytimeresultDao;


    /*用于存放simNo对应的配置*/
    private ConcurrentHashMap<String, List<TakingphotosbytimeresultCacheDO>> simNoConfigCache = new ConcurrentHashMap<>();


    @Value(" ${takingphotosbytimeresultEnabled:false}")
    private Boolean takingphotosbytimeresultEnabled = false;

    public void setTakingphotosbytimeresultEnabled(Boolean takingphotosbytimeresultEnabled) {
        this.takingphotosbytimeresultEnabled = takingphotosbytimeresultEnabled;
    }


    /**
    * 根据simNo获取到配置
    */
    public List<TakingphotosbytimeresultCacheDO> getconfig(String simNo){
        List<TakingphotosbytimeresultCacheDO> dom=null;
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
                if (takingphotosbytimeresultEnabled) {
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
        ConcurrentHashMap<String,List<TakingphotosbytimeresultCacheDO>> simNoConfigCacheTemp = new ConcurrentHashMap<>();
        List<TakingphotosbytimeresultByVehicleDO> takingphotosbytimeresultByVehicleDOS = takingphotosbytimeresultDao.searchconfig();
        if (takingphotosbytimeresultByVehicleDOS != null && takingphotosbytimeresultByVehicleDOS.size() > 0) {
            for (TakingphotosbytimeresultByVehicleDO takingphotosbytimeresultByVehicleDO : takingphotosbytimeresultByVehicleDOS) {
                String simNo = takingphotosbytimeresultByVehicleDO.getSimNo();
                TakingphotosbytimeresultCacheDO takingphotosbytimeresultCacheDO =new TakingphotosbytimeresultCacheDO();
                BeanUtils.copyProperties(takingphotosbytimeresultByVehicleDO,takingphotosbytimeresultCacheDO);
                 List<TakingphotosbytimeresultCacheDO> takingphotosbytimeresultCacheDOs=new ArrayList();
                if(simNoConfigCacheTemp.containsKey(simNo)){
                 takingphotosbytimeresultCacheDOs=simNoConfigCacheTemp.get(simNo);
                 takingphotosbytimeresultCacheDOs.add(takingphotosbytimeresultCacheDO);
                }
                simNoConfigCacheTemp.put(simNo, takingphotosbytimeresultCacheDOs);
            }
        }
        simNoConfigCache = simNoConfigCacheTemp;//将simNo每个对应转发的配置加入到其中
        log.debug("定时拍照结果表(总表)缓存成功");
    }




}