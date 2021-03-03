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
    /**  机构id  */
    private Long  depid;
    /**    */
    private String  depname;
    /**    */
    private String  driver;
    /**    */
    private String  drivermobile;
    /**    */
    private String  gpsterminaltype;
    /**  运输行业编码  */
    private String  industry;
    /**  安装时间  */
    private Date  installdate;
    /**    */
    private Date  lastchecktime;
    /**  业户id  */
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
    /**  车辆颜色  */
    private Long  platecolor;
    /**  车牌号  */
    private String  plateno;
    /**  车籍地  */
    private String  region;
    /**    */
    private String  routes;
    /**  运行状态  */
    private String  runstatus;
    /**    */
    private String  simno;
    /**    */
    private String  status;
    /**    */
    private Long  termid;
    /**    */
    private String  usetype;
    /**  车辆类型  */
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
    /**  视频通道数  */
    private Long  videochannelnum;
    /**    */
    private String  username;
    /**    */
    private Long  videodatatype;
    /**    */
    private Long  channelid;
    /**    */
    private String  videodisk;
    /**  视频通道名称  */
    private String  videochannelnames;
    /**  物联网卡号  */
    private String  flowrateno;
    /**  车辆登录密码  */
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
    /**    */
    private Date  inlinedate;
    /**  同步时间  */
    private Date  synchronizeddate;
    /**  运营商id  */
    private Long  operatorid;

}