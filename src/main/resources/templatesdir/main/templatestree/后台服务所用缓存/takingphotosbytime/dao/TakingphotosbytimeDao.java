package templatestree.后台服务所用缓存.takingphotosbytime.dao;

import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author ：hzz
* @description： 定时拍照配置表 配置信息dao
* @date ：2020/10/19 11:49
*/
@Service
public class TakingphotosbytimeDao {

    @Autowired
    private JdbcUtil jdbcUtil;

    /**
     * 查询所有需要转发的车辆和配置信息
     * @return
     */
    public List<TakingphotosbytimeByVehicleDO> searchconfig(){
        String sql="\n" +
                "select " +
                "  a.id,  "+
                "  a.name,  "+
                "  a.configinterval,  "+
                "  a.createdate,  "+
                "  a.updatedate,  "+
                "  a.starttime,  "+
                "  a.endtime,  "+
                "  a.validstarttime,  "+
                "  a.validendtime,  "+
                "  a.configcondition,  "+
                "  a.speed,  "+
                "  a.packduration,  "+
                "  a.channel,  "+
                "  a.userid,  "+
                "  a.isuse,  "+
                "  a.deleted,  "+
                "  a.username,  "+
                " v.simNo,v.plateNo\n" +
                "from  takingphotosbytime a\n" +
                "left join \n" +
                "takingphotosbytimebydep dep on a.id=dep.mainId\n" +
                "left join vehicle v on dep.depId = v.depId \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and dep.deleted =false \n" +
                "\n" +
                "union all \n" +
                "select " +
                "  a.id,  "+
                "  a.name,  "+
                "  a.configinterval,  "+
                "  a.createdate,  "+
                "  a.updatedate,  "+
                "  a.starttime,  "+
                "  a.endtime,  "+
                "  a.validstarttime,  "+
                "  a.validendtime,  "+
                "  a.configcondition,  "+
                "  a.speed,  "+
                "  a.packduration,  "+
                "  a.channel,  "+
                "  a.userid,  "+
                "  a.isuse,  "+
                "  a.deleted,  "+
                "  a.username,  "+
                " v.simNo,v.plateNo\n" +
                "from  takingphotosbytime a\n" +
                "left join \n" +
                "takingphotosbytimebyvehicle veh on a.id=veh.mainId\n" +
                "left join vehicle v on veh.vehicleid = v.vehicleid \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and veh.deleted =false ";
        List<TakingphotosbytimeByVehicleDO> query = jdbcUtil.sql(sql).query(TakingphotosbytimeByVehicleDO.class);
        return  query;
    }



}
