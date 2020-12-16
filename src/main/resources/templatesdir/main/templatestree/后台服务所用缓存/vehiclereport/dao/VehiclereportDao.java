package templatestree.后台服务所用缓存.vehiclereport.dao;

import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author ：hzz
* @description： 企业车辆信息表维护表 配置信息dao
* @date ：2020/10/19 11:49
*/
@Service
public class VehiclereportDao {

    @Autowired
    private JdbcUtil jdbcUtil;

    /**
     * 查询所有需要转发的车辆和配置信息
     * @return
     */
    public List<VehiclereportByVehicleDO> searchconfig(){
        String sql="\n" +
                "select " +
                "  a.id,  "+
                "  a.plateno,  "+
                "  a.color,  "+
                "  a.ton,  "+
                "  a.transportcard,  "+
                "  a.jilevel,  "+
                "  a.jileveldate,  "+
                "  a.aftercheckdate,  "+
                "  a.lastweidate,  "+
                "  a.afterweidate,  "+
                "  a.transportdep,  "+
                "  a.beforecheckdate,  "+
                "  a.vehicletype,  "+
                "  a.yearcheckdate,  "+
                "  a.yearcheckresult,  "+
                "  a.state,  "+
                "  a.vehicleaddress,  "+
                "  a.deplicence,  "+
                "  a.vehiclecreatetime,  "+
                "  a.vehicleowner,  "+
                "  a.transporttype,  "+
                "  a.drivercardregisterdate,  "+
                "  a.region,  "+
                "  a.enginenumber,  "+
                "  a.vin,  "+
                "  a.factoryplatemodel,  "+
                "  a.transportcardcreatedate,  "+
                "  a.businessscope,  "+
                "  a.dangercargocheck,  "+
                "  a.drivername,  "+
                "  a.certificatecode,  "+
                "  a.supercargo,  "+
                "  a.supercargocertificatecode,  "+
                "  a.cargotype,  "+
                "  a.signlightnumber,  "+
                "  a.signlightinsdate,  "+
                "  a.signlightvaliditydate,  "+
                "  a.signboardvaliditydate,  "+
                "  a.carriersrisk,  "+
                "  a.carriersriskvaliditydate,  "+
                "  a.isusegps,  "+
                "  a.isusedriverrecorder,  "+
                "  a.isusegpsanddervercorder,  "+
                "  a.safecheckdate,  "+
                "  a.violationnum,  "+
                "  a.policestate,  "+
                "  a.secondmaintenancecycle,  "+
                "  a.secondmaintenancemileage,  "+
                "  a.fueltype,  "+
                "  a.driver,  "+
                "  a.drivercard,  "+
                "  a.driversfzh,  "+
                "  a.drivercardfirstdate,  "+
                "  a.transportcardfirstdate,  "+
                "  a.fulltotalmass,  "+
                "  a.length,  "+
                "  a.width,  "+
                "  a.height,  "+
                "  a.depmobilephone,  "+
                "  a.deptelephone,  "+
                "  a.carbodycolor,  "+
                "  a.capacitysource,  "+
                "  a.tractionmass,  "+
                "  a.twomaintaindep,  "+
                "  a.allinspection,  "+
                "  a.policecartype,  "+
                "  a.time,  "+
                "  a.createtime,  "+
                "  a.userid,  "+
                " v.simNo,v.plateNo\n" +
                "from  vehiclereport a\n" +
                "left join \n" +
                "vehiclereportbydep dep on a.id=dep.mainId\n" +
                "left join vehicle v on dep.depId = v.depId \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and dep.deleted =false \n" +
                "\n" +
                "union all \n" +
                "select " +
                "  a.id,  "+
                "  a.plateno,  "+
                "  a.color,  "+
                "  a.ton,  "+
                "  a.transportcard,  "+
                "  a.jilevel,  "+
                "  a.jileveldate,  "+
                "  a.aftercheckdate,  "+
                "  a.lastweidate,  "+
                "  a.afterweidate,  "+
                "  a.transportdep,  "+
                "  a.beforecheckdate,  "+
                "  a.vehicletype,  "+
                "  a.yearcheckdate,  "+
                "  a.yearcheckresult,  "+
                "  a.state,  "+
                "  a.vehicleaddress,  "+
                "  a.deplicence,  "+
                "  a.vehiclecreatetime,  "+
                "  a.vehicleowner,  "+
                "  a.transporttype,  "+
                "  a.drivercardregisterdate,  "+
                "  a.region,  "+
                "  a.enginenumber,  "+
                "  a.vin,  "+
                "  a.factoryplatemodel,  "+
                "  a.transportcardcreatedate,  "+
                "  a.businessscope,  "+
                "  a.dangercargocheck,  "+
                "  a.drivername,  "+
                "  a.certificatecode,  "+
                "  a.supercargo,  "+
                "  a.supercargocertificatecode,  "+
                "  a.cargotype,  "+
                "  a.signlightnumber,  "+
                "  a.signlightinsdate,  "+
                "  a.signlightvaliditydate,  "+
                "  a.signboardvaliditydate,  "+
                "  a.carriersrisk,  "+
                "  a.carriersriskvaliditydate,  "+
                "  a.isusegps,  "+
                "  a.isusedriverrecorder,  "+
                "  a.isusegpsanddervercorder,  "+
                "  a.safecheckdate,  "+
                "  a.violationnum,  "+
                "  a.policestate,  "+
                "  a.secondmaintenancecycle,  "+
                "  a.secondmaintenancemileage,  "+
                "  a.fueltype,  "+
                "  a.driver,  "+
                "  a.drivercard,  "+
                "  a.driversfzh,  "+
                "  a.drivercardfirstdate,  "+
                "  a.transportcardfirstdate,  "+
                "  a.fulltotalmass,  "+
                "  a.length,  "+
                "  a.width,  "+
                "  a.height,  "+
                "  a.depmobilephone,  "+
                "  a.deptelephone,  "+
                "  a.carbodycolor,  "+
                "  a.capacitysource,  "+
                "  a.tractionmass,  "+
                "  a.twomaintaindep,  "+
                "  a.allinspection,  "+
                "  a.policecartype,  "+
                "  a.time,  "+
                "  a.createtime,  "+
                "  a.userid,  "+
                " v.simNo,v.plateNo\n" +
                "from  vehiclereport a\n" +
                "left join \n" +
                "vehiclereportbyvehicle veh on a.id=veh.mainId\n" +
                "left join vehicle v on veh.vehicleid = v.vehicleid \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and veh.deleted =false ";
        List<VehiclereportByVehicleDO> query = jdbcUtil.sql(sql).query(VehiclereportByVehicleDO.class);
        return  query;
    }



}
