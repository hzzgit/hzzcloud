package common.vehiclereport.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class VehiclereportExlVo  extends BaseRowModel  {

    @ExcelProperty(value = {"序号"} )
    private int id;
    /**  车牌号  */
    @ExcelProperty(value = {"车牌号"} )
    private String  plateno;
    /**  颜色  */
    @ExcelProperty(value = {"颜色"} )
    private String  color;
    /**  吨座位  */
    @ExcelProperty(value = {"吨座位"} )
    private String  ton;
    /**  运输证号  */
    @ExcelProperty(value = {"运输证号"} )
    private String  transportcard;
    /**  技术等级  */
    @ExcelProperty(value = {"技术等级"} )
    private String  jilevel;
    /**  等级评定日  */
    @ExcelProperty(value = {"等级评定日"} )
    private Date  jileveldate;
    /**  下次综检日  */
    @ExcelProperty(value = {"下次综检日"} )
    private Date  aftercheckdate;
    /**  末次二维日  */
    @ExcelProperty(value = {"末次二维日"} )
    private Date  lastweidate;
    /**  下次维护日  */
    @ExcelProperty(value = {"下次维护日"} )
    private Date  afterweidate;
    /**  运输企业  */
    @ExcelProperty(value = {"运输企业"} )
    private String  transportdep;
    /**  上次综检日  */
    @ExcelProperty(value = {"上次综检日"} )
    private Date  beforecheckdate;
    /**  车辆类型  */
    @ExcelProperty(value = {"车辆类型"} )
    private String  vehicletype;
    /**  年审日期  */
    @ExcelProperty(value = {"年审日期"} )
    private Date  yearcheckdate;
    /**  年审结果  */
    @ExcelProperty(value = {"年审结果"} )
    private String  yearcheckresult;
    /**  状态  */
    @ExcelProperty(value = {"状态"} )
    private String  state;
    /**  车籍地  */
    @ExcelProperty(value = {"车籍地"} )
    private String  vehicleaddress;
    /**  企业许可证  */
    @ExcelProperty(value = {"企业许可证"} )
    private String  deplicence;
    /**  创建日期  */
    @ExcelProperty(value = {"创建日期"} )
    private String  vehiclecreatetime;
    /**  车主  */
    @ExcelProperty(value = {"车主"} )
    private String  vehicleowner;
    /**  运输类型  */
    @ExcelProperty(value = {"运输类型"} )
    private String  transporttype;
    /**  行驶证登记日  */
    @ExcelProperty(value = {"行驶证登记日"} )
    private Date  drivercardregisterdate;
    /**  行政区域  */
    @ExcelProperty(value = {"行政区域"} )
    private String  region;
    /**  发动机号  */
    @ExcelProperty(value = {"发动机号"} )
    private String  enginenumber;
    /**  车架号  */
    @ExcelProperty(value = {"车架号"} )
    private String  vin;
    /**  厂牌型号  */
    @ExcelProperty(value = {"厂牌型号"} )
    private String  factoryplatemodel;
    /**  运输证发证日  */
    @ExcelProperty(value = {"运输证发证日"} )
    private Date  transportcardcreatedate;
    /**  经营范围  */
    @ExcelProperty(value = {"经营范围"} )
    private String  businessscope;
    /**  危货审批  */
    @ExcelProperty(value = {"危货审批"} )
    private String  dangercargocheck;
    /**  驾驶员姓名  */
    @ExcelProperty(value = {"驾驶员姓名"} )
    private String  drivername;
    /**  从业资格证号  */
    @ExcelProperty(value = {"从业资格证号"} )
    private String  certificatecode;
    /**  押运员  */
    @ExcelProperty(value = {"押运员"} )
    private String  supercargo;
    /**  押运员资格证号  */
    @ExcelProperty(value = {"押运员资格证号"} )
    private String  supercargocertificatecode;
    /**  承运货种  */
    @ExcelProperty(value = {"承运货种"} )
    private String  cargotype;
    /**  标志灯编号  */
    @ExcelProperty(value = {"标志灯编号"} )
    private String  signlightnumber;
    /**  标志灯安装日期  */
    @ExcelProperty(value = {"标志灯安装日期"} )
    private Date  signlightinsdate;
    /**  标志灯有效期  */
    @ExcelProperty(value = {"标志灯有效期"} )
    private Date  signlightvaliditydate;
    /**  标志牌有效期  */
    @ExcelProperty(value = {"标志牌有效期"} )
    private Date  signboardvaliditydate;
    /**  承运人责任险  */
    @ExcelProperty(value = {"承运人责任险"} )
    private String  carriersrisk;
    /**  承运人责任险有效期  */
    @ExcelProperty(value = {"承运人责任险有效期"} )
    private Date  carriersriskvaliditydate;
    /**  使用GPS  */
    @ExcelProperty(value = {"使用GPS"} )
    private String  isusegps;
    /**  有行车记录仪  */
    @ExcelProperty(value = {"有行车记录仪"} )
    private String  isusedriverrecorder;
    /**  带行车记录仪的GPS  */
    @ExcelProperty(value = {"带行车记录仪的GPS"} )
    private String  isusegpsanddervercorder;
    /**  安检有效期  */
    @ExcelProperty(value = {"安检有效期"} )
    private Date  safecheckdate;
    /**  违章次数  */
    @ExcelProperty(value = {"违章次数"} )
    private Long  violationnum;
    /**  交警状态  */
    @ExcelProperty(value = {"交警状态"} )
    private String  policestate;
    /**  二级维护周期  */
    @ExcelProperty(value = {"二级维护周期"} )
    private String  secondmaintenancecycle;
    /**  二级维护间隔里程  */
    @ExcelProperty(value = {"二级维护间隔里程"} )
    private String  secondmaintenancemileage;
    /**  燃料种类  */
    @ExcelProperty(value = {"燃料种类"} )
    private String  fueltype;
    /**  驾驶员  */
    @ExcelProperty(value = {"驾驶员"} )
    private String  driver;
    /**  驾驶员资格证  */
    @ExcelProperty(value = {"驾驶员资格证"} )
    private String  drivercard;
    /**  驾驶员身份证  */
    @ExcelProperty(value = {"驾驶员身份证"} )
    private String  driversfzh;
    /**  行驶证首发日期  */
    @ExcelProperty(value = {"行驶证首发日期"} )
    private Date  drivercardfirstdate;
    /**  运输证首发日期  */
    @ExcelProperty(value = {"运输证首发日期"} )
    private Date  transportcardfirstdate;
    /**  满载总质量  */
    @ExcelProperty(value = {"满载总质量"} )
    private Long  fulltotalmass;
    /**  长  */
    @ExcelProperty(value = {"长"} )
    private Long  length;
    /**  宽  */
    @ExcelProperty(value = {"宽"} )
    private Long  width;
    /**  高  */
    @ExcelProperty(value = {"高"} )
    private Long  height;
    /**  企业手机  */
    @ExcelProperty(value = {"企业手机"} )
    private String  depmobilephone;
    /**  企业电话  */
    @ExcelProperty(value = {"企业电话"} )
    private String  deptelephone;
    /**  车身颜色  */
    @ExcelProperty(value = {"车身颜色"} )
    private String  carbodycolor;
    /**  运力来源  */
    @ExcelProperty(value = {"运力来源"} )
    private String  capacitysource;
    /**  准牵引总质量  */
    @ExcelProperty(value = {"准牵引总质量"} )
    private Long  tractionmass;
    /**  二维维修企业  */
    @ExcelProperty(value = {"二维维修企业"} )
    private String  twomaintaindep;
    /**  综检单位  */
    @ExcelProperty(value = {"综检单位"} )
    private String  allinspection;
    /**  交警车型  */
    @ExcelProperty(value = {"交警车型"} )
    private String  policecartype;
    /**  创建时间  */
    @ExcelProperty(value = {"创建时间"} )
    private Date  createtime;
    /**  创建用户  */
    @ExcelProperty(value = {"创建用户"} )
    private Long  userid;


}