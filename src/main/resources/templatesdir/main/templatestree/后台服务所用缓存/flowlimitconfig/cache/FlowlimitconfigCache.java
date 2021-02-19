package templatestree.后台服务所用缓存.flowlimitconfig.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： 流量阈值配置表 配置信息缓存
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class FlowlimitconfigCache {

    @Autowired
    private FlowlimitconfigDao flowlimitconfigDao;


    /*用于存放simNo对应的配置*/
    private ConcurrentHashMap<String, List<FlowlimitconfigCacheDO>> simNoConfigCache = new ConcurrentHashMap<>();


    @Value(" ${flowlimitconfigEnabled:false}")
    private Boolean flowlimitconfigEnabled = false;

    public void setFlowlimitconfigEnabled(Boolean flowlimitconfigEnabled) {
        this.flowlimitconfigEnabled = flowlimitconfigEnabled;
    }


    /**
    * 根据simNo获取到配置
    */
    public List<FlowlimitconfigCacheDO> getconfig(String simNo){
        List<FlowlimitconfigCacheDO> dom=null;
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
                if (flowlimitconfigEnabled) {
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
        ConcurrentHashMap<String,List<FlowlimitconfigCacheDO>> simNoConfigCacheTemp = new ConcurrentHashMap<>();
        List<FlowlimitconfigByVehicleDO> flowlimitconfigByVehicleDOS = flowlimitconfigDao.searchconfig();
        if (flowlimitconfigByVehicleDOS != null && flowlimitconfigByVehicleDOS.size() > 0) {
            for (FlowlimitconfigByVehicleDO flowlimitconfigByVehicleDO : flowlimitconfigByVehicleDOS) {
                String simNo = flowlimitconfigByVehicleDO.getSimNo();
                FlowlimitconfigCacheDO flowlimitconfigCacheDO =new FlowlimitconfigCacheDO();
                BeanUtils.copyProperties(flowlimitconfigByVehicleDO,flowlimitconfigCacheDO);
                 List<FlowlimitconfigCacheDO> flowlimitconfigCacheDOs=new ArrayList();
                if(simNoConfigCacheTemp.containsKey(simNo)){
                 flowlimitconfigCacheDOs=simNoConfigCacheTemp.get(simNo);
                 flowlimitconfigCacheDOs.add(flowlimitconfigCacheDO);
                }
                simNoConfigCacheTemp.put(simNo, flowlimitconfigCacheDOs);
            }
        }
        simNoConfigCache = simNoConfigCacheTemp;//将simNo每个对应转发的配置加入到其中
        log.debug("流量阈值配置表缓存成功");
    }




}