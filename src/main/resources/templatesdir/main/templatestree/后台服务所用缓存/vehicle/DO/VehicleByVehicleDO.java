package templatestree.后台服务所用缓存.vehicle.DO;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
public class VehicleByVehicleDO implements java.io.Serializable  {
    /*simNO*/
    private String simNo;

    /*车牌号*/
    private String plateNo;

    /**    */
    private Long  vehicleid;
    /**    */
    private Date  createdate;
    /**    */
    private Long  deleted;
    /**    */
    private String  owner;
    /**    */
    private String  remark;
    /**    */
    private Date  buytime;
    /**    */
    private Long  depid;
    /**    */
    private String  depname;
    /**    */
    private String  driver;
    /**    */
    private String  drivermobile;
    /**    */
    private String  gpsterminaltype;
    /**    */
    private String  industry;
    /**    */
    private Date  installdate;
    /**    */
    private Date  lastchecktime;
    /**    */
    private Long  memberid;
    /**    */
    private String  monitor;
    /**    */
    private String  monitormobile;
    /**    */
    private String  motorid;
    /**    */
    private Date  offlinetime;
    /**    */
    private Date  onlinetime;
    /**    */
    private String  operpermit;
    /**    */
    private Long  platecolor;
    /**    */
    private String  plateno;
    /**    */
    private String  region;
    /**    */
    private String  routes;
    /**    */
    private String  runstatus;
    /**    */
    private String  simno;
    /**    */
    private String  status;
    /**    */
    private Long  termid;
    /**    */
    private String  usetype;
    /**    */
    private String  vehicletype;
    /**    */
    private String  vendor;
    /**    */
    private String  videodeviceid;
    /**    */
    private Date  enddate;
    /**    */
    private Date  startdate;
    /**    */
    private Double  workhour;
    /**    */
    private Date  buydate;
    /**    */
    private String  engineno;
    /**    */
    private String  frameno;
    /**    */
    private String  manufacture;
    /**    */
    private String  modelno;
    /**    */
    private String  photo;
    /**    */
    private Long  companyid;
    /**    */
    private Long  videochannelnum;
    /**    */
    private String  username;
    /**    */
    private Long  videodatatype;
    /**    */
    private Long  channelid;
    /**    */
    private String  videodisk;
    /**    */
    private String  videochannelnames;
    /**    */
    private String  flowrateno;
    /**    */
    private String  vehiclepassword;
    /**  上级平台的营运类型代码    (必填)	  字典维护  */
    private String  supertranstype;
    /**  吨（座）位  */
    private String  seatingcapacity;
    /**  道路运输证号  */
    private String  transserialno;
    /**  营运线路  */
    private String  routename;
    /**  服务开始时间  */
    private Date  servicestartdate;
    /**  服务结束时间  */
    private Date  serviceenddate;
    /**  入网时间  */
    private Date  inlinedate;
    /**  里程调整  */
    private String  mileageadjustment;
    /**  百公里油耗  */
    private String  fuelconsumption;
    /**  更新时间  */
    private Date  updatedate;
    /**  更新人  */
    private Long  updatestaff;
    /**  创建人  */
    private Long  createstaff;
    /**  车辆厂商  */
    private String  vehiclemanufacturer;
    /**  运营商id  */
    private Long  operatorid;
    /**  协议版本  */
    private String  protocol_version;

}