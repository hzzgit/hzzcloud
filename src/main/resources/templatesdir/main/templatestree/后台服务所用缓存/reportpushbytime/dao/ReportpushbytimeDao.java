package templatestree.后台服务所用缓存.reportpushbytime.dao;

import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author ：hzz
* @description： 金融报表生成，用于控制每个用户每个月什么时间段生成指定车辆的数据 配置信息dao
* @date ：2020/10/19 11:49
*/
@Service
public class ReportpushbytimeDao {

    @Autowired
    private JdbcUtil jdbcUtil;

    /**
     * 查询所有需要转发的车辆和配置信息
     * @return
     */
    public List<ReportpushbytimeByVehicleDO> searchconfig(){
        String sql="\n" +
                "select " +
                "  a.id,  "+
                "  a.username,  "+
                "  a.userid,  "+
                "  a.starttime,  "+
                "  a.endtime,  "+
                "  a.createdate,  "+
                "  a.deleted,  "+
                "  a.loginname,  "+
                "  a.vehicleid,  "+
                "  a.plateno,  "+
                " v.simNo,v.plateNo\n" +
                "from  reportpushbytime a\n" +
                "left join \n" +
                "reportpushbytimebydep dep on a.id=dep.mainId\n" +
                "left join vehicle v on dep.depId = v.depId \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and dep.deleted =false \n" +
                "\n" +
                "union all \n" +
                "select " +
                "  a.id,  "+
                "  a.username,  "+
                "  a.userid,  "+
                "  a.starttime,  "+
                "  a.endtime,  "+
                "  a.createdate,  "+
                "  a.deleted,  "+
                "  a.loginname,  "+
                "  a.vehicleid,  "+
                "  a.plateno,  "+
                " v.simNo,v.plateNo\n" +
                "from  reportpushbytime a\n" +
                "left join \n" +
                "reportpushbytimebyvehicle veh on a.id=veh.mainId\n" +
                "left join vehicle v on veh.vehicleid = v.vehicleid \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and veh.deleted =false ";
        List<ReportpushbytimeByVehicleDO> query = jdbcUtil.sql(sql).query(ReportpushbytimeByVehicleDO.class);
        return  query;
    }



}
