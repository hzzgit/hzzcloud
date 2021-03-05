package templatestree.后台服务所用缓存.config_table_filed.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： 用于管理平台配置表的信息的表字段配置表 配置信息缓存
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class Config_table_filedCache {

    @Autowired
    private Config_table_filedDao config_table_filedDao;


    /*用于存放simNo对应的配置*/
    private ConcurrentHashMap<String, List<Config_table_filedCacheDO>> simNoConfigCache = new ConcurrentHashMap<>();


    @Value(" ${config_table_filedEnabled:false}")
    private Boolean config_table_filedEnabled = false;

    public void setConfig_table_filedEnabled(Boolean config_table_filedEnabled) {
        this.config_table_filedEnabled = config_table_filedEnabled;
    }


    /**
    * 根据simNo获取到配置
    */
    public List<Config_table_filedCacheDO> getconfig(String simNo){
        List<Config_table_filedCacheDO> dom=null;
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
                if (config_table_filedEnabled) {
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
        ConcurrentHashMap<String,List<Config_table_filedCacheDO>> simNoConfigCacheTemp = new ConcurrentHashMap<>();
        List<Config_table_filedByVehicleDO> config_table_filedByVehicleDOS = config_table_filedDao.searchconfig();
        if (config_table_filedByVehicleDOS != null && config_table_filedByVehicleDOS.size() > 0) {
            for (Config_table_filedByVehicleDO config_table_filedByVehicleDO : config_table_filedByVehicleDOS) {
                String simNo = config_table_filedByVehicleDO.getSimNo();
                Config_table_filedCacheDO config_table_filedCacheDO =new Config_table_filedCacheDO();
                BeanUtils.copyProperties(config_table_filedByVehicleDO,config_table_filedCacheDO);
                 List<Config_table_filedCacheDO> config_table_filedCacheDOs=new ArrayList();
                if(simNoConfigCacheTemp.containsKey(simNo)){
                 config_table_filedCacheDOs=simNoConfigCacheTemp.get(simNo);
                 config_table_filedCacheDOs.add(config_table_filedCacheDO);
                }
                simNoConfigCacheTemp.put(simNo, config_table_filedCacheDOs);
            }
        }
        simNoConfigCache = simNoConfigCacheTemp;//将simNo每个对应转发的配置加入到其中
        log.debug("用于管理平台配置表的信息的表字段配置表缓存成功");
    }




}