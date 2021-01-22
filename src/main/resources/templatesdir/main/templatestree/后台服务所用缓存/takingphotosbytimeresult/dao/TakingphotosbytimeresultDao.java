package templatestree.后台服务所用缓存.takingphotosbytimeresult.dao;

import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author ：hzz
* @description： 定时拍照结果表(总表) 配置信息dao
* @date ：2020/10/19 11:49
*/
@Service
public class TakingphotosbytimeresultDao {

    @Autowired
    private JdbcUtil jdbcUtil;

    /**
     * 查询所有需要转发的车辆和配置信息
     * @return
     */
    public List<TakingphotosbytimeresultByVehicleDO> searchconfig(){
        String sql="\n" +
                "select " +
                "  a.id,  "+
                "  a.createdate,  "+
                "  a.updatedate,  "+
                "  a.deleted,  "+
                "  a.userid,  "+
                "  a.photonum,  "+
                "  a.remark,  "+
                "  a.latitude,  "+
                "  a.longitude,  "+
                "  a.sendtime,  "+
                "  a.simno,  "+
                "  a.vehicleid,  "+
                "  a.speed,  "+
                "  a.drivername,  "+
                "  a.certificate,  "+
                "  a.configid,  "+
                "  a.status,  "+
                " v.simNo,v.plateNo\n" +
                "from  takingphotosbytimeresult a\n" +
                "left join \n" +
                "takingphotosbytimeresultbydep dep on a.id=dep.mainId\n" +
                "left join vehicle v on dep.depId = v.depId \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and dep.deleted =false \n" +
                "\n" +
                "union all \n" +
                "select " +
                "  a.id,  "+
                "  a.createdate,  "+
                "  a.updatedate,  "+
                "  a.deleted,  "+
                "  a.userid,  "+
                "  a.photonum,  "+
                "  a.remark,  "+
                "  a.latitude,  "+
                "  a.longitude,  "+
                "  a.sendtime,  "+
                "  a.simno,  "+
                "  a.vehicleid,  "+
                "  a.speed,  "+
                "  a.drivername,  "+
                "  a.certificate,  "+
                "  a.configid,  "+
                "  a.status,  "+
                " v.simNo,v.plateNo\n" +
                "from  takingphotosbytimeresult a\n" +
                "left join \n" +
                "takingphotosbytimeresultbyvehicle veh on a.id=veh.mainId\n" +
                "left join vehicle v on veh.vehicleid = v.vehicleid \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and veh.deleted =false ";
        List<TakingphotosbytimeresultByVehicleDO> query = jdbcUtil.sql(sql).query(TakingphotosbytimeresultByVehicleDO.class);
        return  query;
    }



}
