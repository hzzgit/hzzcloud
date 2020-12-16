package templatestree.vehiclereport.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import com.ltmonitor.web.util.quanxianUtil;
import com.ltmonitor.web.util.*;
import com.ltmonitor.util.StringUtil;
import org.apache.commons.lang.StringUtils;
/**
* 企业车辆信息表维护表接口层
*/
@Controller
@RequestMapping("/vehiclereport")
@Slf4j
public class VehiclereportController  extends GenericAction{

@Autowired
private VehiclereportService vehiclereportservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;


/**
* 保存企业车辆信息表维护表 ,使用json请求，表单数据是解释含义，不需要传
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Vehiclereport vehiclereport, HttpServletRequest request
)
throws Exception {
OnlineUser onlineUser = getOnlineUser();
vehiclereportservice.save(vehiclereport,  onlineUser.getEntityId());
return new JsonMessage(true, "操作成功");
}


/**
* 删除企业车辆信息表维护表
* @param id 主键
* @return
*/
@ResponseBody
@RequestMapping("/delete.action")
public JsonMessage delete(
String id) throws Exception {
vehiclereportservice.fdelete(id);
return new JsonMessage(true, "操作成功");
}

/**
* 查询列表
        *   @param  id 主键
        *   @param  plateno 车牌号
        *   @param  color 颜色
        *   @param  ton 吨座位
        *   @param  transportcard 运输证号
        *   @param  jilevel 技术等级
        *   @param  startjileveldate 等级评定日开始时间
        *   @param  endjileveldate 等级评定日结束时间
        *   @param  startaftercheckdate 下次综检日开始时间
        *   @param  endaftercheckdate 下次综检日结束时间
        *   @param  startlastweidate 末次二维日开始时间
        *   @param  endlastweidate 末次二维日结束时间
        *   @param  startafterweidate 下次维护日开始时间
        *   @param  endafterweidate 下次维护日结束时间
        *   @param  transportdep 运输企业
        *   @param  startbeforecheckdate 上次综检日开始时间
        *   @param  endbeforecheckdate 上次综检日结束时间
        *   @param  vehicletype 车辆类型
        *   @param  startyearcheckdate 年审日期开始时间
        *   @param  endyearcheckdate 年审日期结束时间
        *   @param  yearcheckresult 年审结果
        *   @param  state 状态
        *   @param  vehicleaddress 车籍地
        *   @param  deplicence 企业许可证
        *   @param  vehiclecreatetime 创建日期
        *   @param  vehicleowner 车主
        *   @param  transporttype 运输类型
        *   @param  startdrivercardregisterdate 行驶证登记日开始时间
        *   @param  enddrivercardregisterdate 行驶证登记日结束时间
        *   @param  region 行政区域
        *   @param  enginenumber 发动机号
        *   @param  vin 车架号
        *   @param  factoryplatemodel 厂牌型号
        *   @param  starttransportcardcreatedate 运输证发证日开始时间
        *   @param  endtransportcardcreatedate 运输证发证日结束时间
        *   @param  businessscope 经营范围
        *   @param  dangercargocheck 危货审批
        *   @param  drivername 驾驶员姓名
        *   @param  certificatecode 从业资格证号
        *   @param  supercargo 押运员
        *   @param  supercargocertificatecode 押运员资格证号
        *   @param  cargotype 承运货种
        *   @param  signlightnumber 标志灯编号
        *   @param  startsignlightinsdate 标志灯安装日期开始时间
        *   @param  endsignlightinsdate 标志灯安装日期结束时间
        *   @param  startsignlightvaliditydate 标志灯有效期开始时间
        *   @param  endsignlightvaliditydate 标志灯有效期结束时间
        *   @param  startsignboardvaliditydate 标志牌有效期开始时间
        *   @param  endsignboardvaliditydate 标志牌有效期结束时间
        *   @param  carriersrisk 承运人责任险
        *   @param  startcarriersriskvaliditydate 承运人责任险有效期开始时间
        *   @param  endcarriersriskvaliditydate 承运人责任险有效期结束时间
        *   @param  isusegps 使用GPS
        *   @param  isusedriverrecorder 有行车记录仪
        *   @param  isusegpsanddervercorder 带行车记录仪的GPS
        *   @param  startsafecheckdate 安检有效期开始时间
        *   @param  endsafecheckdate 安检有效期结束时间
        *   @param  violationnum 违章次数
        *   @param  policestate 交警状态
        *   @param  secondmaintenancecycle 二级维护周期
        *   @param  secondmaintenancemileage 二级维护间隔里程
        *   @param  fueltype 燃料种类
        *   @param  driver 驾驶员
        *   @param  drivercard 驾驶员资格证
        *   @param  driversfzh 驾驶员身份证
        *   @param  startdrivercardfirstdate 行驶证首发日期开始时间
        *   @param  enddrivercardfirstdate 行驶证首发日期结束时间
        *   @param  starttransportcardfirstdate 运输证首发日期开始时间
        *   @param  endtransportcardfirstdate 运输证首发日期结束时间
        *   @param  fulltotalmass 满载总质量
        *   @param  length 长
        *   @param  width 宽
        *   @param  height 高
        *   @param  depmobilephone 企业手机
        *   @param  deptelephone 企业电话
        *   @param  carbodycolor 车身颜色
        *   @param  capacitysource 运力来源
        *   @param  tractionmass 准牵引总质量
        *   @param  twomaintaindep 二维维修企业
        *   @param  allinspection 综检单位
        *   @param  policecartype 交警车型
        *   @param  starttime 统计日期开始时间
        *   @param  endtime 统计日期结束时间
        *   @param  startcreatetime 创建时间开始时间
        *   @param  endcreatetime 创建时间结束时间
        *   @param  userid 创建用户
* @return
*/
@ResponseBody
@RequestMapping("/selectlist.action")
public PaginateResult selectlist(
/**
*当前页
*/
@RequestParam(value = "page", defaultValue = "1") int page,
/**
*一页多少条
*/
@RequestParam(value = "rows", defaultValue = "10") int rows,
/**
*要进行查询的机构id,用逗号隔开
*/
@RequestParam(required = false) Long[] depIds,
        /**
        *主键
        */
        @RequestParam(required = false) Long  id,
        /**
        *车牌号
        */
        @RequestParam(required = false) String  plateno,
        /**
        *颜色
        */
        @RequestParam(required = false) String  color,
        /**
        *吨座位
        */
        @RequestParam(required = false) String  ton,
        /**
        *运输证号
        */
        @RequestParam(required = false) String  transportcard,
        /**
        *技术等级
        */
        @RequestParam(required = false) String  jilevel,
        /**
        *等级评定日
        */
        @RequestParam(required = false) Date  startjileveldate,
        @RequestParam(required = false) Date  endjileveldate,
        /**
        *下次综检日
        */
        @RequestParam(required = false) Date  startaftercheckdate,
        @RequestParam(required = false) Date  endaftercheckdate,
        /**
        *末次二维日
        */
        @RequestParam(required = false) Date  startlastweidate,
        @RequestParam(required = false) Date  endlastweidate,
        /**
        *下次维护日
        */
        @RequestParam(required = false) Date  startafterweidate,
        @RequestParam(required = false) Date  endafterweidate,
        /**
        *运输企业
        */
        @RequestParam(required = false) String  transportdep,
        /**
        *上次综检日
        */
        @RequestParam(required = false) Date  startbeforecheckdate,
        @RequestParam(required = false) Date  endbeforecheckdate,
        /**
        *车辆类型
        */
        @RequestParam(required = false) String  vehicletype,
        /**
        *年审日期
        */
        @RequestParam(required = false) Date  startyearcheckdate,
        @RequestParam(required = false) Date  endyearcheckdate,
        /**
        *年审结果
        */
        @RequestParam(required = false) String  yearcheckresult,
        /**
        *状态
        */
        @RequestParam(required = false) String  state,
        /**
        *车籍地
        */
        @RequestParam(required = false) String  vehicleaddress,
        /**
        *企业许可证
        */
        @RequestParam(required = false) String  deplicence,
        /**
        *创建日期
        */
        @RequestParam(required = false) String  vehiclecreatetime,
        /**
        *车主
        */
        @RequestParam(required = false) String  vehicleowner,
        /**
        *运输类型
        */
        @RequestParam(required = false) String  transporttype,
        /**
        *行驶证登记日
        */
        @RequestParam(required = false) Date  startdrivercardregisterdate,
        @RequestParam(required = false) Date  enddrivercardregisterdate,
        /**
        *行政区域
        */
        @RequestParam(required = false) String  region,
        /**
        *发动机号
        */
        @RequestParam(required = false) String  enginenumber,
        /**
        *车架号
        */
        @RequestParam(required = false) String  vin,
        /**
        *厂牌型号
        */
        @RequestParam(required = false) String  factoryplatemodel,
        /**
        *运输证发证日
        */
        @RequestParam(required = false) Date  starttransportcardcreatedate,
        @RequestParam(required = false) Date  endtransportcardcreatedate,
        /**
        *经营范围
        */
        @RequestParam(required = false) String  businessscope,
        /**
        *危货审批
        */
        @RequestParam(required = false) String  dangercargocheck,
        /**
        *驾驶员姓名
        */
        @RequestParam(required = false) String  drivername,
        /**
        *从业资格证号
        */
        @RequestParam(required = false) String  certificatecode,
        /**
        *押运员
        */
        @RequestParam(required = false) String  supercargo,
        /**
        *押运员资格证号
        */
        @RequestParam(required = false) String  supercargocertificatecode,
        /**
        *承运货种
        */
        @RequestParam(required = false) String  cargotype,
        /**
        *标志灯编号
        */
        @RequestParam(required = false) String  signlightnumber,
        /**
        *标志灯安装日期
        */
        @RequestParam(required = false) Date  startsignlightinsdate,
        @RequestParam(required = false) Date  endsignlightinsdate,
        /**
        *标志灯有效期
        */
        @RequestParam(required = false) Date  startsignlightvaliditydate,
        @RequestParam(required = false) Date  endsignlightvaliditydate,
        /**
        *标志牌有效期
        */
        @RequestParam(required = false) Date  startsignboardvaliditydate,
        @RequestParam(required = false) Date  endsignboardvaliditydate,
        /**
        *承运人责任险
        */
        @RequestParam(required = false) String  carriersrisk,
        /**
        *承运人责任险有效期
        */
        @RequestParam(required = false) Date  startcarriersriskvaliditydate,
        @RequestParam(required = false) Date  endcarriersriskvaliditydate,
        /**
        *使用GPS
        */
        @RequestParam(required = false) String  isusegps,
        /**
        *有行车记录仪
        */
        @RequestParam(required = false) String  isusedriverrecorder,
        /**
        *带行车记录仪的GPS
        */
        @RequestParam(required = false) String  isusegpsanddervercorder,
        /**
        *安检有效期
        */
        @RequestParam(required = false) Date  startsafecheckdate,
        @RequestParam(required = false) Date  endsafecheckdate,
        /**
        *违章次数
        */
        @RequestParam(required = false) Long  violationnum,
        /**
        *交警状态
        */
        @RequestParam(required = false) String  policestate,
        /**
        *二级维护周期
        */
        @RequestParam(required = false) String  secondmaintenancecycle,
        /**
        *二级维护间隔里程
        */
        @RequestParam(required = false) String  secondmaintenancemileage,
        /**
        *燃料种类
        */
        @RequestParam(required = false) String  fueltype,
        /**
        *驾驶员
        */
        @RequestParam(required = false) String  driver,
        /**
        *驾驶员资格证
        */
        @RequestParam(required = false) String  drivercard,
        /**
        *驾驶员身份证
        */
        @RequestParam(required = false) String  driversfzh,
        /**
        *行驶证首发日期
        */
        @RequestParam(required = false) Date  startdrivercardfirstdate,
        @RequestParam(required = false) Date  enddrivercardfirstdate,
        /**
        *运输证首发日期
        */
        @RequestParam(required = false) Date  starttransportcardfirstdate,
        @RequestParam(required = false) Date  endtransportcardfirstdate,
        /**
        *满载总质量
        */
        @RequestParam(required = false) Long  fulltotalmass,
        /**
        *长
        */
        @RequestParam(required = false) Long  length,
        /**
        *宽
        */
        @RequestParam(required = false) Long  width,
        /**
        *高
        */
        @RequestParam(required = false) Long  height,
        /**
        *企业手机
        */
        @RequestParam(required = false) String  depmobilephone,
        /**
        *企业电话
        */
        @RequestParam(required = false) String  deptelephone,
        /**
        *车身颜色
        */
        @RequestParam(required = false) String  carbodycolor,
        /**
        *运力来源
        */
        @RequestParam(required = false) String  capacitysource,
        /**
        *准牵引总质量
        */
        @RequestParam(required = false) Long  tractionmass,
        /**
        *二维维修企业
        */
        @RequestParam(required = false) String  twomaintaindep,
        /**
        *综检单位
        */
        @RequestParam(required = false) String  allinspection,
        /**
        *交警车型
        */
        @RequestParam(required = false) String  policecartype,
        /**
        *统计日期
        */
        @RequestParam(required = false) Date  starttime,
        @RequestParam(required = false) Date  endtime,
        /**
        *创建时间
        */
        @RequestParam(required = false) Date  startcreatetime,
        @RequestParam(required = false) Date  endcreatetime,
        /**
        *创建用户
        */
        @RequestParam(required = false) Long  userid,
HttpServletRequest request
) {
PaginateResult paginateResult = new PaginateResult(500, "服务器错误");
try {
Map params = getParams(request);
OnlineUser onlineUser = getOnlineUser();
if(onlineUser.isSuperAdmin()){
params.put("owner", onlineUser.getEntityId());
params.put("userid", onlineUser.getEntityId());
}
Uservehicleauthority uservehicleauthority=getUservehicleauthorityByWeb(depIds);
quanxianUtil.getquanxian(uservehicleauthority, params);
return vehiclereportservice.selectlist(params, page, rows);
} catch (Exception e) {
log.error("企业车辆信息表维护表查询列表失败", e);
return paginateResult;
}
}


/**
* 导出列表文件
        *   @param  id 主键
        *   @param  plateno 车牌号
        *   @param  color 颜色
        *   @param  ton 吨座位
        *   @param  transportcard 运输证号
        *   @param  jilevel 技术等级
        *   @param  startjileveldate 等级评定日开始时间
        *   @param  endjileveldate 等级评定日结束时间
        *   @param  startaftercheckdate 下次综检日开始时间
        *   @param  endaftercheckdate 下次综检日结束时间
        *   @param  startlastweidate 末次二维日开始时间
        *   @param  endlastweidate 末次二维日结束时间
        *   @param  startafterweidate 下次维护日开始时间
        *   @param  endafterweidate 下次维护日结束时间
        *   @param  transportdep 运输企业
        *   @param  startbeforecheckdate 上次综检日开始时间
        *   @param  endbeforecheckdate 上次综检日结束时间
        *   @param  vehicletype 车辆类型
        *   @param  startyearcheckdate 年审日期开始时间
        *   @param  endyearcheckdate 年审日期结束时间
        *   @param  yearcheckresult 年审结果
        *   @param  state 状态
        *   @param  vehicleaddress 车籍地
        *   @param  deplicence 企业许可证
        *   @param  vehiclecreatetime 创建日期
        *   @param  vehicleowner 车主
        *   @param  transporttype 运输类型
        *   @param  startdrivercardregisterdate 行驶证登记日开始时间
        *   @param  enddrivercardregisterdate 行驶证登记日结束时间
        *   @param  region 行政区域
        *   @param  enginenumber 发动机号
        *   @param  vin 车架号
        *   @param  factoryplatemodel 厂牌型号
        *   @param  starttransportcardcreatedate 运输证发证日开始时间
        *   @param  endtransportcardcreatedate 运输证发证日结束时间
        *   @param  businessscope 经营范围
        *   @param  dangercargocheck 危货审批
        *   @param  drivername 驾驶员姓名
        *   @param  certificatecode 从业资格证号
        *   @param  supercargo 押运员
        *   @param  supercargocertificatecode 押运员资格证号
        *   @param  cargotype 承运货种
        *   @param  signlightnumber 标志灯编号
        *   @param  startsignlightinsdate 标志灯安装日期开始时间
        *   @param  endsignlightinsdate 标志灯安装日期结束时间
        *   @param  startsignlightvaliditydate 标志灯有效期开始时间
        *   @param  endsignlightvaliditydate 标志灯有效期结束时间
        *   @param  startsignboardvaliditydate 标志牌有效期开始时间
        *   @param  endsignboardvaliditydate 标志牌有效期结束时间
        *   @param  carriersrisk 承运人责任险
        *   @param  startcarriersriskvaliditydate 承运人责任险有效期开始时间
        *   @param  endcarriersriskvaliditydate 承运人责任险有效期结束时间
        *   @param  isusegps 使用GPS
        *   @param  isusedriverrecorder 有行车记录仪
        *   @param  isusegpsanddervercorder 带行车记录仪的GPS
        *   @param  startsafecheckdate 安检有效期开始时间
        *   @param  endsafecheckdate 安检有效期结束时间
        *   @param  violationnum 违章次数
        *   @param  policestate 交警状态
        *   @param  secondmaintenancecycle 二级维护周期
        *   @param  secondmaintenancemileage 二级维护间隔里程
        *   @param  fueltype 燃料种类
        *   @param  driver 驾驶员
        *   @param  drivercard 驾驶员资格证
        *   @param  driversfzh 驾驶员身份证
        *   @param  startdrivercardfirstdate 行驶证首发日期开始时间
        *   @param  enddrivercardfirstdate 行驶证首发日期结束时间
        *   @param  starttransportcardfirstdate 运输证首发日期开始时间
        *   @param  endtransportcardfirstdate 运输证首发日期结束时间
        *   @param  fulltotalmass 满载总质量
        *   @param  length 长
        *   @param  width 宽
        *   @param  height 高
        *   @param  depmobilephone 企业手机
        *   @param  deptelephone 企业电话
        *   @param  carbodycolor 车身颜色
        *   @param  capacitysource 运力来源
        *   @param  tractionmass 准牵引总质量
        *   @param  twomaintaindep 二维维修企业
        *   @param  allinspection 综检单位
        *   @param  policecartype 交警车型
        *   @param  starttime 统计日期开始时间
        *   @param  endtime 统计日期结束时间
        *   @param  startcreatetime 创建时间开始时间
        *   @param  endcreatetime 创建时间结束时间
        *   @param  userid 创建用户
* @return
*/
@ResponseBody
@RequestMapping("/exportlist.action")
public void exportlist(
/**
*要进行查询的机构id,用逗号隔开
*/
@RequestParam(required = false) Long[] depIds,
        /**
        *主键
        */
        @RequestParam(value = "id",required = false) Long  id,
        /**
        *车牌号
        */
        @RequestParam(value = "plateno",required = false) String  plateno,
        /**
        *颜色
        */
        @RequestParam(value = "color",required = false) String  color,
        /**
        *吨座位
        */
        @RequestParam(value = "ton",required = false) String  ton,
        /**
        *运输证号
        */
        @RequestParam(value = "transportcard",required = false) String  transportcard,
        /**
        *技术等级
        */
        @RequestParam(value = "jilevel",required = false) String  jilevel,
        /**
        *等级评定日
        */
        @RequestParam(value = "jileveldate",required = false) Date  startjileveldate,
        @RequestParam(value = "jileveldate",required = false) Date  endjileveldate,
        /**
        *下次综检日
        */
        @RequestParam(value = "aftercheckdate",required = false) Date  startaftercheckdate,
        @RequestParam(value = "aftercheckdate",required = false) Date  endaftercheckdate,
        /**
        *末次二维日
        */
        @RequestParam(value = "lastweidate",required = false) Date  startlastweidate,
        @RequestParam(value = "lastweidate",required = false) Date  endlastweidate,
        /**
        *下次维护日
        */
        @RequestParam(value = "afterweidate",required = false) Date  startafterweidate,
        @RequestParam(value = "afterweidate",required = false) Date  endafterweidate,
        /**
        *运输企业
        */
        @RequestParam(value = "transportdep",required = false) String  transportdep,
        /**
        *上次综检日
        */
        @RequestParam(value = "beforecheckdate",required = false) Date  startbeforecheckdate,
        @RequestParam(value = "beforecheckdate",required = false) Date  endbeforecheckdate,
        /**
        *车辆类型
        */
        @RequestParam(value = "vehicletype",required = false) String  vehicletype,
        /**
        *年审日期
        */
        @RequestParam(value = "yearcheckdate",required = false) Date  startyearcheckdate,
        @RequestParam(value = "yearcheckdate",required = false) Date  endyearcheckdate,
        /**
        *年审结果
        */
        @RequestParam(value = "yearcheckresult",required = false) String  yearcheckresult,
        /**
        *状态
        */
        @RequestParam(value = "state",required = false) String  state,
        /**
        *车籍地
        */
        @RequestParam(value = "vehicleaddress",required = false) String  vehicleaddress,
        /**
        *企业许可证
        */
        @RequestParam(value = "deplicence",required = false) String  deplicence,
        /**
        *创建日期
        */
        @RequestParam(value = "vehiclecreatetime",required = false) String  vehiclecreatetime,
        /**
        *车主
        */
        @RequestParam(value = "vehicleowner",required = false) String  vehicleowner,
        /**
        *运输类型
        */
        @RequestParam(value = "transporttype",required = false) String  transporttype,
        /**
        *行驶证登记日
        */
        @RequestParam(value = "drivercardregisterdate",required = false) Date  startdrivercardregisterdate,
        @RequestParam(value = "drivercardregisterdate",required = false) Date  enddrivercardregisterdate,
        /**
        *行政区域
        */
        @RequestParam(value = "region",required = false) String  region,
        /**
        *发动机号
        */
        @RequestParam(value = "enginenumber",required = false) String  enginenumber,
        /**
        *车架号
        */
        @RequestParam(value = "vin",required = false) String  vin,
        /**
        *厂牌型号
        */
        @RequestParam(value = "factoryplatemodel",required = false) String  factoryplatemodel,
        /**
        *运输证发证日
        */
        @RequestParam(value = "transportcardcreatedate",required = false) Date  starttransportcardcreatedate,
        @RequestParam(value = "transportcardcreatedate",required = false) Date  endtransportcardcreatedate,
        /**
        *经营范围
        */
        @RequestParam(value = "businessscope",required = false) String  businessscope,
        /**
        *危货审批
        */
        @RequestParam(value = "dangercargocheck",required = false) String  dangercargocheck,
        /**
        *驾驶员姓名
        */
        @RequestParam(value = "drivername",required = false) String  drivername,
        /**
        *从业资格证号
        */
        @RequestParam(value = "certificatecode",required = false) String  certificatecode,
        /**
        *押运员
        */
        @RequestParam(value = "supercargo",required = false) String  supercargo,
        /**
        *押运员资格证号
        */
        @RequestParam(value = "supercargocertificatecode",required = false) String  supercargocertificatecode,
        /**
        *承运货种
        */
        @RequestParam(value = "cargotype",required = false) String  cargotype,
        /**
        *标志灯编号
        */
        @RequestParam(value = "signlightnumber",required = false) String  signlightnumber,
        /**
        *标志灯安装日期
        */
        @RequestParam(value = "signlightinsdate",required = false) Date  startsignlightinsdate,
        @RequestParam(value = "signlightinsdate",required = false) Date  endsignlightinsdate,
        /**
        *标志灯有效期
        */
        @RequestParam(value = "signlightvaliditydate",required = false) Date  startsignlightvaliditydate,
        @RequestParam(value = "signlightvaliditydate",required = false) Date  endsignlightvaliditydate,
        /**
        *标志牌有效期
        */
        @RequestParam(value = "signboardvaliditydate",required = false) Date  startsignboardvaliditydate,
        @RequestParam(value = "signboardvaliditydate",required = false) Date  endsignboardvaliditydate,
        /**
        *承运人责任险
        */
        @RequestParam(value = "carriersrisk",required = false) String  carriersrisk,
        /**
        *承运人责任险有效期
        */
        @RequestParam(value = "carriersriskvaliditydate",required = false) Date  startcarriersriskvaliditydate,
        @RequestParam(value = "carriersriskvaliditydate",required = false) Date  endcarriersriskvaliditydate,
        /**
        *使用GPS
        */
        @RequestParam(value = "isusegps",required = false) String  isusegps,
        /**
        *有行车记录仪
        */
        @RequestParam(value = "isusedriverrecorder",required = false) String  isusedriverrecorder,
        /**
        *带行车记录仪的GPS
        */
        @RequestParam(value = "isusegpsanddervercorder",required = false) String  isusegpsanddervercorder,
        /**
        *安检有效期
        */
        @RequestParam(value = "safecheckdate",required = false) Date  startsafecheckdate,
        @RequestParam(value = "safecheckdate",required = false) Date  endsafecheckdate,
        /**
        *违章次数
        */
        @RequestParam(value = "violationnum",required = false) Long  violationnum,
        /**
        *交警状态
        */
        @RequestParam(value = "policestate",required = false) String  policestate,
        /**
        *二级维护周期
        */
        @RequestParam(value = "secondmaintenancecycle",required = false) String  secondmaintenancecycle,
        /**
        *二级维护间隔里程
        */
        @RequestParam(value = "secondmaintenancemileage",required = false) String  secondmaintenancemileage,
        /**
        *燃料种类
        */
        @RequestParam(value = "fueltype",required = false) String  fueltype,
        /**
        *驾驶员
        */
        @RequestParam(value = "driver",required = false) String  driver,
        /**
        *驾驶员资格证
        */
        @RequestParam(value = "drivercard",required = false) String  drivercard,
        /**
        *驾驶员身份证
        */
        @RequestParam(value = "driversfzh",required = false) String  driversfzh,
        /**
        *行驶证首发日期
        */
        @RequestParam(value = "drivercardfirstdate",required = false) Date  startdrivercardfirstdate,
        @RequestParam(value = "drivercardfirstdate",required = false) Date  enddrivercardfirstdate,
        /**
        *运输证首发日期
        */
        @RequestParam(value = "transportcardfirstdate",required = false) Date  starttransportcardfirstdate,
        @RequestParam(value = "transportcardfirstdate",required = false) Date  endtransportcardfirstdate,
        /**
        *满载总质量
        */
        @RequestParam(value = "fulltotalmass",required = false) Long  fulltotalmass,
        /**
        *长
        */
        @RequestParam(value = "length",required = false) Long  length,
        /**
        *宽
        */
        @RequestParam(value = "width",required = false) Long  width,
        /**
        *高
        */
        @RequestParam(value = "height",required = false) Long  height,
        /**
        *企业手机
        */
        @RequestParam(value = "depmobilephone",required = false) String  depmobilephone,
        /**
        *企业电话
        */
        @RequestParam(value = "deptelephone",required = false) String  deptelephone,
        /**
        *车身颜色
        */
        @RequestParam(value = "carbodycolor",required = false) String  carbodycolor,
        /**
        *运力来源
        */
        @RequestParam(value = "capacitysource",required = false) String  capacitysource,
        /**
        *准牵引总质量
        */
        @RequestParam(value = "tractionmass",required = false) Long  tractionmass,
        /**
        *二维维修企业
        */
        @RequestParam(value = "twomaintaindep",required = false) String  twomaintaindep,
        /**
        *综检单位
        */
        @RequestParam(value = "allinspection",required = false) String  allinspection,
        /**
        *交警车型
        */
        @RequestParam(value = "policecartype",required = false) String  policecartype,
        /**
        *统计日期
        */
        @RequestParam(value = "time",required = false) Date  starttime,
        @RequestParam(value = "time",required = false) Date  endtime,
        /**
        *创建时间
        */
        @RequestParam(value = "createtime",required = false) Date  startcreatetime,
        @RequestParam(value = "createtime",required = false) Date  endcreatetime,
        /**
        *创建用户
        */
        @RequestParam(value = "userid",required = false) Long  userid,
HttpServletRequest request,
HttpServletResponse response
) {
try {
Map params = getParams(request);
Uservehicleauthority uservehicleauthority=getUservehicleauthorityByWeb(depIds);
quanxianUtil.getquanxian(uservehicleauthority, params);
PaginateResult selectmemberlist =vehiclereportservice.selectlist(params, 0, 0);
List lists = selectmemberlist.getRows();
List exldate = new ArrayList<>();
for (int i = 0; i < lists.size(); i++) {
Object row=lists.get(i);
VehiclereportExlVo vehiclereport = MaptoBeanUtil.mapToBean((Map
<String, Object>) row, VehiclereportExlVo.class);
vehiclereport.setId(i+1);
exldate.add(vehiclereport);
}
exceClasslUtil.exlport("企业车辆信息表维护表", request, response, exldate, VehiclereportExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
} catch (Exception e) {
log.error("企业车辆信息表维护表导出列表失败", e);
}
}


private  Map getParams(HttpServletRequest request) {
Map parameters = request.getParameterMap();
Map paraMap = new HashMap();
String rowdata = "";
if (parameters.size() > 0) {
for (Object key : parameters.keySet()) {
String strKey = "" + key;
int index = strKey.indexOf("[]");
if (index > 0) {
strKey = strKey.replaceAll("\\[\\]", "");
}
String[] values = (String[]) parameters.get(key);
if (values.length == 1) {
String strValue = values[0];
if (strValue != null && strValue.isEmpty() == false)
paraMap.put(strKey, strValue);
} else
paraMap.put(strKey, values);
}
} else {
BufferedReader br = null;

try {
br = request.getReader();
String line = br.readLine();
if (line == null) {

} else {
StringBuilder ret = new StringBuilder();
ret.append(line);

while ((line = br.readLine()) != null) {
ret.append('\n').append(line);
}

rowdata = ret.toString();
}
paraMap = (Map) JSON.parse(rowdata);
} catch (IOException var4) {
throw new RuntimeException(var4);
}
}
return paraMap;
}


}