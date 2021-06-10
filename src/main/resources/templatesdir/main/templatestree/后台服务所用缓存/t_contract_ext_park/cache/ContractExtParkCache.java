package templatestree.后台服务所用缓存.t_contract_ext_park.cache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
* @author ：hzz
* @description： 车场合同附加协议 配置信息缓存
* @date ：2020/10/19 11:49
*/
@Service
@Slf4j
@RefreshValue
public class ContractExtParkCache {

    @Autowired
    private ContractExtParkDao t_contract_ext_parkDao;


    /*用于存放simNo对应的配置*/
    private ConcurrentHashMap<String, List<ContractExtParkCacheDO>> simNoConfigCache = new ConcurrentHashMap<>();


    @Value(" ${t_contract_ext_parkEnabled:false}")
    private Boolean t_contract_ext_parkEnabled = false;

    public void setContractExtParkEnabled(Boolean t_contract_ext_parkEnabled) {
        this.t_contract_ext_parkEnabled = t_contract_ext_parkEnabled;
    }


    /**
    * 根据simNo获取到配置
    */
    public List<ContractExtParkCacheDO> getconfig(String simNo){
        List<ContractExtParkCacheDO> dom=null;
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
                if (t_contract_ext_parkEnabled) {
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
        ConcurrentHashMap<String,List<ContractExtParkCacheDO>> simNoConfigCacheTemp = new ConcurrentHashMap<>();
        List<ContractExtParkByVehicleDO> t_contract_ext_parkByVehicleDOS = t_contract_ext_parkDao.searchconfig();
        if (t_contract_ext_parkByVehicleDOS != null && t_contract_ext_parkByVehicleDOS.size() > 0) {
            for (ContractExtParkByVehicleDO t_contract_ext_parkByVehicleDO : t_contract_ext_parkByVehicleDOS) {
                String simNo = t_contract_ext_parkByVehicleDO.getSimNo();
                ContractExtParkCacheDO t_contract_ext_parkCacheDO =new ContractExtParkCacheDO();
                BeanUtils.copyProperties(t_contract_ext_parkByVehicleDO,t_contract_ext_parkCacheDO);
                 List<ContractExtParkCacheDO> t_contract_ext_parkCacheDOs=new ArrayList();
                if(simNoConfigCacheTemp.containsKey(simNo)){
                 t_contract_ext_parkCacheDOs=simNoConfigCacheTemp.get(simNo);
                 t_contract_ext_parkCacheDOs.add(t_contract_ext_parkCacheDO);
                }
                simNoConfigCacheTemp.put(simNo, t_contract_ext_parkCacheDOs);
            }
        }
        simNoConfigCache = simNoConfigCacheTemp;//将simNo每个对应转发的配置加入到其中
        log.debug("车场合同附加协议缓存成功");
    }




}