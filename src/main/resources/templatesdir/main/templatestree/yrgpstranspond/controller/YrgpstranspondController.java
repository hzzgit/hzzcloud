package templatestree.yrgpstranspond.controller;

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
* 奕人gps转发基于protobuffer协议配置表接口层
*/
@Controller
@RequestMapping("/yrgpstranspond")
@Slf4j
public class YrgpstranspondController  extends GenericAction{

@Autowired
private YrgpstranspondService yrgpstranspondservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;


/**
* 保存奕人gps转发基于protobuffer协议配置表 ,使用json请求，表单数据是解释含义，不需要传
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Yrgpstranspond yrgpstranspond, HttpServletRequest request
)
throws Exception {
yrgpstranspondservice.save(yrgpstranspond);
return new JsonMessage(true, "操作成功");
}


/**
* 删除奕人gps转发基于protobuffer协议配置表
* @param id 主键
* @return
*/
@ResponseBody
@RequestMapping("/delete.action")
public JsonMessage delete(
String id) throws Exception {
yrgpstranspondservice.fdelete(id);
return new JsonMessage(true, "操作成功");
}

/**
* 查询列表
        *   @param  id 主键
        *   @param  name 名称
        *   @param  consumer consumer为⽤户id由奕人提供
        *   @param  ak ak由奕人提供
        *   @param  ip 转发的ip地址
        *   @param  port 转发的端口号
        *   @param  startcreatedate 创建时间开始时间
        *   @param  endcreatedate 创建时间结束时间
        *   @param  startupdatedate 修改时间开始时间
        *   @param  endupdatedate 修改时间结束时间
        *   @param  userid 用户id
        *   @param  deleted 删除标志,1代表删除,0代表正常
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
        *consumer为⽤户id由奕人提供
        */
        @RequestParam(required = false) String  consumer,
        /**
        *ak由奕人提供
        */
        @RequestParam(required = false) String  ak,
        /**
        *转发的ip地址
        */
        @RequestParam(required = false) String  ip,
        /**
        *转发的端口号
        */
        @RequestParam(required = false) Long  port,
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
        *用户id
        */
        @RequestParam(required = false) Long  userid,
        /**
        *删除标志,1代表删除,0代表正常
        */
        @RequestParam(required = false) Long  deleted,
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
return yrgpstranspondservice.selectlist(params, page, rows);
} catch (Exception e) {
log.error("奕人gps转发基于protobuffer协议配置表查询列表失败", e);
return paginateResult;
}
}


/**
* 导出列表文件
        *   @param  id 主键
        *   @param  name 名称
        *   @param  consumer consumer为⽤户id由奕人提供
        *   @param  ak ak由奕人提供
        *   @param  ip 转发的ip地址
        *   @param  port 转发的端口号
        *   @param  startcreatedate 创建时间开始时间
        *   @param  endcreatedate 创建时间结束时间
        *   @param  startupdatedate 修改时间开始时间
        *   @param  endupdatedate 修改时间结束时间
        *   @param  userid 用户id
        *   @param  deleted 删除标志,1代表删除,0代表正常
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
        *consumer为⽤户id由奕人提供
        */
        @RequestParam(value = "consumer",required = false) String  consumer,
        /**
        *ak由奕人提供
        */
        @RequestParam(value = "ak",required = false) String  ak,
        /**
        *转发的ip地址
        */
        @RequestParam(value = "ip",required = false) String  ip,
        /**
        *转发的端口号
        */
        @RequestParam(value = "port",required = false) Long  port,
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
        *用户id
        */
        @RequestParam(value = "userid",required = false) Long  userid,
        /**
        *删除标志,1代表删除,0代表正常
        */
        @RequestParam(value = "deleted",required = false) Long  deleted,
HttpServletRequest request,
HttpServletResponse response
) {
try {
Map params = getParams(request);
Uservehicleauthority uservehicleauthority=getUservehicleauthorityByWeb(depIds);
quanxianUtil.getquanxian(uservehicleauthority, params);
PaginateResult selectmemberlist =yrgpstranspondservice.selectlist(params, 0, 0);
List lists = selectmemberlist.getRows();
List exldate = new ArrayList<>();
for (int i = 0; i < lists.size(); i++) {
Object row=lists.get(i);
YrgpstranspondExlVo yrgpstranspond = MaptoBeanUtil.mapToBean((Map
<String, Object>) row, YrgpstranspondExlVo.class);
yrgpstranspond.setId(i+1);
exldate.add(yrgpstranspond);
}
exceClasslUtil.exlport("奕人gps转发基于protobuffer协议配置表", request, response, exldate, YrgpstranspondExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
} catch (Exception e) {
log.error("奕人gps转发基于protobuffer协议配置表导出列表失败", e);
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