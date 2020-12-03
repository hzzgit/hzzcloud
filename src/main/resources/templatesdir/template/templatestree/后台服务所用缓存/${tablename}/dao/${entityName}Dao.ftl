package ${packpath};

import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author ：hzz
* @description： ${tableconment} 配置信息dao
* @date ：2020/10/19 11:49
*/
@Service
public class ${entityName}Dao {

    @Autowired
    private JdbcUtil jdbcUtil;

    /**
     * 查询所有需要转发的车辆和配置信息
     * @return
     */
    public List<${entityName}ByVehicleDO> searchconfig(){
        String sql="\n" +
                "select " +
                <#list tableColumnList as tablecolumn>
                "  a.${tablecolumn.columnname},  "+
                </#list>
                " v.simNo,v.plateNo\n" +
                "from  ${tablename} a\n" +
                "left join \n" +
                "${tablename}bydep dep on a.id=dep.mainId\n" +
                "left join vehicle v on dep.depId = v.depId \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and dep.deleted =false \n" +
                "\n" +
                "union all \n" +
                "select " +
            <#list tableColumnList as tablecolumn>
                "  a.${tablecolumn.columnname},  "+
            </#list>
                " v.simNo,v.plateNo\n" +
                "from  ${tablename} a\n" +
                "left join \n" +
                "${tablename}byvehicle veh on a.id=veh.mainId\n" +
                "left join vehicle v on veh.vehicleid = v.vehicleid \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and veh.deleted =false ";
        List<${entityName}ByVehicleDO> query = jdbcUtil.sql(sql).query(${entityName}ByVehicleDO.class);
        return  query;
    }



}
