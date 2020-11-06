package com.hzz.hzzcloud.freemarker.main.datafillingvehicle.controller;

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
* 填充的数据的车辆主键接口层
*/
@Controller
@RequestMapping("/datafillingvehicle")
@Slf4j
public class DatafillingvehicleController  extends GenericAction{

@Autowired
private DatafillingvehicleService datafillingvehicleservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;


/**
* 保存填充的数据的车辆主键 ,使用json请求，表单数据是解释含义，不需要传
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Datafillingvehicle datafillingvehicle, HttpServletRequest request
)
throws Exception {
datafillingvehicleservice.save(datafillingvehicle);
return new JsonMessage(true, "操作成功");
}


/**
* 删除填充的数据的车辆主键
* @param id 主键
* @return
*/
@ResponseBody
@RequestMapping("/delete.action")
public JsonMessage delete(
String id) throws Exception {
datafillingvehicleservice.fdelete(id);
return new JsonMessage(true, "操作成功");
}

/**
* 查询列表
        *   @param  id 
        *   @param  startcreatedate 开始时间
        *   @param  endcreatedate 结束时间
        *   @param  vehicleid 
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
        @RequestParam(required = false) Long  id,
        /**
        *
        */
        @RequestParam(required = false) Date  startcreatedate,
        @RequestParam(required = false) Date  endcreatedate,
        /**
        *
        */
        @RequestParam(required = false) Long  vehicleid,
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
return datafillingvehicleservice.selectlist(params, page, rows);
} catch (Exception e) {
log.error("填充的数据的车辆主键查询列表失败", e);
return paginateResult;
}
}


/**
* 导出列表文件
        *   @param  id 
        *   @param  startcreatedate 开始时间
        *   @param  endcreatedate 结束时间
        *   @param  vehicleid 
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
        @RequestParam(value = "id",required = false) Long  id,
        /**
        *
        */
        @RequestParam(value = "createdate",required = false) Date  startcreatedate,
        @RequestParam(value = "createdate",required = false) Date  endcreatedate,
        /**
        *
        */
        @RequestParam(value = "vehicleid",required = false) Long  vehicleid,
HttpServletRequest request,
HttpServletResponse response
) {
try {
Map params = getParams(request);
Uservehicleauthority uservehicleauthority=getUservehicleauthorityByWeb(depIds);
quanxianUtil.getquanxian(uservehicleauthority, params);
PaginateResult selectmemberlist =datafillingvehicleservice.selectlist(params, 0, 0);
List lists = selectmemberlist.getRows();
List exldate = new ArrayList<>();
for (int i = 0; i < lists.size(); i++) {
Object row=lists.get(i);
DatafillingvehicleExlVo datafillingvehicle = MaptoBeanUtil.mapToBean((Map
<String, Object>) row, DatafillingvehicleExlVo.class);
datafillingvehicle.setId(i+1);
exldate.add(datafillingvehicle);
}
exceClasslUtil.exlport("填充的数据的车辆主键", request, response, exldate, DatafillingvehicleExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
} catch (Exception e) {
log.error("填充的数据的车辆主键导出列表失败", e);
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