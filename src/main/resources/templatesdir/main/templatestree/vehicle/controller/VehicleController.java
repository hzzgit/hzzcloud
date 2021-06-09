package templatestree.vehicle.controller;

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
* 接口层
*/
@Controller
@RequestMapping("/vehicle")
@Slf4j
public class VehicleController  extends GenericAction{


@Autowired
private VehicleService vehicleservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;
@Autowired
private JdbcUtil jdbcUtil;

/**
* 保存 ,使用json请求
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Vehicle vehicle, HttpServletRequest request
)
throws Exception {
OnlineUser onlineUser = getOnlineUser();
vehicleservice.save(vehicle,  onlineUser.getEntityId());
return new JsonMessage(true, "操作成功");
}


/**
* 删除
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
        *   @param  depid 
        *   @param  depname 
        *   @param  driver 
        *   @param  drivermobile 
        *   @param  gpsterminaltype 
        *   @param  industry 
        *   @param  startinstalldate 开始时间
        *   @param  endinstalldate 结束时间
        *   @param  startlastchecktime 开始时间
        *   @param  endlastchecktime 结束时间
        *   @param  memberid 
        *   @param  monitor 
        *   @param  monitormobile 
        *   @param  motorid 
        *   @param  startofflinetime 开始时间
        *   @param  endofflinetime 结束时间
        *   @param  startonlinetime 开始时间
        *   @param  endonlinetime 结束时间
        *   @param  operpermit 
        *   @param  platecolor 
        *   @param  plateno 
        *   @param  region 
        *   @param  routes 
        *   @param  runstatus 
        *   @param  simno 
        *   @param  status 
        *   @param  termid 
        *   @param  usetype 
        *   @param  vehicletype 
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
        *   @param  videochannelnum 
        *   @param  username 
        *   @param  videodatatype 
        *   @param  channelid 
        *   @param  videodisk 
        *   @param  videochannelnames 
        *   @param  flowrateno 
        *   @param  vehiclepassword 
        *   @param  supertranstype 上级平台的营运类型代码    (必填)	  字典维护
        *   @param  seatingcapacity 吨（座）位
        *   @param  transserialno 道路运输证号
        *   @param  routename 营运线路
        *   @param  startservicestartdate 服务开始时间开始时间
        *   @param  endservicestartdate 服务开始时间结束时间
        *   @param  startserviceenddate 服务结束时间开始时间
        *   @param  endserviceenddate 服务结束时间结束时间
        *   @param  startinlinedate 入网时间开始时间
        *   @param  endinlinedate 入网时间结束时间
        *   @param  startupdatedate 更新时间开始时间
        *   @param  endupdatedate 更新时间结束时间
        *   @param  updatestaff 更新人
        *   @param  createstaff 创建人
        *   @param  vehiclemanufacturer 车辆厂商
        *   @param  operatorid 运营商id
        *   @param  protocol_version 协议版本
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
            *
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
            *
            */
            @RequestParam(required = false) String  industry,
            /**
            *
            */
            @RequestParam(required = false) Date  startinstalldate,
            @RequestParam(required = false) Date  endinstalldate,
            /**
            *
            */
            @RequestParam(required = false) Date  startlastchecktime,
            @RequestParam(required = false) Date  endlastchecktime,
            /**
            *
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
            *
            */
            @RequestParam(required = false) Long  platecolor,
            /**
            *
            */
            @RequestParam(required = false) String  plateno,
            /**
            *
            */
            @RequestParam(required = false) String  region,
            /**
            *
            */
            @RequestParam(required = false) String  routes,
            /**
            *
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
            *
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
            *
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
            *
            */
            @RequestParam(required = false) String  videochannelnames,
            /**
            *
            */
            @RequestParam(required = false) String  flowrateno,
            /**
            *
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
            *入网时间
            */
            @RequestParam(required = false) Date  startinlinedate,
            @RequestParam(required = false) Date  endinlinedate,
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
            *运营商id
            */
            @RequestParam(required = false) Long  operatorid,
            /**
            *协议版本
            */
            @RequestParam(required = false) String  protocol_version,
HttpServletRequest request
) {
PaginateResult paginateResult = new PaginateResult(500, "服务器错误");
try {
Map params = getParams(request);
OnlineUser onlineUser = getOnlineUser();
if(!onlineUser.isSuperAdmin()){
params.put("owner", onlineUser.getEntityId());
params.put("userid", onlineUser.getEntityId());
}
Uservehicleauthority uservehicleauthority=getUservehicleauthorityByWeb(depIds);
quanxianUtil.getquanxian(uservehicleauthority, params);
return vehicleservice.selectlist(params, page, rows);
} catch (Exception e) {
log.error("查询列表失败", e);
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
        *   @param  depid 
        *   @param  depname 
        *   @param  driver 
        *   @param  drivermobile 
        *   @param  gpsterminaltype 
        *   @param  industry 
        *   @param  startinstalldate 开始时间
        *   @param  endinstalldate 结束时间
        *   @param  startlastchecktime 开始时间
        *   @param  endlastchecktime 结束时间
        *   @param  memberid 
        *   @param  monitor 
        *   @param  monitormobile 
        *   @param  motorid 
        *   @param  startofflinetime 开始时间
        *   @param  endofflinetime 结束时间
        *   @param  startonlinetime 开始时间
        *   @param  endonlinetime 结束时间
        *   @param  operpermit 
        *   @param  platecolor 
        *   @param  plateno 
        *   @param  region 
        *   @param  routes 
        *   @param  runstatus 
        *   @param  simno 
        *   @param  status 
        *   @param  termid 
        *   @param  usetype 
        *   @param  vehicletype 
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
        *   @param  videochannelnum 
        *   @param  username 
        *   @param  videodatatype 
        *   @param  channelid 
        *   @param  videodisk 
        *   @param  videochannelnames 
        *   @param  flowrateno 
        *   @param  vehiclepassword 
        *   @param  supertranstype 上级平台的营运类型代码    (必填)	  字典维护
        *   @param  seatingcapacity 吨（座）位
        *   @param  transserialno 道路运输证号
        *   @param  routename 营运线路
        *   @param  startservicestartdate 服务开始时间开始时间
        *   @param  endservicestartdate 服务开始时间结束时间
        *   @param  startserviceenddate 服务结束时间开始时间
        *   @param  endserviceenddate 服务结束时间结束时间
        *   @param  startinlinedate 入网时间开始时间
        *   @param  endinlinedate 入网时间结束时间
        *   @param  startupdatedate 更新时间开始时间
        *   @param  endupdatedate 更新时间结束时间
        *   @param  updatestaff 更新人
        *   @param  createstaff 创建人
        *   @param  vehiclemanufacturer 车辆厂商
        *   @param  operatorid 运营商id
        *   @param  protocol_version 协议版本
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
        *
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
        *
        */
        @RequestParam(value = "industry",required = false) String  industry,
        /**
        *
        */
        @RequestParam(value = "installdate",required = false) Date  startinstalldate,
        @RequestParam(value = "installdate",required = false) Date  endinstalldate,
        /**
        *
        */
        @RequestParam(value = "lastchecktime",required = false) Date  startlastchecktime,
        @RequestParam(value = "lastchecktime",required = false) Date  endlastchecktime,
        /**
        *
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
        *
        */
        @RequestParam(value = "platecolor",required = false) Long  platecolor,
        /**
        *
        */
        @RequestParam(value = "plateno",required = false) String  plateno,
        /**
        *
        */
        @RequestParam(value = "region",required = false) String  region,
        /**
        *
        */
        @RequestParam(value = "routes",required = false) String  routes,
        /**
        *
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
        *
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
        *
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
        *
        */
        @RequestParam(value = "videochannelnames",required = false) String  videochannelnames,
        /**
        *
        */
        @RequestParam(value = "flowrateno",required = false) String  flowrateno,
        /**
        *
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
        *入网时间
        */
        @RequestParam(value = "inlinedate",required = false) Date  startinlinedate,
        @RequestParam(value = "inlinedate",required = false) Date  endinlinedate,
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
        *运营商id
        */
        @RequestParam(value = "operatorid",required = false) Long  operatorid,
        /**
        *协议版本
        */
        @RequestParam(value = "protocol_version",required = false) String  protocol_version,
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
exceClasslUtil.exlport("", request, response, exldate, VehicleExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
} catch (Exception e) {
log.error("导出列表失败", e);
}
}


/**
* 启用/禁用配置
*
* @param id    主键,vehicleid
* @param isuse 1启用，0禁用
* @return
*/
@ResponseBody
@RequestMapping("/updateisuse.action")
public JsonMessage updateIsuse(
Integer id, int isuse
) {
try {
String sql = "update subiaodb.vehicle set isuse=?  where vehicleid=?";
jdbcUtil.sql(sql).addIndexParam(isuse, id).executeUpdate();
} catch (Exception e) {
log.error("修改配置启用状态失败", e);
return json(false, "修改失败");
}
return json(true, "修改成功");

}


/**
* 查询详情
*
* @param id 主键 vehicleid
* @return
*/
@ResponseBody
@RequestMapping("/searchdetail.action")
public JsonMessage searchDetail(
Integer id
) {
Map alldata = null;
try {
String sql = "select * from subiaodb.vehicle where vehicleid=?";
Vehicle vehicle = jdbcUtil.sql(sql).addIndexParam(id).queryFirst(Vehicle.class);
alldata = new HashMap();
alldata.put("vehicle", vehicle);
} catch (Exception e) {
log.error("查询详情失败", e);
return json(true, "查询详情失败");
}
return json(true,1, alldata);
}



/**
* 保存配置的机构授权表
*
* @param addDepIds    要新增的机构主键id,用逗号隔开
* @param deleteDepIds 要删除的机构主键Id,用逗号隔开
* @param id           配置表主键,也就是 vehicleid
* @return
*/
@ResponseBody
@RequestMapping("/savedep.action")
public JsonMessage saveDep(@RequestParam(value = "adddepids", defaultValue = "") String addDepIds,
@RequestParam(value = "deletedepids", defaultValue = "") String deleteDepIds,
Long id) {

String[] adddepIds = null;
String[] deletedepIds = null;
OnlineUser onlineUser = getOnlineUser();
try {
log.error("警告!用户进行机构配置修改操作,新增的机构有" + addDepIds + ",删除的机构有" + deleteDepIds + ",当前操作用户名为" + onlineUser.getName() + ",用户id为" +
onlineUser.getEntityId() + ",操作的配置表主键= " + id);
if (!StringUtil.isNullOrEmpty(addDepIds)) {
adddepIds = addDepIds.split(",");
}
if (!StringUtil.isNullOrEmpty(deleteDepIds)) {
deletedepIds = deleteDepIds.split(",");
}
vehicleservice.saveByDep(deletedepIds, adddepIds, id);
} catch (Exception ex) {
log.error("机构配置报错,组织机构Ids" + addDepIds + "配置id" + id + "错误信息" + ex.getMessage(), ex);
return new JsonMessage(false, "保存失败");
}
return new JsonMessage(true, "保存成功");
}




/**
* 查看该配置的机构授权数据（树），vehicleid
*
* @param id
* @return
*/
@ResponseBody
@RequestMapping("/searchdep.action")
public JsonMessage searchDepById(Integer id) {
if (id > 0) {
Uservehicleauthority uservehicleauthority = getUservehicleauthorityByWeb(0);
List<Long> depList1 = new ArrayList<>();
    if (getOnlineUser().isSuperAdmin()) {
    depList1 = getOnlineUser().getDepIdList();
    } else {
    depList1 = uservehicleauthority.getDepIdList();
    }
    List<Department> depList = getAuthorizedDepList(depList1);

        Map deptreemap = vehicleservice.depNodeTree(id, depList);
        return json(true, deptreemap);
        } else {
        return json(false, "查询id不能为空");
        }
        }



        /**
        * 保存配置的用户授权表
        *
        * @param id 主键  vehicleid
        * @param deleteuserids ,要删除的用户id,userId,用逗号隔开
        * @param adduserids  ,要添加的用户id,userId,用逗号隔开
        * @return
        */
        @ResponseBody
        @RequestMapping("/saveconfigbyuser.action")
        public JsonMessage saveconfigbyuser(Long id, String deleteuserids, String adduserids) {

        String[] deleteuserIdsArr = null;
        String[] adduserIdsArr = null;
        if (!StringUtils.isBlank(deleteuserids)) {
        deleteuserIdsArr = deleteuserids.split(",");
        }
        if (!StringUtils.isBlank(adduserids)) {
        adduserIdsArr = adduserids.split(",");
        }
        try {
        vehicleservice.saveConfigByUser(id, deleteuserIdsArr, adduserIdsArr);
        return json(true, "保存成功");
        } catch (Exception e) {
        log.error("配置绑定用户出错", e);
        return json(false, "保存失败");
        }

        }

        /**
        * 查看该配置的用户授权数据
        *
        * @param id
        * @return
        */
        @ResponseBody
        @RequestMapping("/searchalluser.action")
        public JsonMessage searchAllUserById(Integer id) {
        boolean isadmin = getOnlineUser().isSuperAdmin();
        String sql = null;
        List<RowDataMap> alldata = new ArrayList<RowDataMap>();
                if (isadmin) {
                sql = "select userid,name  from userinfo where deleted =0 and usertype !='admin' ";
                alldata = jdbcUtil.sql(sql).queryWithMap();
                } else {
                //非超级管理员可选择该用户创建的用户
                sql = "select userid,name from userinfo where createStaff =? and deleted = 0";
                alldata = jdbcUtil.sql(sql).addIndexParam(getOnlineUser().getEntityId()).queryWithMap();
                }
                String resql = "select userid from vehicleuserlim where mainid=?";
                List<Integer> userlims = jdbcUtil.sql(resql).addIndexParam(id).queryFirstColInt();
                    Map userlimMap = new HashMap();
                    if (ConverterUtils.isList(userlims)) {
                    for (Integer userlim : userlims) {
                    userlimMap.put(userlim, "");
                    }
                    }
                    List<Integer> checkid = new ArrayList<>();
                        for (RowDataMap alldatum : alldata) {
                        int userid = alldatum.getIntegerValue("userid");
                        if (userlimMap.containsKey(userid)) {
                        checkid.add(userid);
                        }
                        }
                        Map data = new HashMap();
                        data.put("data", alldata);
                        data.put("checkid", checkid);
                        return json(true, data);

                        }


                        /**
                        * 保存配置的车辆授权
                        *
                        * @param id               要保存的区域围栏主键 ,就是areaId
                        * @param deletevehicleIds 要删除的车辆主键集合，用逗号隔开
                        * @param addvehicleIds    要添加的车辆主键集合，用逗号隔开
                        * @return
                        */
                        @ResponseBody
                        @RequestMapping("/savevehicle.action")
                        public JsonMessage saveVehicle(Long id,
                        @RequestParam(value = "deletevehicleids", defaultValue = "") String deletevehicleIds,
                        @RequestParam(value = "addvehicleids", defaultValue = "") String addvehicleIds) {
                        String[] deletevehicleId = null;
                        String[] addvehicleId = null;
                        OnlineUser onlineUser = getOnlineUser();
                        log.debug("用户进行车辆权限修改操作,新增的车辆有" + addvehicleIds + ",删除的车辆有" + deletevehicleIds + ",当前操作用户名为" + onlineUser.getName() + ",用户id为+" +
                        onlineUser.getEntityId() + ",操作的配置表主键= " + id);
                        if (!com.ltmonitor.util.StringUtil.isNullOrEmpty(deletevehicleIds)) {
                        deletevehicleId = deletevehicleIds.split(",");
                        }
                        if (!com.ltmonitor.util.StringUtil.isNullOrEmpty(addvehicleIds)) {
                        addvehicleId = addvehicleIds.split(",");
                        }
                        if (!vehicleservice.saveByVehicle(addvehicleId, deletevehicleId, id)) {
                        return json(true, "保存失败");
                        }
                        return json(true, "保存成功");
                        }


                        /**
                        * 查看该配置的车辆授权数据（树）
                        *
                        * @param id 要查询的配置表主键，vehicleid
                        * @return
                        */
                        @ResponseBody
                        @RequestMapping("/searchvechcle.action")
                        public JsonMessage searchVechcleById(Integer id) {
                        Uservehicleauthority uservehicleauthority = getUservehicleauthorityByWeb(0);
                        if (getOnlineUser().isSuperAdmin()) {
                        uservehicleauthority.setDepIdList(getOnlineUser().getDepIdList());
                        }
                        Map deptreemap = vehicleservice.depandceNodeTree(id, uservehicleauthority);
                        return json(true, deptreemap);
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