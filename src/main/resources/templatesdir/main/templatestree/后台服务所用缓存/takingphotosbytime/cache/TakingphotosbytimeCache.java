package templatestree.后台服务所用缓存.takingphotosbytime.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： 定时拍照配置表 配置信息缓存
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class TakingphotosbytimeCache {

    @Autowired
    private TakingphotosbytimeDao takingphotosbytimeDao;


    /*用于存放simNo对应的配置*/
    private ConcurrentHashMap<String, List<TakingphotosbytimeCacheDO>> simNoConfigCache = new ConcurrentHashMap<>();


    @Value(" ${takingphotosbytimeEnabled:false}")
    private Boolean takingphotosbytimeEnabled = false;

    public void setTakingphotosbytimeEnabled(Boolean takingphotosbytimeEnabled) {
        this.takingphotosbytimeEnabled = takingphotosbytimeEnabled;
    }


    /**
    * 根据simNo获取到配置
    */
    public List<TakingphotosbytimeCacheDO> getconfig(String simNo){
        List<TakingphotosbytimeCacheDO> dom=null;
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
                if (takingphotosbytimeEnabled) {
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
        ConcurrentHashMap<String,List<TakingphotosbytimeCacheDO>> simNoConfigCacheTemp = new ConcurrentHashMap<>();
        List<TakingphotosbytimeByVehicleDO> takingphotosbytimeByVehicleDOS = takingphotosbytimeDao.searchconfig();
        if (takingphotosbytimeByVehicleDOS != null && takingphotosbytimeByVehicleDOS.size() > 0) {
            for (TakingphotosbytimeByVehicleDO takingphotosbytimeByVehicleDO : takingphotosbytimeByVehicleDOS) {
                String simNo = takingphotosbytimeByVehicleDO.getSimNo();
                TakingphotosbytimeCacheDO takingphotosbytimeCacheDO =new TakingphotosbytimeCacheDO();
                BeanUtils.copyProperties(takingphotosbytimeByVehicleDO,takingphotosbytimeCacheDO);
                 List<TakingphotosbytimeCacheDO> takingphotosbytimeCacheDOs=new ArrayList();
                if(simNoConfigCacheTemp.containsKey(simNo)){
                 takingphotosbytimeCacheDOs=simNoConfigCacheTemp.get(simNo);
                 takingphotosbytimeCacheDOs.add(takingphotosbytimeCacheDO);
                }
                simNoConfigCacheTemp.put(simNo, takingphotosbytimeCacheDOs);
            }
        }
        simNoConfigCache = simNoConfigCacheTemp;//将simNo每个对应转发的配置加入到其中
        log.debug("定时拍照配置表缓存成功");
    }




}