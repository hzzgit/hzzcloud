package templatestree.后台服务所用缓存.config_table_filed.dao;

import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author ：hzz
* @description： 用于管理平台配置表的信息的表字段配置表 配置信息dao
* @date ：2020/10/19 11:49
*/
@Service
public class Config_table_filedDao {

    @Autowired
    private JdbcUtil jdbcUtil;

    /**
     * 查询所有需要转发的车辆和配置信息
     * @return
     */
    public List<Config_table_filedByVehicleDO> searchconfig(){
        String sql="\n" +
                "select " +
                "  a.id,  "+
                "  a.mainid,  "+
                "  a.deleted,  "+
                "  a.colname,  "+
                "  a.annotation,  "+
                "  a.createdate,  "+
                "  a.issearchfiled,  "+
                "  a.searchtype,  "+
                "  a.coltype,  "+
                "  a.isshow,  "+
                "  a.issort,  "+
                "  a.sorttype,  "+
                " v.simNo,v.plateNo\n" +
                "from  config_table_filed a\n" +
                "left join \n" +
                "config_table_filedbydep dep on a.id=dep.mainId\n" +
                "left join vehicle v on dep.depId = v.depId \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and dep.deleted =false \n" +
                "\n" +
                "union all \n" +
                "select " +
                "  a.id,  "+
                "  a.mainid,  "+
                "  a.deleted,  "+
                "  a.colname,  "+
                "  a.annotation,  "+
                "  a.createdate,  "+
                "  a.issearchfiled,  "+
                "  a.searchtype,  "+
                "  a.coltype,  "+
                "  a.isshow,  "+
                "  a.issort,  "+
                "  a.sorttype,  "+
                " v.simNo,v.plateNo\n" +
                "from  config_table_filed a\n" +
                "left join \n" +
                "config_table_filedbyvehicle veh on a.id=veh.mainId\n" +
                "left join vehicle v on veh.vehicleid = v.vehicleid \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and veh.deleted =false ";
        List<Config_table_filedByVehicleDO> query = jdbcUtil.sql(sql).query(Config_table_filedByVehicleDO.class);
        return  query;
    }



}
