package nocommon.vehicle.controller;

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
* 车辆表接口层
*/
@Controller
@RequestMapping("/vehicle")
@Slf4j
public class VehicleController  extends GenericAction{

@Autowired
private VehicleService vehicleservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;


/**
* 保存车辆表 ,使用json请求，表单数据是解释含义，不需要传
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Vehicle vehicle, HttpServletRequest request
)
throws Exception {
vehicleservice.save(vehicle);
return new JsonMessage(true, "操作成功");
}


/**
* 删除车辆表
* @param id 主键
* @return
*/
@ResponseBody
@RequestMapping("/delete.action")
public JsonMessage delete(
String id) throws Exception {
vehicleservice.fdelete(id);
return new JsonMessage(true, "操作成功");
}

/**
* 查询列表
        *   @param  vehicleid 
        *   @param  startcreatedate 开始时间
        *   @param  endcreatedate 结束时间
        *   @param  deleted 
        *   @param  owner 
        *   @param  remark 
        *   @param  startbuytime 开始时间
        *   @param  endbuytime 结束时间
        *   @param  depid 机构id
        *   @param  depname 
        *   @param  driver 
        *   @param  drivermobile 
        *   @param  gpsterminaltype 
        *   @param  industry 运输行业编码
        *   @param  startinstalldate 安装时间开始时间
        *   @param  endinstalldate 安装时间结束时间
        *   @param  startlastchecktime 开始时间
        *   @param  endlastchecktime 结束时间
        *   @param  memberid 业户id
        *   @param  monitor 
        *   @param  monitormobile 
        *   @param  motorid 
        *   @param  startofflinetime 开始时间
        *   @param  endofflinetime 结束时间
        *   @param  startonlinetime 开始时间
        *   @param  endonlinetime 结束时间
        *   @param  operpermit 
        *   @param  platecolor 车辆颜色
        *   @param  plateno 车牌号
        *   @param  region 车籍地
        *   @param  routes 
        *   @param  runstatus 运行状态
        *   @param  simno 
        *   @param  status 
        *   @param  termid 
        *   @param  usetype 
        *   @param  vehicletype 车辆类型
        *   @param  vendor 
        *   @param  videodeviceid 
        *   @param  startenddate 开始时间
        *   @param  endenddate 结束时间
        *   @param  startstartdate 开始时间
        *   @param  endstartdate 结束时间
        *   @param  startbuydate 开始时间
        *   @param  endbuydate 结束时间
        *   @param  engineno 
        *   @param  frameno 
        *   @param  manufacture 
        *   @param  modelno 
        *   @param  photo 
        *   @param  companyid 
        *   @param  videochannelnum 视频通道数
        *   @param  username 
        *   @param  videodatatype 
        *   @param  channelid 
        *   @param  videodisk 
        *   @param  videochannelnames 视频通道名称
        *   @param  flowrateno 物联网卡号
        *   @param  vehiclepassword 车辆登录密码
        *   @param  supertranstype 上级平台的营运类型代码    (必填)	  字典维护
        *   @param  seatingcapacity 吨（座）位
        *   @param  transserialno 道路运输证号
        *   @param  routename 营运线路
        *   @param  startservicestartdate 服务开始时间开始时间
        *   @param  endservicestartdate 服务开始时间结束时间
        *   @param  startserviceenddate 服务结束时间开始时间
        *   @param  endserviceenddate 服务结束时间结束时间
        *   @param  startupdatedate 更新时间开始时间
        *   @param  endupdatedate 更新时间结束时间
        *   @param  updatestaff 更新人
        *   @param  createstaff 创建人
        *   @param  vehiclemanufacturer 车辆厂商
        *   @param  startinlinedate 开始时间
        *   @param  endinlinedate 结束时间
        *   @param  startsynchronizeddate 同步时间开始时间
        *   @param  endsynchronizeddate 同步时间结束时间
        *   @param  operatorid 运营商id
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
        *
        */
        @RequestParam(required = false) Long  vehicleid,
        /**
        *
        */
        @RequestParam(required = false) Date  startcreatedate,
        @RequestParam(required = false) Date  endcreatedate,
        /**
        *
        */
        @RequestParam(required = false) Long  deleted,
        /**
        *
        */
        @RequestParam(required = false) String  owner,
        /**
        *
        */
        @RequestParam(required = false) String  remark,
        /**
        *
        */
        @RequestParam(required = false) Date  startbuytime,
        @RequestParam(required = false) Date  endbuytime,
        /**
        *机构id
        */
        @RequestParam(required = false) Long  depid,
        /**
        *
        */
        @RequestParam(required = false) String  depname,
        /**
        *
        */
        @RequestParam(required = false) String  driver,
        /**
        *
        */
        @RequestParam(required = false) String  drivermobile,
        /**
        *
        */
        @RequestParam(required = false) String  gpsterminaltype,
        /**
        *运输行业编码
        */
        @RequestParam(required = false) String  industry,
        /**
        *安装时间
        */
        @RequestParam(required = false) Date  startinstalldate,
        @RequestParam(required = false) Date  endinstalldate,
        /**
        *
        */
        @RequestParam(required = false) Date  startlastchecktime,
        @RequestParam(required = false) Date  endlastchecktime,
        /**
        *业户id
        */
        @RequestParam(required = false) Long  memberid,
        /**
        *
        */
        @RequestParam(required = false) String  monitor,
        /**
        *
        */
        @RequestParam(required = false) String  monitormobile,
        /**
        *
        */
        @RequestParam(required = false) String  motorid,
        /**
        *
        */
        @RequestParam(required = false) Date  startofflinetime,
        @RequestParam(required = false) Date  endofflinetime,
        /**
        *
        */
        @RequestParam(required = false) Date  startonlinetime,
        @RequestParam(required = false) Date  endonlinetime,
        /**
        *
        */
        @RequestParam(required = false) String  operpermit,
        /**
        *车辆颜色
        */
        @RequestParam(required = false) Long  platecolor,
        /**
        *车牌号
        */
        @RequestParam(required = false) String  plateno,
        /**
        *车籍地
        */
        @RequestParam(required = false) String  region,
        /**
        *
        */
        @RequestParam(required = false) String  routes,
        /**
        *运行状态
        */
        @RequestParam(required = false) String  runstatus,
        /**
        *
        */
        @RequestParam(required = false) String  simno,
        /**
        *
        */
        @RequestParam(required = false) String  status,
        /**
        *
        */
        @RequestParam(required = false) Long  termid,
        /**
        *
        */
        @RequestParam(required = false) String  usetype,
        /**
        *车辆类型
        */
        @RequestParam(required = false) String  vehicletype,
        /**
        *
        */
        @RequestParam(required = false) String  vendor,
        /**
        *
        */
        @RequestParam(required = false) String  videodeviceid,
        /**
        *
        */
        @RequestParam(required = false) Date  startenddate,
        @RequestParam(required = false) Date  endenddate,
        /**
        *
        */
        @RequestParam(required = false) Date  startstartdate,
        @RequestParam(required = false) Date  endstartdate,
        /**
        *
        */
        @RequestParam(required = false) Date  startbuydate,
        @RequestParam(required = false) Date  endbuydate,
        /**
        *
        */
        @RequestParam(required = false) String  engineno,
        /**
        *
        */
        @RequestParam(required = false) String  frameno,
        /**
        *
        */
        @RequestParam(required = false) String  manufacture,
        /**
        *
        */
        @RequestParam(required = false) String  modelno,
        /**
        *
        */
        @RequestParam(required = false) String  photo,
        /**
        *
        */
        @RequestParam(required = false) Long  companyid,
        /**
        *视频通道数
        */
        @RequestParam(required = false) Long  videochannelnum,
        /**
        *
        */
        @RequestParam(required = false) String  username,
        /**
        *
        */
        @RequestParam(required = false) Long  videodatatype,
        /**
        *
        */
        @RequestParam(required = false) Long  channelid,
        /**
        *
        */
        @RequestParam(required = false) String  videodisk,
        /**
        *视频通道名称
        */
        @RequestParam(required = false) String  videochannelnames,
        /**
        *物联网卡号
        */
        @RequestParam(required = false) String  flowrateno,
        /**
        *车辆登录密码
        */
        @RequestParam(required = false) String  vehiclepassword,
        /**
        *上级平台的营运类型代码    (必填)	  字典维护
        */
        @RequestParam(required = false) String  supertranstype,
        /**
        *吨（座）位
        */
        @RequestParam(required = false) String  seatingcapacity,
        /**
        *道路运输证号
        */
        @RequestParam(required = false) String  transserialno,
        /**
        *营运线路
        */
        @RequestParam(required = false) String  routename,
        /**
        *服务开始时间
        */
        @RequestParam(required = false) Date  startservicestartdate,
        @RequestParam(required = false) Date  endservicestartdate,
        /**
        *服务结束时间
        */
        @RequestParam(required = false) Date  startserviceenddate,
        @RequestParam(required = false) Date  endserviceenddate,
        /**
        *更新时间
        */
        @RequestParam(required = false) Date  startupdatedate,
        @RequestParam(required = false) Date  endupdatedate,
        /**
        *更新人
        */
        @RequestParam(required = false) Long  updatestaff,
        /**
        *创建人
        */
        @RequestParam(required = false) Long  createstaff,
        /**
        *车辆厂商
        */
        @RequestParam(required = false) String  vehiclemanufacturer,
        /**
        *
        */
        @RequestParam(required = false) Date  startinlinedate,
        @RequestParam(required = false) Date  endinlinedate,
        /**
        *同步时间
        */
        @RequestParam(required = false) Date  startsynchronizeddate,
        @RequestParam(required = false) Date  endsynchronizeddate,
        /**
        *运营商id
        */
        @RequestParam(required = false) Long  operatorid,
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
return vehicleservice.selectlist(params, page, rows);
} catch (Exception e) {
log.error("车辆表查询列表失败", e);
return paginateResult;
}
}


/**
* 导出列表文件
        *   @param  vehicleid 
        *   @param  startcreatedate 开始时间
        *   @param  endcreatedate 结束时间
        *   @param  deleted 
        *   @param  owner 
        *   @param  remark 
        *   @param  startbuytime 开始时间
        *   @param  endbuytime 结束时间
        *   @param  depid 机构id
        *   @param  depname 
        *   @param  driver 
        *   @param  drivermobile 
        *   @param  gpsterminaltype 
        *   @param  industry 运输行业编码
        *   @param  startinstalldate 安装时间开始时间
        *   @param  endinstalldate 安装时间结束时间
        *   @param  startlastchecktime 开始时间
        *   @param  endlastchecktime 结束时间
        *   @param  memberid 业户id
        *   @param  monitor 
        *   @param  monitormobile 
        *   @param  motorid 
        *   @param  startofflinetime 开始时间
        *   @param  endofflinetime 结束时间
        *   @param  startonlinetime 开始时间
        *   @param  endonlinetime 结束时间
        *   @param  operpermit 
        *   @param  platecolor 车辆颜色
        *   @param  plateno 车牌号
        *   @param  region 车籍地
        *   @param  routes 
        *   @param  runstatus 运行状态
        *   @param  simno 
        *   @param  status 
        *   @param  termid 
        *   @param  usetype 
        *   @param  vehicletype 车辆类型
        *   @param  vendor 
        *   @param  videodeviceid 
        *   @param  startenddate 开始时间
        *   @param  endenddate 结束时间
        *   @param  startstartdate 开始时间
        *   @param  endstartdate 结束时间
        *   @param  startbuydate 开始时间
        *   @param  endbuydate 结束时间
        *   @param  engineno 
        *   @param  frameno 
        *   @param  manufacture 
        *   @param  modelno 
        *   @param  photo 
        *   @param  companyid 
        *   @param  videochannelnum 视频通道数
        *   @param  username 
        *   @param  videodatatype 
        *   @param  channelid 
        *   @param  videodisk 
        *   @param  videochannelnames 视频通道名称
        *   @param  flowrateno 物联网卡号
        *   @param  vehiclepassword 车辆登录密码
        *   @param  supertranstype 上级平台的营运类型代码    (必填)	  字典维护
        *   @param  seatingcapacity 吨（座）位
        *   @param  transserialno 道路运输证号
        *   @param  routename 营运线路
        *   @param  startservicestartdate 服务开始时间开始时间
        *   @param  endservicestartdate 服务开始时间结束时间
        *   @param  startserviceenddate 服务结束时间开始时间
        *   @param  endserviceenddate 服务结束时间结束时间
        *   @param  startupdatedate 更新时间开始时间
        *   @param  endupdatedate 更新时间结束时间
        *   @param  updatestaff 更新人
        *   @param  createstaff 创建人
        *   @param  vehiclemanufacturer 车辆厂商
        *   @param  startinlinedate 开始时间
        *   @param  endinlinedate 结束时间
        *   @param  startsynchronizeddate 同步时间开始时间
        *   @param  endsynchronizeddate 同步时间结束时间
        *   @param  operatorid 运营商id
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
        *
        */
        @RequestParam(value = "vehicleid",required = false) Long  vehicleid,
        /**
        *
        */
        @RequestParam(value = "createdate",required = false) Date  startcreatedate,
        @RequestParam(value = "createdate",required = false) Date  endcreatedate,
        /**
        *
        */
        @RequestParam(value = "deleted",required = false) Long  deleted,
        /**
        *
        */
        @RequestParam(value = "owner",required = false) String  owner,
        /**
        *
        */
        @RequestParam(value = "remark",required = false) String  remark,
        /**
        *
        */
        @RequestParam(value = "buytime",required = false) Date  startbuytime,
        @RequestParam(value = "buytime",required = false) Date  endbuytime,
        /**
        *机构id
        */
        @RequestParam(value = "depid",required = false) Long  depid,
        /**
        *
        */
        @RequestParam(value = "depname",required = false) String  depname,
        /**
        *
        */
        @RequestParam(value = "driver",required = false) String  driver,
        /**
        *
        */
        @RequestParam(value = "drivermobile",required = false) String  drivermobile,
        /**
        *
        */
        @RequestParam(value = "gpsterminaltype",required = false) String  gpsterminaltype,
        /**
        *运输行业编码
        */
        @RequestParam(value = "industry",required = false) String  industry,
        /**
        *安装时间
        */
        @RequestParam(value = "installdate",required = false) Date  startinstalldate,
        @RequestParam(value = "installdate",required = false) Date  endinstalldate,
        /**
        *
        */
        @RequestParam(value = "lastchecktime",required = false) Date  startlastchecktime,
        @RequestParam(value = "lastchecktime",required = false) Date  endlastchecktime,
        /**
        *业户id
        */
        @RequestParam(value = "memberid",required = false) Long  memberid,
        /**
        *
        */
        @RequestParam(value = "monitor",required = false) String  monitor,
        /**
        *
        */
        @RequestParam(value = "monitormobile",required = false) String  monitormobile,
        /**
        *
        */
        @RequestParam(value = "motorid",required = false) String  motorid,
        /**
        *
        */
        @RequestParam(value = "offlinetime",required = false) Date  startofflinetime,
        @RequestParam(value = "offlinetime",required = false) Date  endofflinetime,
        /**
        *
        */
        @RequestParam(value = "onlinetime",required = false) Date  startonlinetime,
        @RequestParam(value = "onlinetime",required = false) Date  endonlinetime,
        /**
        *
        */
        @RequestParam(value = "operpermit",required = false) String  operpermit,
        /**
        *车辆颜色
        */
        @RequestParam(value = "platecolor",required = false) Long  platecolor,
        /**
        *车牌号
        */
        @RequestParam(value = "plateno",required = false) String  plateno,
        /**
        *车籍地
        */
        @RequestParam(value = "region",required = false) String  region,
        /**
        *
        */
        @RequestParam(value = "routes",required = false) String  routes,
        /**
        *运行状态
        */
        @RequestParam(value = "runstatus",required = false) String  runstatus,
        /**
        *
        */
        @RequestParam(value = "simno",required = false) String  simno,
        /**
        *
        */
        @RequestParam(value = "status",required = false) String  status,
        /**
        *
        */
        @RequestParam(value = "termid",required = false) Long  termid,
        /**
        *
        */
        @RequestParam(value = "usetype",required = false) String  usetype,
        /**
        *车辆类型
        */
        @RequestParam(value = "vehicletype",required = false) String  vehicletype,
        /**
        *
        */
        @RequestParam(value = "vendor",required = false) String  vendor,
        /**
        *
        */
        @RequestParam(value = "videodeviceid",required = false) String  videodeviceid,
        /**
        *
        */
        @RequestParam(value = "enddate",required = false) Date  startenddate,
        @RequestParam(value = "enddate",required = false) Date  endenddate,
        /**
        *
        */
        @RequestParam(value = "startdate",required = false) Date  startstartdate,
        @RequestParam(value = "startdate",required = false) Date  endstartdate,
        /**
        *
        */
        @RequestParam(value = "buydate",required = false) Date  startbuydate,
        @RequestParam(value = "buydate",required = false) Date  endbuydate,
        /**
        *
        */
        @RequestParam(value = "engineno",required = false) String  engineno,
        /**
        *
        */
        @RequestParam(value = "frameno",required = false) String  frameno,
        /**
        *
        */
        @RequestParam(value = "manufacture",required = false) String  manufacture,
        /**
        *
        */
        @RequestParam(value = "modelno",required = false) String  modelno,
        /**
        *
        */
        @RequestParam(value = "photo",required = false) String  photo,
        /**
        *
        */
        @RequestParam(value = "companyid",required = false) Long  companyid,
        /**
        *视频通道数
        */
        @RequestParam(value = "videochannelnum",required = false) Long  videochannelnum,
        /**
        *
        */
        @RequestParam(value = "username",required = false) String  username,
        /**
        *
        */
        @RequestParam(value = "videodatatype",required = false) Long  videodatatype,
        /**
        *
        */
        @RequestParam(value = "channelid",required = false) Long  channelid,
        /**
        *
        */
        @RequestParam(value = "videodisk",required = false) String  videodisk,
        /**
        *视频通道名称
        */
        @RequestParam(value = "videochannelnames",required = false) String  videochannelnames,
        /**
        *物联网卡号
        */
        @RequestParam(value = "flowrateno",required = false) String  flowrateno,
        /**
        *车辆登录密码
        */
        @RequestParam(value = "vehiclepassword",required = false) String  vehiclepassword,
        /**
        *上级平台的营运类型代码    (必填)	  字典维护
        */
        @RequestParam(value = "supertranstype",required = false) String  supertranstype,
        /**
        *吨（座）位
        */
        @RequestParam(value = "seatingcapacity",required = false) String  seatingcapacity,
        /**
        *道路运输证号
        */
        @RequestParam(value = "transserialno",required = false) String  transserialno,
        /**
        *营运线路
        */
        @RequestParam(value = "routename",required = false) String  routename,
        /**
        *服务开始时间
        */
        @RequestParam(value = "servicestartdate",required = false) Date  startservicestartdate,
        @RequestParam(value = "servicestartdate",required = false) Date  endservicestartdate,
        /**
        *服务结束时间
        */
        @RequestParam(value = "serviceenddate",required = false) Date  startserviceenddate,
        @RequestParam(value = "serviceenddate",required = false) Date  endserviceenddate,
        /**
        *更新时间
        */
        @RequestParam(value = "updatedate",required = false) Date  startupdatedate,
        @RequestParam(value = "updatedate",required = false) Date  endupdatedate,
        /**
        *更新人
        */
        @RequestParam(value = "updatestaff",required = false) Long  updatestaff,
        /**
        *创建人
        */
        @RequestParam(value = "createstaff",required = false) Long  createstaff,
        /**
        *车辆厂商
        */
        @RequestParam(value = "vehiclemanufacturer",required = false) String  vehiclemanufacturer,
        /**
        *
        */
        @RequestParam(value = "inlinedate",required = false) Date  startinlinedate,
        @RequestParam(value = "inlinedate",required = false) Date  endinlinedate,
        /**
        *同步时间
        */
        @RequestParam(value = "synchronizeddate",required = false) Date  startsynchronizeddate,
        @RequestParam(value = "synchronizeddate",required = false) Date  endsynchronizeddate,
        /**
        *运营商id
        */
        @RequestParam(value = "operatorid",required = false) Long  operatorid,
HttpServletRequest request,
HttpServletResponse response
) {
try {
Map params = getParams(request);
Uservehicleauthority uservehicleauthority=getUservehicleauthorityByWeb(depIds);
quanxianUtil.getquanxian(uservehicleauthority, params);
PaginateResult selectmemberlist =vehicleservice.selectlist(params, 0, 0);
List lists = selectmemberlist.getRows();
List exldate = new ArrayList<>();
for (int i = 0; i < lists.size(); i++) {
Object row=lists.get(i);
VehicleExlVo vehicle = MaptoBeanUtil.mapToBean((Map
<String, Object>) row, VehicleExlVo.class);
vehicle.setId(i+1);
exldate.add(vehicle);
}
exceClasslUtil.exlport("车辆表", request, response, exldate, VehicleExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
} catch (Exception e) {
log.error("车辆表导出列表失败", e);
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