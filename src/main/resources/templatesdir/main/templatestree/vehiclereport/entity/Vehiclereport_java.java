package templatestree.vehiclereport.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.vehiclereport")
public class Vehiclereport implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_plateno = "plateno";
    public static final String F_color = "color";
    public static final String F_ton = "ton";
    public static final String F_transportcard = "transportcard";
    public static final String F_jilevel = "jilevel";
    public static final String F_jileveldate = "jileveldate";
    public static final String F_aftercheckdate = "aftercheckdate";
    public static final String F_lastweidate = "lastweidate";
    public static final String F_afterweidate = "afterweidate";
    public static final String F_transportdep = "transportdep";
    public static final String F_beforecheckdate = "beforecheckdate";
    public static final String F_vehicletype = "vehicletype";
    public static final String F_yearcheckdate = "yearcheckdate";
    public static final String F_yearcheckresult = "yearcheckresult";
    public static final String F_state = "state";
    public static final String F_vehicleaddress = "vehicleaddress";
    public static final String F_deplicence = "deplicence";
    public static final String F_vehiclecreatetime = "vehiclecreatetime";
    public static final String F_vehicleowner = "vehicleowner";
    public static final String F_transporttype = "transporttype";
    public static final String F_drivercardregisterdate = "drivercardregisterdate";
    public static final String F_region = "region";
    public static final String F_enginenumber = "enginenumber";
    public static final String F_vin = "vin";
    public static final String F_factoryplatemodel = "factoryplatemodel";
    public static final String F_transportcardcreatedate = "transportcardcreatedate";
    public static final String F_businessscope = "businessscope";
    public static final String F_dangercargocheck = "dangercargocheck";
    public static final String F_drivername = "drivername";
    public static final String F_certificatecode = "certificatecode";
    public static final String F_supercargo = "supercargo";
    public static final String F_supercargocertificatecode = "supercargocertificatecode";
    public static final String F_cargotype = "cargotype";
    public static final String F_signlightnumber = "signlightnumber";
    public static final String F_signlightinsdate = "signlightinsdate";
    public static final String F_signlightvaliditydate = "signlightvaliditydate";
    public static final String F_signboardvaliditydate = "signboardvaliditydate";
    public static final String F_carriersrisk = "carriersrisk";
    public static final String F_carriersriskvaliditydate = "carriersriskvaliditydate";
    public static final String F_isusegps = "isusegps";
    public static final String F_isusedriverrecorder = "isusedriverrecorder";
    public static final String F_isusegpsanddervercorder = "isusegpsanddervercorder";
    public static final String F_safecheckdate = "safecheckdate";
    public static final String F_violationnum = "violationnum";
    public static final String F_policestate = "policestate";
    public static final String F_secondmaintenancecycle = "secondmaintenancecycle";
    public static final String F_secondmaintenancemileage = "secondmaintenancemileage";
    public static final String F_fueltype = "fueltype";
    public static final String F_driver = "driver";
    public static final String F_drivercard = "drivercard";
    public static final String F_driversfzh = "driversfzh";
    public static final String F_drivercardfirstdate = "drivercardfirstdate";
    public static final String F_transportcardfirstdate = "transportcardfirstdate";
    public static final String F_fulltotalmass = "fulltotalmass";
    public static final String F_length = "length";
    public static final String F_width = "width";
    public static final String F_height = "height";
    public static final String F_depmobilephone = "depmobilephone";
    public static final String F_deptelephone = "deptelephone";
    public static final String F_carbodycolor = "carbodycolor";
    public static final String F_capacitysource = "capacitysource";
    public static final String F_tractionmass = "tractionmass";
    public static final String F_twomaintaindep = "twomaintaindep";
    public static final String F_allinspection = "allinspection";
    public static final String F_policecartype = "policecartype";
    public static final String F_time = "time";
    public static final String F_createtime = "createtime";
    public static final String F_userid = "userid";


    /**  主键  */
        @DbId
    private Long  id;
    /**  车牌号  */
    private String  plateno;
    /**  颜色  */
    private String  color;
    /**  吨座位  */
    private String  ton;
    /**  运输证号  */
    private String  transportcard;
    /**  技术等级  */
    private String  jilevel;
    /**  等级评定日  */
    private Date  jileveldate;
    /**  下次综检日  */
    private Date  aftercheckdate;
    /**  末次二维日  */
    private Date  lastweidate;
    /**  下次维护日  */
    private Date  afterweidate;
    /**  运输企业  */
    private String  transportdep;
    /**  上次综检日  */
    private Date  beforecheckdate;
    /**  车辆类型  */
    private String  vehicletype;
    /**  年审日期  */
    private Date  yearcheckdate;
    /**  年审结果  */
    private String  yearcheckresult;
    /**  状态  */
    private String  state;
    /**  车籍地  */
    private String  vehicleaddress;
    /**  企业许可证  */
    private String  deplicence;
    /**  创建日期  */
    private String  vehiclecreatetime;
    /**  车主  */
    private String  vehicleowner;
    /**  运输类型  */
    private String  transporttype;
    /**  行驶证登记日  */
    private Date  drivercardregisterdate;
    /**  行政区域  */
    private String  region;
    /**  发动机号  */
    private String  enginenumber;
    /**  车架号  */
    private String  vin;
    /**  厂牌型号  */
    private String  factoryplatemodel;
    /**  运输证发证日  */
    private Date  transportcardcreatedate;
    /**  经营范围  */
    private String  businessscope;
    /**  危货审批  */
    private String  dangercargocheck;
    /**  驾驶员姓名  */
    private String  drivername;
    /**  从业资格证号  */
    private String  certificatecode;
    /**  押运员  */
    private String  supercargo;
    /**  押运员资格证号  */
    private String  supercargocertificatecode;
    /**  承运货种  */
    private String  cargotype;
    /**  标志灯编号  */
    private String  signlightnumber;
    /**  标志灯安装日期  */
    private Date  signlightinsdate;
    /**  标志灯有效期  */
    private Date  signlightvaliditydate;
    /**  标志牌有效期  */
    private Date  signboardvaliditydate;
    /**  承运人责任险  */
    private String  carriersrisk;
    /**  承运人责任险有效期  */
    private Date  carriersriskvaliditydate;
    /**  使用GPS  */
    private String  isusegps;
    /**  有行车记录仪  */
    private String  isusedriverrecorder;
    /**  带行车记录仪的GPS  */
    private String  isusegpsanddervercorder;
    /**  安检有效期  */
    private Date  safecheckdate;
    /**  违章次数  */
    private Long  violationnum;
    /**  交警状态  */
    private String  policestate;
    /**  二级维护周期  */
    private String  secondmaintenancecycle;
    /**  二级维护间隔里程  */
    private String  secondmaintenancemileage;
    /**  燃料种类  */
    private String  fueltype;
    /**  驾驶员  */
    private String  driver;
    /**  驾驶员资格证  */
    private String  drivercard;
    /**  驾驶员身份证  */
    private String  driversfzh;
    /**  行驶证首发日期  */
    private Date  drivercardfirstdate;
    /**  运输证首发日期  */
    private Date  transportcardfirstdate;
    /**  满载总质量  */
    private Long  fulltotalmass;
    /**  长  */
    private Long  length;
    /**  宽  */
    private Long  width;
    /**  高  */
    private Long  height;
    /**  企业手机  */
    private String  depmobilephone;
    /**  企业电话  */
    private String  deptelephone;
    /**  车身颜色  */
    private String  carbodycolor;
    /**  运力来源  */
    private String  capacitysource;
    /**  准牵引总质量  */
    private Long  tractionmass;
    /**  二维维修企业  */
    private String  twomaintaindep;
    /**  综检单位  */
    private String  allinspection;
    /**  交警车型  */
    private String  policecartype;
    /**  统计日期  */
        @DbId
    private Date  time;
    /**  创建时间  */
    private Date  createtime;
    /**  创建用户  */
    private Long  userid;

public static void main(String[] args) {
String name="{\n";
    name +="  \"id\":0, //主键\n";
    name +="  \"plateno\":\"\", //车牌号\n";
    name +="  \"color\":\"\", //颜色\n";
    name +="  \"ton\":\"\", //吨座位\n";
    name +="  \"transportcard\":\"\", //运输证号\n";
    name +="  \"jilevel\":\"\", //技术等级\n";
    name +="  \"jileveldate\":\"2020-09-11 00:00:00\", //等级评定日\n";
    name +="  \"aftercheckdate\":\"2020-09-11 00:00:00\", //下次综检日\n";
    name +="  \"lastweidate\":\"2020-09-11 00:00:00\", //末次二维日\n";
    name +="  \"afterweidate\":\"2020-09-11 00:00:00\", //下次维护日\n";
    name +="  \"transportdep\":\"\", //运输企业\n";
    name +="  \"beforecheckdate\":\"2020-09-11 00:00:00\", //上次综检日\n";
    name +="  \"vehicletype\":\"\", //车辆类型\n";
    name +="  \"yearcheckdate\":\"2020-09-11 00:00:00\", //年审日期\n";
    name +="  \"yearcheckresult\":\"\", //年审结果\n";
    name +="  \"state\":\"\", //状态\n";
    name +="  \"vehicleaddress\":\"\", //车籍地\n";
    name +="  \"deplicence\":\"\", //企业许可证\n";
    name +="  \"vehiclecreatetime\":\"\", //创建日期\n";
    name +="  \"vehicleowner\":\"\", //车主\n";
    name +="  \"transporttype\":\"\", //运输类型\n";
    name +="  \"drivercardregisterdate\":\"2020-09-11 00:00:00\", //行驶证登记日\n";
    name +="  \"region\":\"\", //行政区域\n";
    name +="  \"enginenumber\":\"\", //发动机号\n";
    name +="  \"vin\":\"\", //车架号\n";
    name +="  \"factoryplatemodel\":\"\", //厂牌型号\n";
    name +="  \"transportcardcreatedate\":\"2020-09-11 00:00:00\", //运输证发证日\n";
    name +="  \"businessscope\":\"\", //经营范围\n";
    name +="  \"dangercargocheck\":\"\", //危货审批\n";
    name +="  \"drivername\":\"\", //驾驶员姓名\n";
    name +="  \"certificatecode\":\"\", //从业资格证号\n";
    name +="  \"supercargo\":\"\", //押运员\n";
    name +="  \"supercargocertificatecode\":\"\", //押运员资格证号\n";
    name +="  \"cargotype\":\"\", //承运货种\n";
    name +="  \"signlightnumber\":\"\", //标志灯编号\n";
    name +="  \"signlightinsdate\":\"2020-09-11 00:00:00\", //标志灯安装日期\n";
    name +="  \"signlightvaliditydate\":\"2020-09-11 00:00:00\", //标志灯有效期\n";
    name +="  \"signboardvaliditydate\":\"2020-09-11 00:00:00\", //标志牌有效期\n";
    name +="  \"carriersrisk\":\"\", //承运人责任险\n";
    name +="  \"carriersriskvaliditydate\":\"2020-09-11 00:00:00\", //承运人责任险有效期\n";
    name +="  \"isusegps\":\"\", //使用GPS\n";
    name +="  \"isusedriverrecorder\":\"\", //有行车记录仪\n";
    name +="  \"isusegpsanddervercorder\":\"\", //带行车记录仪的GPS\n";
    name +="  \"safecheckdate\":\"2020-09-11 00:00:00\", //安检有效期\n";
    name +="  \"violationnum\":0, //违章次数\n";
    name +="  \"policestate\":\"\", //交警状态\n";
    name +="  \"secondmaintenancecycle\":\"\", //二级维护周期\n";
    name +="  \"secondmaintenancemileage\":\"\", //二级维护间隔里程\n";
    name +="  \"fueltype\":\"\", //燃料种类\n";
    name +="  \"driver\":\"\", //驾驶员\n";
    name +="  \"drivercard\":\"\", //驾驶员资格证\n";
    name +="  \"driversfzh\":\"\", //驾驶员身份证\n";
    name +="  \"drivercardfirstdate\":\"2020-09-11 00:00:00\", //行驶证首发日期\n";
    name +="  \"transportcardfirstdate\":\"2020-09-11 00:00:00\", //运输证首发日期\n";
    name +="  \"fulltotalmass\":0, //满载总质量\n";
    name +="  \"length\":0, //长\n";
    name +="  \"width\":0, //宽\n";
    name +="  \"height\":0, //高\n";
    name +="  \"depmobilephone\":\"\", //企业手机\n";
    name +="  \"deptelephone\":\"\", //企业电话\n";
    name +="  \"carbodycolor\":\"\", //车身颜色\n";
    name +="  \"capacitysource\":\"\", //运力来源\n";
    name +="  \"tractionmass\":0, //准牵引总质量\n";
    name +="  \"twomaintaindep\":\"\", //二维维修企业\n";
    name +="  \"allinspection\":\"\", //综检单位\n";
    name +="  \"policecartype\":\"\", //交警车型\n";
    name +="  \"time\":\"2020-09-11 00:00:00\", //统计日期\n";
    name +="  \"createtime\":\"2020-09-11 00:00:00\", //创建时间\n";
    name +="  \"userid\":0 //创建用户\n";
name+="}";
System.out.println(name);

}

}