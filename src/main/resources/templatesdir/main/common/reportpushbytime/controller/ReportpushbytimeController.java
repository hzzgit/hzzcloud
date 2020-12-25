package common.reportpushbytime.controller;

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
* 金融报表生成，用于控制每个用户每个月什么时间段生成指定车辆的数据接口层
*/
@Controller
@RequestMapping("/reportpushbytime")
@Slf4j
public class ReportpushbytimeController  extends GenericAction{

@Autowired
private ReportpushbytimeService reportpushbytimeservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;


/**
* 保存金融报表生成，用于控制每个用户每个月什么时间段生成指定车辆的数据 ,使用json请求，表单数据是解释含义，不需要传
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Reportpushbytime reportpushbytime, HttpServletRequest request
)
throws Exception {
reportpushbytimeservice.save(reportpushbytime);
return new JsonMessage(true, "操作成功");
}


/**
* 删除金融报表生成，用于控制每个用户每个月什么时间段生成指定车辆的数据
* @param id 主键
* @return
*/
@ResponseBody
@RequestMapping("/delete.action")
public JsonMessage delete(
String id) throws Exception {
reportpushbytimeservice.fdelete(id);
return new JsonMessage(true, "操作成功");
}

/**
* 查询列表
        *   @param  id 主键
        *   @param  username 中文名
        *   @param  userid 英文名
        *   @param  starttime 开始时间，yyyy-mm
        *   @param  endtime 结束时间,yyyy-mm
        *   @param  startcreatedate 创建时间开始时间
        *   @param  endcreatedate 创建时间结束时间
        *   @param  deleted 删除标志
        *   @param  loginname 登录名
        *   @param  vehicleid 车辆主键
        *   @param  plateno 车牌号
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
        *中文名
        */
        @RequestParam(required = false) String  username,
        /**
        *英文名
        */
        @RequestParam(required = false) Long  userid,
        /**
        *开始时间，yyyy-mm
        */
        @RequestParam(required = false) String  starttime,
        /**
        *结束时间,yyyy-mm
        */
        @RequestParam(required = false) String  endtime,
        /**
        *创建时间
        */
        @RequestParam(required = false) Date  startcreatedate,
        @RequestParam(required = false) Date  endcreatedate,
        /**
        *删除标志
        */
        @RequestParam(required = false) Long  deleted,
        /**
        *登录名
        */
        @RequestParam(required = false) String  loginname,
        /**
        *车辆主键
        */
        @RequestParam(required = false) Long  vehicleid,
        /**
        *车牌号
        */
        @RequestParam(required = false) String  plateno,
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
return reportpushbytimeservice.selectlist(params, page, rows);
} catch (Exception e) {
log.error("金融报表生成，用于控制每个用户每个月什么时间段生成指定车辆的数据查询列表失败", e);
return paginateResult;
}
}


/**
* 导出列表文件
        *   @param  id 主键
        *   @param  username 中文名
        *   @param  userid 英文名
        *   @param  starttime 开始时间，yyyy-mm
        *   @param  endtime 结束时间,yyyy-mm
        *   @param  startcreatedate 创建时间开始时间
        *   @param  endcreatedate 创建时间结束时间
        *   @param  deleted 删除标志
        *   @param  loginname 登录名
        *   @param  vehicleid 车辆主键
        *   @param  plateno 车牌号
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
        *中文名
        */
        @RequestParam(value = "username",required = false) String  username,
        /**
        *英文名
        */
        @RequestParam(value = "userid",required = false) Long  userid,
        /**
        *开始时间，yyyy-mm
        */
        @RequestParam(value = "starttime",required = false) String  starttime,
        /**
        *结束时间,yyyy-mm
        */
        @RequestParam(value = "endtime",required = false) String  endtime,
        /**
        *创建时间
        */
        @RequestParam(value = "createdate",required = false) Date  startcreatedate,
        @RequestParam(value = "createdate",required = false) Date  endcreatedate,
        /**
        *删除标志
        */
        @RequestParam(value = "deleted",required = false) Long  deleted,
        /**
        *登录名
        */
        @RequestParam(value = "loginname",required = false) String  loginname,
        /**
        *车辆主键
        */
        @RequestParam(value = "vehicleid",required = false) Long  vehicleid,
        /**
        *车牌号
        */
        @RequestParam(value = "plateno",required = false) String  plateno,
HttpServletRequest request,
HttpServletResponse response
) {
try {
Map params = getParams(request);
Uservehicleauthority uservehicleauthority=getUservehicleauthorityByWeb(depIds);
quanxianUtil.getquanxian(uservehicleauthority, params);
PaginateResult selectmemberlist =reportpushbytimeservice.selectlist(params, 0, 0);
List lists = selectmemberlist.getRows();
List exldate = new ArrayList<>();
for (int i = 0; i < lists.size(); i++) {
Object row=lists.get(i);
ReportpushbytimeExlVo reportpushbytime = MaptoBeanUtil.mapToBean((Map
<String, Object>) row, ReportpushbytimeExlVo.class);
reportpushbytime.setId(i+1);
exldate.add(reportpushbytime);
}
exceClasslUtil.exlport("金融报表生成，用于控制每个用户每个月什么时间段生成指定车辆的数据", request, response, exldate, ReportpushbytimeExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
} catch (Exception e) {
log.error("金融报表生成，用于控制每个用户每个月什么时间段生成指定车辆的数据导出列表失败", e);
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