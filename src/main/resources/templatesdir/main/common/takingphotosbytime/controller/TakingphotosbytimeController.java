package common.takingphotosbytime.controller;

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
* 定时拍照配置表接口层
*/
@Controller
@RequestMapping("/takingphotosbytime")
@Slf4j
public class TakingphotosbytimeController  extends GenericAction{

@Autowired
private TakingphotosbytimeService takingphotosbytimeservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;


/**
* 保存定时拍照配置表 ,使用json请求，表单数据是解释含义，不需要传
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Takingphotosbytime takingphotosbytime, HttpServletRequest request
)
throws Exception {
takingphotosbytimeservice.save(takingphotosbytime);
return new JsonMessage(true, "操作成功");
}


/**
* 删除定时拍照配置表
* @param id 主键
* @return
*/
@ResponseBody
@RequestMapping("/delete.action")
public JsonMessage delete(
String id) throws Exception {
takingphotosbytimeservice.fdelete(id);
return new JsonMessage(true, "操作成功");
}

/**
* 查询列表
        *   @param  id 主键
        *   @param  name 名称
        *   @param  configinterval 拍照间隔(单位：分)
        *   @param  startcreatedate 创建时间开始时间
        *   @param  endcreatedate 创建时间结束时间
        *   @param  startupdatedate 修改时间开始时间
        *   @param  endupdatedate 修改时间结束时间
        *   @param  starttime 配置生效开始时间，时间格式为00:00:00 
        *   @param  endtime 配置生效结束时间,时间格式为 23:59:59
        *   @param  startvalidstarttime 配置有效期开始时间,都不传则是无限制，只传开始时间就是大于开始时间即可开始时间
        *   @param  endvalidstarttime 配置有效期开始时间,都不传则是无限制，只传开始时间就是大于开始时间即可结束时间
        *   @param  startvalidendtime 配置有效期结束时间,都不传则是无限制，只传结束时间就是小于开始时间即可开始时间
        *   @param  endvalidendtime 配置有效期结束时间,都不传则是无限制，只传结束时间就是小于开始时间即可结束时间
        *   @param  configcondition 触发条件,0、不限制，1、根据车速，2、根据停车时长
        *   @param  channel 拍照的通道,用";"号隔开
        *   @param  userid 用户id
        *   @param  isuse 是否启用,0禁用,1启用
        *   @param  deleted 删除标志,1代表删除,0代表正常
        *   @param  username 用户名
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
        *名称
        */
        @RequestParam(required = false) String  name,
        /**
        *拍照间隔(单位：分)
        */
        @RequestParam(required = false) Long  configinterval,
        /**
        *创建时间
        */
        @RequestParam(required = false) Date  startcreatedate,
        @RequestParam(required = false) Date  endcreatedate,
        /**
        *修改时间
        */
        @RequestParam(required = false) Date  startupdatedate,
        @RequestParam(required = false) Date  endupdatedate,
        /**
        *配置生效开始时间，时间格式为00:00:00 
        */
        @RequestParam(required = false) String  starttime,
        /**
        *配置生效结束时间,时间格式为 23:59:59
        */
        @RequestParam(required = false) String  endtime,
        /**
        *配置有效期开始时间,都不传则是无限制，只传开始时间就是大于开始时间即可
        */
        @RequestParam(required = false) Date  startvalidstarttime,
        @RequestParam(required = false) Date  endvalidstarttime,
        /**
        *配置有效期结束时间,都不传则是无限制，只传结束时间就是小于开始时间即可
        */
        @RequestParam(required = false) Date  startvalidendtime,
        @RequestParam(required = false) Date  endvalidendtime,
        /**
        *触发条件,0、不限制，1、根据车速，2、根据停车时长
        */
        @RequestParam(required = false) Long  configcondition,
        /**
        *拍照的通道,用";"号隔开
        */
        @RequestParam(required = false) String  channel,
        /**
        *用户id
        */
        @RequestParam(required = false) Long  userid,
        /**
        *是否启用,0禁用,1启用
        */
        @RequestParam(required = false) Long  isuse,
        /**
        *删除标志,1代表删除,0代表正常
        */
        @RequestParam(required = false) Long  deleted,
        /**
        *用户名
        */
        @RequestParam(required = false) String  username,
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
return takingphotosbytimeservice.selectlist(params, page, rows);
} catch (Exception e) {
log.error("定时拍照配置表查询列表失败", e);
return paginateResult;
}
}


/**
* 导出列表文件
        *   @param  id 主键
        *   @param  name 名称
        *   @param  configinterval 拍照间隔(单位：分)
        *   @param  startcreatedate 创建时间开始时间
        *   @param  endcreatedate 创建时间结束时间
        *   @param  startupdatedate 修改时间开始时间
        *   @param  endupdatedate 修改时间结束时间
        *   @param  starttime 配置生效开始时间，时间格式为00:00:00 
        *   @param  endtime 配置生效结束时间,时间格式为 23:59:59
        *   @param  startvalidstarttime 配置有效期开始时间,都不传则是无限制，只传开始时间就是大于开始时间即可开始时间
        *   @param  endvalidstarttime 配置有效期开始时间,都不传则是无限制，只传开始时间就是大于开始时间即可结束时间
        *   @param  startvalidendtime 配置有效期结束时间,都不传则是无限制，只传结束时间就是小于开始时间即可开始时间
        *   @param  endvalidendtime 配置有效期结束时间,都不传则是无限制，只传结束时间就是小于开始时间即可结束时间
        *   @param  configcondition 触发条件,0、不限制，1、根据车速，2、根据停车时长
        *   @param  channel 拍照的通道,用";"号隔开
        *   @param  userid 用户id
        *   @param  isuse 是否启用,0禁用,1启用
        *   @param  deleted 删除标志,1代表删除,0代表正常
        *   @param  username 用户名
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
        *名称
        */
        @RequestParam(value = "name",required = false) String  name,
        /**
        *拍照间隔(单位：分)
        */
        @RequestParam(value = "configinterval",required = false) Long  configinterval,
        /**
        *创建时间
        */
        @RequestParam(value = "createdate",required = false) Date  startcreatedate,
        @RequestParam(value = "createdate",required = false) Date  endcreatedate,
        /**
        *修改时间
        */
        @RequestParam(value = "updatedate",required = false) Date  startupdatedate,
        @RequestParam(value = "updatedate",required = false) Date  endupdatedate,
        /**
        *配置生效开始时间，时间格式为00:00:00 
        */
        @RequestParam(value = "starttime",required = false) String  starttime,
        /**
        *配置生效结束时间,时间格式为 23:59:59
        */
        @RequestParam(value = "endtime",required = false) String  endtime,
        /**
        *配置有效期开始时间,都不传则是无限制，只传开始时间就是大于开始时间即可
        */
        @RequestParam(value = "validstarttime",required = false) Date  startvalidstarttime,
        @RequestParam(value = "validstarttime",required = false) Date  endvalidstarttime,
        /**
        *配置有效期结束时间,都不传则是无限制，只传结束时间就是小于开始时间即可
        */
        @RequestParam(value = "validendtime",required = false) Date  startvalidendtime,
        @RequestParam(value = "validendtime",required = false) Date  endvalidendtime,
        /**
        *触发条件,0、不限制，1、根据车速，2、根据停车时长
        */
        @RequestParam(value = "configcondition",required = false) Long  configcondition,
        /**
        *拍照的通道,用";"号隔开
        */
        @RequestParam(value = "channel",required = false) String  channel,
        /**
        *用户id
        */
        @RequestParam(value = "userid",required = false) Long  userid,
        /**
        *是否启用,0禁用,1启用
        */
        @RequestParam(value = "isuse",required = false) Long  isuse,
        /**
        *删除标志,1代表删除,0代表正常
        */
        @RequestParam(value = "deleted",required = false) Long  deleted,
        /**
        *用户名
        */
        @RequestParam(value = "username",required = false) String  username,
HttpServletRequest request,
HttpServletResponse response
) {
try {
Map params = getParams(request);
Uservehicleauthority uservehicleauthority=getUservehicleauthorityByWeb(depIds);
quanxianUtil.getquanxian(uservehicleauthority, params);
PaginateResult selectmemberlist =takingphotosbytimeservice.selectlist(params, 0, 0);
List lists = selectmemberlist.getRows();
List exldate = new ArrayList<>();
for (int i = 0; i < lists.size(); i++) {
Object row=lists.get(i);
TakingphotosbytimeExlVo takingphotosbytime = MaptoBeanUtil.mapToBean((Map
<String, Object>) row, TakingphotosbytimeExlVo.class);
takingphotosbytime.setId(i+1);
exldate.add(takingphotosbytime);
}
exceClasslUtil.exlport("定时拍照配置表", request, response, exldate, TakingphotosbytimeExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
} catch (Exception e) {
log.error("定时拍照配置表导出列表失败", e);
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