package templatestree.后台服务所用缓存.vehiclereport.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： 企业车辆信息表维护表 配置信息缓存
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class VehiclereportCache {

    @Autowired
    private VehiclereportDao vehiclereportDao;


    /*用于存放simNo对应的配置*/
    private ConcurrentHashMap<String, List<VehiclereportCacheDO>> simNoConfigCache = new ConcurrentHashMap<>();


    @Value(" ${vehiclereportEnabled:false}")
    private Boolean vehiclereportEnabled = false;

    public void setVehiclereportEnabled(Boolean vehiclereportEnabled) {
        this.vehiclereportEnabled = vehiclereportEnabled;
    }


    /**
    * 根据simNo获取到配置
    */
    public List<VehiclereportCacheDO> getconfig(String simNo){
        List<VehiclereportCacheDO> dom=null;
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
                if (vehiclereportEnabled) {
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
        ConcurrentHashMap<String,List<VehiclereportCacheDO>> simNoConfigCacheTemp = new ConcurrentHashMap<>();
        List<VehiclereportByVehicleDO> vehiclereportByVehicleDOS = vehiclereportDao.searchconfig();
        if (vehiclereportByVehicleDOS != null && vehiclereportByVehicleDOS.size() > 0) {
            for (VehiclereportByVehicleDO vehiclereportByVehicleDO : vehiclereportByVehicleDOS) {
                String simNo = vehiclereportByVehicleDO.getSimNo();
                VehiclereportCacheDO vehiclereportCacheDO =new VehiclereportCacheDO();
                BeanUtils.copyProperties(vehiclereportByVehicleDO,vehiclereportCacheDO);
                 List<VehiclereportCacheDO> vehiclereportCacheDOs=new ArrayList();
                if(simNoConfigCacheTemp.containsKey(simNo)){
                 vehiclereportCacheDOs=simNoConfigCacheTemp.get(simNo);
                 vehiclereportCacheDOs.add(vehiclereportCacheDO);
                }
                simNoConfigCacheTemp.put(simNo, vehiclereportCacheDOs);
            }
        }
        simNoConfigCache = simNoConfigCacheTemp;//将simNo每个对应转发的配置加入到其中
        log.debug("企业车辆信息表维护表缓存成功");
    }




}