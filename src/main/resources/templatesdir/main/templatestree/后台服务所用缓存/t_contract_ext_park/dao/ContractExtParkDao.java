package templatestree.后台服务所用缓存.t_contract_ext_park.dao;

import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author ：hzz
* @description： 车场合同附加协议 配置信息dao
* @date ：2020/10/19 11:49
*/
@Service
public class ContractExtParkDao {

    @Autowired
    private JdbcUtil jdbcUtil;

    /**
     * 查询所有需要转发的车辆和配置信息
     * @return
     */
    public List<ContractExtParkByVehicleDO> searchconfig(){
        String sql="\n" +
                "select " +
                "  a.contractextId,  "+
                "  a.contractNo,  "+
                "  a.contractNoExt,  "+
                "  a.parkGuid,  "+
                "  a.signDate,  "+
                "  a.contractAmountExt,  "+
                "  a.receivableAmount,  "+
                "  a.contractAmount,  "+
                "  a.contractPeriod,  "+
                "  a.periodsType,  "+
                "  a.singlePhaseAmount,  "+
                "  a.tryWay,  "+
                "  a.tryNumber,  "+
                "  a.deductMoneyType,  "+
                "  a.deductMoneyPercent,  "+
                "  a.stopContract,  "+
                "  a.remark,  "+
                "  a.approveStatus,  "+
                "  a.processFailedRemark,  "+
                "  a.saleUserId,  "+
                "  a.salePerson,  "+
                "  a.createId,  "+
                "  a.createName,  "+
                "  a.createTime,  "+
                "  a.updateTime,  "+
                "  a.handlerId,  "+
                "  a.handlerName,  "+
                " v.simNo,v.plateNo\n" +
                "from  t_contract_ext_park a\n" +
                "left join \n" +
                "t_contract_ext_parkbydep dep on a.id=dep.mainId\n" +
                "left join vehicle v on dep.depId = v.depId \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and dep.deleted =false \n" +
                "\n" +
                "union all \n" +
                "select " +
                "  a.contractextId,  "+
                "  a.contractNo,  "+
                "  a.contractNoExt,  "+
                "  a.parkGuid,  "+
                "  a.signDate,  "+
                "  a.contractAmountExt,  "+
                "  a.receivableAmount,  "+
                "  a.contractAmount,  "+
                "  a.contractPeriod,  "+
                "  a.periodsType,  "+
                "  a.singlePhaseAmount,  "+
                "  a.tryWay,  "+
                "  a.tryNumber,  "+
                "  a.deductMoneyType,  "+
                "  a.deductMoneyPercent,  "+
                "  a.stopContract,  "+
                "  a.remark,  "+
                "  a.approveStatus,  "+
                "  a.processFailedRemark,  "+
                "  a.saleUserId,  "+
                "  a.salePerson,  "+
                "  a.createId,  "+
                "  a.createName,  "+
                "  a.createTime,  "+
                "  a.updateTime,  "+
                "  a.handlerId,  "+
                "  a.handlerName,  "+
                " v.simNo,v.plateNo\n" +
                "from  t_contract_ext_park a\n" +
                "left join \n" +
                "t_contract_ext_parkbyvehicle veh on a.id=veh.mainId\n" +
                "left join vehicle v on veh.vehicleid = v.vehicleid \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and veh.deleted =false ";
        List<ContractExtParkByVehicleDO> query = jdbcUtil.sql(sql).query(ContractExtParkByVehicleDO.class);
        return  query;
    }



}
