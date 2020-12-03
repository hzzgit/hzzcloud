package ${packpath};

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： ${tableconment} 配置信息缓存
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class ${entityName}Cache {

    @Autowired
    private ${entityName}Dao ${tablename}Dao;


    /*用于存放simNo对应的配置*/
    private ConcurrentHashMap<String, List<${entityName}CacheDO>> simNoConfigCache = new ConcurrentHashMap<>();


    @Value(" <#noparse>${</#noparse>${tablename}Enabled:false}")
    private Boolean ${tablename}Enabled = false;

    public void set${entityName}Enabled(Boolean ${tablename}Enabled) {
        this.${tablename}Enabled = ${tablename}Enabled;
    }


    /**
    * 根据simNo获取到配置
    */
    public List<${entityName}CacheDO> getconfig(String simNo){
        List<${entityName}CacheDO> dom=null;
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
                if (${tablename}Enabled) {
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
        ConcurrentHashMap<String,List<${entityName}CacheDO>> simNoConfigCacheTemp = new ConcurrentHashMap<>();
        List<${entityName}ByVehicleDO> ${tablename}ByVehicleDOS = ${tablename}Dao.searchconfig();
        if (${tablename}ByVehicleDOS != null && ${tablename}ByVehicleDOS.size() > 0) {
            for (${entityName}ByVehicleDO ${tablename}ByVehicleDO : ${tablename}ByVehicleDOS) {
                String simNo = ${tablename}ByVehicleDO.getSimNo();
                ${entityName}CacheDO ${tablename}CacheDO =new ${entityName}CacheDO();
                BeanUtils.copyProperties(${tablename}ByVehicleDO,${tablename}CacheDO);
                 List<${entityName}CacheDO> ${tablename}CacheDOs=new ArrayList();
                if(simNoConfigCacheTemp.containsKey(simNo)){
                 ${tablename}CacheDOs=simNoConfigCacheTemp.get(simNo);
                 ${tablename}CacheDOs.add(${tablename}CacheDO);
                }
                simNoConfigCacheTemp.put(simNo, ${tablename}CacheDOs);
            }
        }
        simNoConfigCache = simNoConfigCacheTemp;//将simNo每个对应转发的配置加入到其中
        log.debug("${tableconment}缓存成功");
    }




}