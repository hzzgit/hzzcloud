package templatestree.vehicle.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.vehicle")
public class Vehicle implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_vehicleid = "vehicleid";
    public static final String F_createdate = "createdate";
    public static final String F_deleted = "deleted";
    public static final String F_owner = "owner";
    public static final String F_remark = "remark";
    public static final String F_buytime = "buytime";
    public static final String F_depid = "depid";
    public static final String F_depname = "depname";
    public static final String F_driver = "driver";
    public static final String F_drivermobile = "drivermobile";
    public static final String F_gpsterminaltype = "gpsterminaltype";
    public static final String F_industry = "industry";
    public static final String F_installdate = "installdate";
    public static final String F_lastchecktime = "lastchecktime";
    public static final String F_memberid = "memberid";
    public static final String F_monitor = "monitor";
    public static final String F_monitormobile = "monitormobile";
    public static final String F_motorid = "motorid";
    public static final String F_offlinetime = "offlinetime";
    public static final String F_onlinetime = "onlinetime";
    public static final String F_operpermit = "operpermit";
    public static final String F_platecolor = "platecolor";
    public static final String F_plateno = "plateno";
    public static final String F_region = "region";
    public static final String F_routes = "routes";
    public static final String F_runstatus = "runstatus";
    public static final String F_simno = "simno";
    public static final String F_status = "status";
    public static final String F_termid = "termid";
    public static final String F_usetype = "usetype";
    public static final String F_vehicletype = "vehicletype";
    public static final String F_vendor = "vendor";
    public static final String F_videodeviceid = "videodeviceid";
    public static final String F_enddate = "enddate";
    public static final String F_startdate = "startdate";
    public static final String F_workhour = "workhour";
    public static final String F_buydate = "buydate";
    public static final String F_engineno = "engineno";
    public static final String F_frameno = "frameno";
    public static final String F_manufacture = "manufacture";
    public static final String F_modelno = "modelno";
    public static final String F_photo = "photo";
    public static final String F_companyid = "companyid";
    public static final String F_videochannelnum = "videochannelnum";
    public static final String F_username = "username";
    public static final String F_videodatatype = "videodatatype";
    public static final String F_channelid = "channelid";
    public static final String F_videodisk = "videodisk";
    public static final String F_videochannelnames = "videochannelnames";
    public static final String F_flowrateno = "flowrateno";
    public static final String F_vehiclepassword = "vehiclepassword";
    public static final String F_supertranstype = "supertranstype";
    public static final String F_seatingcapacity = "seatingcapacity";
    public static final String F_transserialno = "transserialno";
    public static final String F_routename = "routename";
    public static final String F_servicestartdate = "servicestartdate";
    public static final String F_serviceenddate = "serviceenddate";
    public static final String F_inlinedate = "inlinedate";
    public static final String F_mileageadjustment = "mileageadjustment";
    public static final String F_fuelconsumption = "fuelconsumption";
    public static final String F_updatedate = "updatedate";
    public static final String F_updatestaff = "updatestaff";
    public static final String F_createstaff = "createstaff";
    public static final String F_vehiclemanufacturer = "vehiclemanufacturer";
    public static final String F_operatorid = "operatorid";
    public static final String F_protocol_version = "protocol_version";


    /**  修改则传列表中的  */
        @DbId
    private long  vehicleid;
    /**  修改则传列表中的  */
    private Date  createdate;
    /**  修改则传列表中的  */
    private long  deleted;
    /**  修改则传列表中的  */
    private String  owner;
    /**  修改则传列表中的  */
    private String  remark;
    /**  修改则传列表中的  */
    private Date  buytime;
    /**  修改则传列表中的  */
    private long  depid;
    /**  修改则传列表中的  */
    private String  depname;
    /**  修改则传列表中的  */
    private String  driver;
    /**  修改则传列表中的  */
    private String  drivermobile;
    /**  修改则传列表中的  */
    private String  gpsterminaltype;
    /**  修改则传列表中的  */
    private String  industry;
    /**  修改则传列表中的  */
    private Date  installdate;
    /**  修改则传列表中的  */
    private Date  lastchecktime;
    /**  修改则传列表中的  */
    private long  memberid;
    /**  修改则传列表中的  */
    private String  monitor;
    /**  修改则传列表中的  */
    private String  monitormobile;
    /**  修改则传列表中的  */
    private String  motorid;
    /**  修改则传列表中的  */
    private Date  offlinetime;
    /**  修改则传列表中的  */
    private Date  onlinetime;
    /**  修改则传列表中的  */
    private String  operpermit;
    /**  修改则传列表中的  */
    private long  platecolor;
    /**  修改则传列表中的  */
    private String  plateno;
    /**  修改则传列表中的  */
    private String  region;
    /**  修改则传列表中的  */
    private String  routes;
    /**  修改则传列表中的  */
    private String  runstatus;
    /**  修改则传列表中的  */
    private String  simno;
    /**  修改则传列表中的  */
    private String  status;
    /**  修改则传列表中的  */
    private long  termid;
    /**  修改则传列表中的  */
    private String  usetype;
    /**  修改则传列表中的  */
    private String  vehicletype;
    /**  修改则传列表中的  */
    private String  vendor;
    /**  修改则传列表中的  */
    private String  videodeviceid;
    /**  修改则传列表中的  */
    private Date  enddate;
    /**  修改则传列表中的  */
    private Date  startdate;
    /**  修改则传列表中的  */
    private double  workhour;
    /**  修改则传列表中的  */
    private Date  buydate;
    /**  修改则传列表中的  */
    private String  engineno;
    /**  修改则传列表中的  */
    private String  frameno;
    /**  修改则传列表中的  */
    private String  manufacture;
    /**  修改则传列表中的  */
    private String  modelno;
    /**  修改则传列表中的  */
    private String  photo;
    /**  修改则传列表中的  */
    private long  companyid;
    /**  修改则传列表中的  */
    private long  videochannelnum;
    /**  修改则传列表中的  */
    private String  username;
    /**  修改则传列表中的  */
    private long  videodatatype;
    /**  修改则传列表中的  */
    private long  channelid;
    /**  修改则传列表中的  */
    private String  videodisk;
    /**  修改则传列表中的  */
    private String  videochannelnames;
    /**  修改则传列表中的  */
    private String  flowrateno;
    /**  修改则传列表中的  */
    private String  vehiclepassword;
    /**  上级平台的营运类型代码    (必填)	  字典维护修改则传列表中的  */
    private String  supertranstype;
    /**  吨（座）位修改则传列表中的  */
    private String  seatingcapacity;
    /**  道路运输证号修改则传列表中的  */
    private String  transserialno;
    /**  营运线路修改则传列表中的  */
    private String  routename;
    /**  服务开始时间修改则传列表中的  */
    private Date  servicestartdate;
    /**  服务结束时间修改则传列表中的  */
    private Date  serviceenddate;
    /**  入网时间修改则传列表中的  */
    private Date  inlinedate;
    /**  里程调整修改则传列表中的  */
    private String  mileageadjustment;
    /**  百公里油耗修改则传列表中的  */
    private String  fuelconsumption;
    /**  更新时间修改则传列表中的  */
    private Date  updatedate;
    /**  更新人修改则传列表中的  */
    private long  updatestaff;
    /**  创建人修改则传列表中的  */
    private long  createstaff;
    /**  车辆厂商修改则传列表中的  */
    private String  vehiclemanufacturer;
    /**  运营商id修改则传列表中的  */
    private long  operatorid;
    /**  协议版本修改则传列表中的  */
    private String  protocol_version;

public static void main(String[] args) {
String name="{\n";
        name +="  \"vehicleid\":1, //\n";
        name +="  \"owner\":\"测试\", //\n";
        name +="  \"remark\":\"测试\", //\n";
        name +="  \"buytime\":\"2020-09-11 00:00:00\", //\n";
        name +="  \"depid\":1, //\n";
        name +="  \"depname\":\"测试\", //\n";
        name +="  \"driver\":\"测试\", //\n";
        name +="  \"drivermobile\":\"测试\", //\n";
        name +="  \"gpsterminaltype\":\"测试\", //\n";
        name +="  \"industry\":\"测试\", //\n";
        name +="  \"installdate\":\"2020-09-11 00:00:00\", //\n";
        name +="  \"lastchecktime\":\"2020-09-11 00:00:00\", //\n";
        name +="  \"memberid\":1, //\n";
        name +="  \"monitor\":\"测试\", //\n";
        name +="  \"monitormobile\":\"测试\", //\n";
        name +="  \"motorid\":\"测试\", //\n";
        name +="  \"offlinetime\":\"2020-09-11 00:00:00\", //\n";
        name +="  \"onlinetime\":\"2020-09-11 00:00:00\", //\n";
        name +="  \"operpermit\":\"测试\", //\n";
        name +="  \"platecolor\":1, //\n";
        name +="  \"plateno\":\"测试\", //\n";
        name +="  \"region\":\"测试\", //\n";
        name +="  \"routes\":\"测试\", //\n";
        name +="  \"runstatus\":\"测试\", //\n";
        name +="  \"simno\":\"测试\", //\n";
        name +="  \"status\":\"测试\", //\n";
        name +="  \"termid\":1, //\n";
        name +="  \"usetype\":\"测试\", //\n";
        name +="  \"vehicletype\":\"测试\", //\n";
        name +="  \"vendor\":\"测试\", //\n";
        name +="  \"videodeviceid\":\"测试\", //\n";
        name +="  \"enddate\":\"2020-09-11 00:00:00\", //\n";
        name +="  \"startdate\":\"2020-09-11 00:00:00\", //\n";
        name +="  \"workhour\":0.0, //\n";
        name +="  \"buydate\":\"2020-09-11 00:00:00\", //\n";
        name +="  \"engineno\":\"测试\", //\n";
        name +="  \"frameno\":\"测试\", //\n";
        name +="  \"manufacture\":\"测试\", //\n";
        name +="  \"modelno\":\"测试\", //\n";
        name +="  \"photo\":\"测试\", //\n";
        name +="  \"companyid\":1, //\n";
        name +="  \"videochannelnum\":1, //\n";
        name +="  \"username\":\"测试\", //\n";
        name +="  \"videodatatype\":1, //\n";
        name +="  \"channelid\":1, //\n";
        name +="  \"videodisk\":\"测试\", //\n";
        name +="  \"videochannelnames\":\"测试\", //\n";
        name +="  \"flowrateno\":\"测试\", //\n";
        name +="  \"vehiclepassword\":\"测试\", //\n";
        name +="  \"supertranstype\":\"测试\", //上级平台的营运类型代码    (必填)	  字典维护\n";
        name +="  \"seatingcapacity\":\"测试\", //吨（座）位\n";
        name +="  \"transserialno\":\"测试\", //道路运输证号\n";
        name +="  \"routename\":\"测试\", //营运线路\n";
        name +="  \"servicestartdate\":\"2020-09-11 00:00:00\", //服务开始时间\n";
        name +="  \"serviceenddate\":\"2020-09-11 00:00:00\", //服务结束时间\n";
        name +="  \"inlinedate\":\"2020-09-11 00:00:00\", //入网时间\n";
        name +="  \"mileageadjustment\":\"\", //里程调整\n";
        name +="  \"fuelconsumption\":\"\", //百公里油耗\n";
        name +="  \"updatestaff\":1, //更新人\n";
        name +="  \"createstaff\":1, //创建人\n";
        name +="  \"vehiclemanufacturer\":\"测试\", //车辆厂商\n";
        name +="  \"operatorid\":1, //运营商id\n";
        name +="  \"protocol_version\":\"测试\" //协议版本\n";
name+="}";
System.out.println(name);
name ="    {\"success\":true, \n "+
" \"code\":200,\n " +
" \"message\":\"success\", \n " +
" \"data\": " +name+
", \n \"total\":1} ";
System.out.println(name);
}

}