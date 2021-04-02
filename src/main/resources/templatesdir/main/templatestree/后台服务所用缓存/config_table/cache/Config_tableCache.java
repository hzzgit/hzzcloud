package templatestree.后台服务所用缓存.config_table.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： 用于管理平台配置表的信息的表结构存储表 配置信息缓存
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class Config_tableCache {

    @Autowired
    private Config_tableDao config_tableDao;


    /*用于存放simNo对应的配置*/
    private ConcurrentHashMap<String, List<Config_tableCacheDO>> simNoConfigCache = new ConcurrentHashMap<>();


    @Value(" ${config_tableEnabled:false}")
    private Boolean config_tableEnabled = false;

    public void setConfig_tableEnabled(Boolean config_tableEnabled) {
        this.config_tableEnabled = config_tableEnabled;
    }


    /**
    * 根据simNo获取到配置
    */
    public List<Config_tableCacheDO> getconfig(String simNo){
        List<Config_tableCacheDO> dom=null;
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
                if (config_tableEnabled) {
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
        ConcurrentHashMap<String,List<Config_tableCacheDO>> simNoConfigCacheTemp = new ConcurrentHashMap<>();
        List<Config_tableByVehicleDO> config_tableByVehicleDOS = config_tableDao.searchconfig();
        if (config_tableByVehicleDOS != null && config_tableByVehicleDOS.size() > 0) {
            for (Config_tableByVehicleDO config_tableByVehicleDO : config_tableByVehicleDOS) {
                String simNo = config_tableByVehicleDO.getSimNo();
                Config_tableCacheDO config_tableCacheDO =new Config_tableCacheDO();
                BeanUtils.copyProperties(config_tableByVehicleDO,config_tableCacheDO);
                 List<Config_tableCacheDO> config_tableCacheDOs=new ArrayList();
                if(simNoConfigCacheTemp.containsKey(simNo)){
                 config_tableCacheDOs=simNoConfigCacheTemp.get(simNo);
                 config_tableCacheDOs.add(config_tableCacheDO);
                }
                simNoConfigCacheTemp.put(simNo, config_tableCacheDOs);
            }
        }
        simNoConfigCache = simNoConfigCacheTemp;//将simNo每个对应转发的配置加入到其中
        log.debug("用于管理平台配置表的信息的表结构存储表缓存成功");
    }




}