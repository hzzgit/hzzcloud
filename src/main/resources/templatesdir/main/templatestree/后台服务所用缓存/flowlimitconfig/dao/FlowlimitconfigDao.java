package templatestree.后台服务所用缓存.flowlimitconfig.dao;

import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author ：hzz
* @description： 流量阈值配置表 配置信息dao
* @date ：2020/10/19 11:49
*/
@Service
public class FlowlimitconfigDao {

    @Autowired
    private JdbcUtil jdbcUtil;

    /**
     * 查询所有需要转发的车辆和配置信息
     * @return
     */
    public List<FlowlimitconfigByVehicleDO> searchconfig(){
        String sql="\n" +
                "select " +
                "  a.id,  "+
                "  a.name,  "+
                "  a.limitval,  "+
                "  a.validstarttime,  "+
                "  a.validendtime,  "+
                "  a.createdate,  "+
                "  a.updatedate,  "+
                "  a.userid,  "+
                "  a.isuse,  "+
                "  a.deleted,  "+
                " v.simNo,v.plateNo\n" +
                "from  flowlimitconfig a\n" +
                "left join \n" +
                "flowlimitconfigbydep dep on a.id=dep.mainId\n" +
                "left join vehicle v on dep.depId = v.depId \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and dep.deleted =false \n" +
                "\n" +
                "union all \n" +
                "select " +
                "  a.id,  "+
                "  a.name,  "+
                "  a.limitval,  "+
                "  a.validstarttime,  "+
                "  a.validendtime,  "+
                "  a.createdate,  "+
                "  a.updatedate,  "+
                "  a.userid,  "+
                "  a.isuse,  "+
                "  a.deleted,  "+
                " v.simNo,v.plateNo\n" +
                "from  flowlimitconfig a\n" +
                "left join \n" +
                "flowlimitconfigbyvehicle veh on a.id=veh.mainId\n" +
                "left join vehicle v on veh.vehicleid = v.vehicleid \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and veh.deleted =false ";
        List<FlowlimitconfigByVehicleDO> query = jdbcUtil.sql(sql).query(FlowlimitconfigByVehicleDO.class);
        return  query;
    }



}
