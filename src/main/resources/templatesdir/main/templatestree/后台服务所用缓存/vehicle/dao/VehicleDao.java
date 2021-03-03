package templatestree.后台服务所用缓存.vehicle.dao;

import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
* @author ：hzz
* @description： 车辆表 配置信息dao
* @date ：2020/10/19 11:49
*/
@Service
public class VehicleDao {

    @Autowired
    private JdbcUtil jdbcUtil;

    /**
     * 查询所有需要转发的车辆和配置信息
     * @return
     */
    public List<VehicleByVehicleDO> searchconfig(){
        String sql="\n" +
                "select " +
                "  a.vehicleid,  "+
                "  a.createdate,  "+
                "  a.deleted,  "+
                "  a.owner,  "+
                "  a.remark,  "+
                "  a.buytime,  "+
                "  a.depid,  "+
                "  a.depname,  "+
                "  a.driver,  "+
                "  a.drivermobile,  "+
                "  a.gpsterminaltype,  "+
                "  a.industry,  "+
                "  a.installdate,  "+
                "  a.lastchecktime,  "+
                "  a.memberid,  "+
                "  a.monitor,  "+
                "  a.monitormobile,  "+
                "  a.motorid,  "+
                "  a.offlinetime,  "+
                "  a.onlinetime,  "+
                "  a.operpermit,  "+
                "  a.platecolor,  "+
                "  a.plateno,  "+
                "  a.region,  "+
                "  a.routes,  "+
                "  a.runstatus,  "+
                "  a.simno,  "+
                "  a.status,  "+
                "  a.termid,  "+
                "  a.usetype,  "+
                "  a.vehicletype,  "+
                "  a.vendor,  "+
                "  a.videodeviceid,  "+
                "  a.enddate,  "+
                "  a.startdate,  "+
                "  a.workhour,  "+
                "  a.buydate,  "+
                "  a.engineno,  "+
                "  a.frameno,  "+
                "  a.manufacture,  "+
                "  a.modelno,  "+
                "  a.photo,  "+
                "  a.companyid,  "+
                "  a.videochannelnum,  "+
                "  a.username,  "+
                "  a.videodatatype,  "+
                "  a.channelid,  "+
                "  a.videodisk,  "+
                "  a.videochannelnames,  "+
                "  a.flowrateno,  "+
                "  a.vehiclepassword,  "+
                "  a.supertranstype,  "+
                "  a.seatingcapacity,  "+
                "  a.transserialno,  "+
                "  a.routename,  "+
                "  a.servicestartdate,  "+
                "  a.serviceenddate,  "+
                "  a.mileageadjustment,  "+
                "  a.fuelconsumption,  "+
                "  a.updatedate,  "+
                "  a.updatestaff,  "+
                "  a.createstaff,  "+
                "  a.vehiclemanufacturer,  "+
                "  a.inlinedate,  "+
                "  a.synchronizeddate,  "+
                "  a.operatorid,  "+
                " v.simNo,v.plateNo\n" +
                "from  vehicle a\n" +
                "left join \n" +
                "vehiclebydep dep on a.id=dep.mainId\n" +
                "left join vehicle v on dep.depId = v.depId \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and dep.deleted =false \n" +
                "\n" +
                "union all \n" +
                "select " +
                "  a.vehicleid,  "+
                "  a.createdate,  "+
                "  a.deleted,  "+
                "  a.owner,  "+
                "  a.remark,  "+
                "  a.buytime,  "+
                "  a.depid,  "+
                "  a.depname,  "+
                "  a.driver,  "+
                "  a.drivermobile,  "+
                "  a.gpsterminaltype,  "+
                "  a.industry,  "+
                "  a.installdate,  "+
                "  a.lastchecktime,  "+
                "  a.memberid,  "+
                "  a.monitor,  "+
                "  a.monitormobile,  "+
                "  a.motorid,  "+
                "  a.offlinetime,  "+
                "  a.onlinetime,  "+
                "  a.operpermit,  "+
                "  a.platecolor,  "+
                "  a.plateno,  "+
                "  a.region,  "+
                "  a.routes,  "+
                "  a.runstatus,  "+
                "  a.simno,  "+
                "  a.status,  "+
                "  a.termid,  "+
                "  a.usetype,  "+
                "  a.vehicletype,  "+
                "  a.vendor,  "+
                "  a.videodeviceid,  "+
                "  a.enddate,  "+
                "  a.startdate,  "+
                "  a.workhour,  "+
                "  a.buydate,  "+
                "  a.engineno,  "+
                "  a.frameno,  "+
                "  a.manufacture,  "+
                "  a.modelno,  "+
                "  a.photo,  "+
                "  a.companyid,  "+
                "  a.videochannelnum,  "+
                "  a.username,  "+
                "  a.videodatatype,  "+
                "  a.channelid,  "+
                "  a.videodisk,  "+
                "  a.videochannelnames,  "+
                "  a.flowrateno,  "+
                "  a.vehiclepassword,  "+
                "  a.supertranstype,  "+
                "  a.seatingcapacity,  "+
                "  a.transserialno,  "+
                "  a.routename,  "+
                "  a.servicestartdate,  "+
                "  a.serviceenddate,  "+
                "  a.mileageadjustment,  "+
                "  a.fuelconsumption,  "+
                "  a.updatedate,  "+
                "  a.updatestaff,  "+
                "  a.createstaff,  "+
                "  a.vehiclemanufacturer,  "+
                "  a.inlinedate,  "+
                "  a.synchronizeddate,  "+
                "  a.operatorid,  "+
                " v.simNo,v.plateNo\n" +
                "from  vehicle a\n" +
                "left join \n" +
                "vehiclebyvehicle veh on a.id=veh.mainId\n" +
                "left join vehicle v on veh.vehicleid = v.vehicleid \n" +
                "where 1=1 and a.isuse =1 and a.deleted=false \n" +
                "and v.deleted=false \n" +
                "and veh.deleted =false ";
        List<VehicleByVehicleDO> query = jdbcUtil.sql(sql).query(VehicleByVehicleDO.class);
        return  query;
    }



}
