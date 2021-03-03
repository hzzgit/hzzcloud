package templatestree.后台服务所用缓存.vehicle.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： 车辆表 配置信息缓存
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class VehicleCache {

    @Autowired
    private VehicleDao vehicleDao;


    /*用于存放simNo对应的配置*/
    private ConcurrentHashMap<String, List<VehicleCacheDO>> simNoConfigCache = new ConcurrentHashMap<>();


    @Value(" ${vehicleEnabled:false}")
    private Boolean vehicleEnabled = false;

    public void setVehicleEnabled(Boolean vehicleEnabled) {
        this.vehicleEnabled = vehicleEnabled;
    }


    /**
    * 根据simNo获取到配置
    */
    public List<VehicleCacheDO> getconfig(String simNo){
        List<VehicleCacheDO> dom=null;
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
                if (vehicleEnabled) {
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
        ConcurrentHashMap<String,List<VehicleCacheDO>> simNoConfigCacheTemp = new ConcurrentHashMap<>();
        List<VehicleByVehicleDO> vehicleByVehicleDOS = vehicleDao.searchconfig();
        if (vehicleByVehicleDOS != null && vehicleByVehicleDOS.size() > 0) {
            for (VehicleByVehicleDO vehicleByVehicleDO : vehicleByVehicleDOS) {
                String simNo = vehicleByVehicleDO.getSimNo();
                VehicleCacheDO vehicleCacheDO =new VehicleCacheDO();
                BeanUtils.copyProperties(vehicleByVehicleDO,vehicleCacheDO);
                 List<VehicleCacheDO> vehicleCacheDOs=new ArrayList();
                if(simNoConfigCacheTemp.containsKey(simNo)){
                 vehicleCacheDOs=simNoConfigCacheTemp.get(simNo);
                 vehicleCacheDOs.add(vehicleCacheDO);
                }
                simNoConfigCacheTemp.put(simNo, vehicleCacheDOs);
            }
        }
        simNoConfigCache = simNoConfigCacheTemp;//将simNo每个对应转发的配置加入到其中
        log.debug("车辆表缓存成功");
    }




}