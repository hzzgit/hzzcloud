package templatestree.后台服务所用缓存.t_contract_park.dao;

import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author ：hzz
* @description： 车场合同 配置信息dao
* @date ：2020/10/19 11:49
*/
@Service
public class ContractParkDao {

    @Autowired
    private JdbcUtil jdbcUtil;

    /**
     * 查询所有需要转发的车辆和配置信息
     * @return
     */
    public List<ContractParkByVehicleDO> searchconfig(){
        String sql="\n" +
                "select " +
                "  a.contractId,  "+
                "  a.parkGuid,  "+
                "  a.contractNo,  "+
                "  a.contractSpace,  "+
                "  a.contractInChannelCount,  "+
                "  a.contractOutChannelCount,  "+
                "  a.serviceObject,  "+
                "  a.operationalModel,  "+
                "  a.signSource,  "+
                "  a.saleUserId,  "+
                "  a.salePerson,  "+
                "  a.contractStatus,  "+
                "  a.signDate,  "+
                "  a.contractPeriod,  "+
                "  a.contractStartTime,  "+
                "  a.contractExpireTime,  "+
                "  a.contractAmount,  "+
                "  a.serviceChargeAmount,  "+
                "  a.periodsType,  "+
                "  a.receivablesType,  "+
                "  a.createId,  "+
                "  a.createName,  "+
                "  a.createTime,  "+
                "  a.updateTime,  "+
                "  a.handlerId,  "+
                "  a.handlerName,  "+
                "  a.tryWay,  "+
                "  a.approveStatus,  "+
                "  a.tryNumber,  "+
                "  a.deductMoneyType,  "+
                "  a.deductMoneyPercent,  "+
                "  a.singlePhaseAmount,  "+
                "  a.processFailedRemark,  "+
                " v.simNo,v.plateNo\n" +
                "from  t_contract_park a\n" +
                "left join \n" +
                "t_contract_parkbydep dep on a.id=dep.mainId\n" +
                "left join vehicle v on dep.depId = v.depId \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and dep.deleted =false \n" +
                "\n" +
                "union all \n" +
                "select " +
                "  a.contractId,  "+
                "  a.parkGuid,  "+
                "  a.contractNo,  "+
                "  a.contractSpace,  "+
                "  a.contractInChannelCount,  "+
                "  a.contractOutChannelCount,  "+
                "  a.serviceObject,  "+
                "  a.operationalModel,  "+
                "  a.signSource,  "+
                "  a.saleUserId,  "+
                "  a.salePerson,  "+
                "  a.contractStatus,  "+
                "  a.signDate,  "+
                "  a.contractPeriod,  "+
                "  a.contractStartTime,  "+
                "  a.contractExpireTime,  "+
                "  a.contractAmount,  "+
                "  a.serviceChargeAmount,  "+
                "  a.periodsType,  "+
                "  a.receivablesType,  "+
                "  a.createId,  "+
                "  a.createName,  "+
                "  a.createTime,  "+
                "  a.updateTime,  "+
                "  a.handlerId,  "+
                "  a.handlerName,  "+
                "  a.tryWay,  "+
                "  a.approveStatus,  "+
                "  a.tryNumber,  "+
                "  a.deductMoneyType,  "+
                "  a.deductMoneyPercent,  "+
                "  a.singlePhaseAmount,  "+
                "  a.processFailedRemark,  "+
                " v.simNo,v.plateNo\n" +
                "from  t_contract_park a\n" +
                "left join \n" +
                "t_contract_parkbyvehicle veh on a.id=veh.mainId\n" +
                "left join vehicle v on veh.vehicleid = v.vehicleid \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and veh.deleted =false ";
        List<ContractParkByVehicleDO> query = jdbcUtil.sql(sql).query(ContractParkByVehicleDO.class);
        return  query;
    }



}
