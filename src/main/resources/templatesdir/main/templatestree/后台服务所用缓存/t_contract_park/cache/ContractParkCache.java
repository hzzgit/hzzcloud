package templatestree.后台服务所用缓存.t_contract_park.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： 车场合同 配置信息缓存
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class ContractParkCache {

    @Autowired
    private ContractParkDao t_contract_parkDao;


    /*用于存放simNo对应的配置*/
    private ConcurrentHashMap<String, List<ContractParkCacheDO>> simNoConfigCache = new ConcurrentHashMap<>();


    @Value(" ${t_contract_parkEnabled:false}")
    private Boolean t_contract_parkEnabled = false;

    public void setContractParkEnabled(Boolean t_contract_parkEnabled) {
        this.t_contract_parkEnabled = t_contract_parkEnabled;
    }


    /**
    * 根据simNo获取到配置
    */
    public List<ContractParkCacheDO> getconfig(String simNo){
        List<ContractParkCacheDO> dom=null;
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
                if (t_contract_parkEnabled) {
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
        ConcurrentHashMap<String,List<ContractParkCacheDO>> simNoConfigCacheTemp = new ConcurrentHashMap<>();
        List<ContractParkByVehicleDO> t_contract_parkByVehicleDOS = t_contract_parkDao.searchconfig();
        if (t_contract_parkByVehicleDOS != null && t_contract_parkByVehicleDOS.size() > 0) {
            for (ContractParkByVehicleDO t_contract_parkByVehicleDO : t_contract_parkByVehicleDOS) {
                String simNo = t_contract_parkByVehicleDO.getSimNo();
                ContractParkCacheDO t_contract_parkCacheDO =new ContractParkCacheDO();
                BeanUtils.copyProperties(t_contract_parkByVehicleDO,t_contract_parkCacheDO);
                 List<ContractParkCacheDO> t_contract_parkCacheDOs=new ArrayList();
                if(simNoConfigCacheTemp.containsKey(simNo)){
                 t_contract_parkCacheDOs=simNoConfigCacheTemp.get(simNo);
                 t_contract_parkCacheDOs.add(t_contract_parkCacheDO);
                }
                simNoConfigCacheTemp.put(simNo, t_contract_parkCacheDOs);
            }
        }
        simNoConfigCache = simNoConfigCacheTemp;//将simNo每个对应转发的配置加入到其中
        log.debug("车场合同缓存成功");
    }




}