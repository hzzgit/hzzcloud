package common.config_table_filed.controller;

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
* 用于管理平台配置表的信息的表字段配置表接口层
*/
@Controller
@RequestMapping("/config_table_filed")
@Slf4j
public class Config_table_filedController  extends GenericAction{

@Autowired
private Config_table_filedService config_table_filedservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;


/**
* 保存用于管理平台配置表的信息的表字段配置表 ,使用json请求，表单数据是解释含义，不需要传
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Config_table_filed config_table_filed, HttpServletRequest request
)
throws Exception {
config_table_filedservice.save(config_table_filed);
return new JsonMessage(true, "操作成功");
}


/**
* 删除用于管理平台配置表的信息的表字段配置表
* @param id 主键
* @return
*/
@ResponseBody
@RequestMapping("/delete.action")
public JsonMessage delete(
String id) throws Exception {
config_table_filedservice.fdelete(id);
return new JsonMessage(true, "操作成功");
}

/**
* 查询列表
        *   @param  id 主键
        *   @param  mainid 配置表主键
        *   @param  deleted 删除标志
        *   @param  colname 字段名
        *   @param  annotation 字段显示名称
        *   @param  startcreatedate 创建时间开始时间
        *   @param  endcreatedate 创建时间结束时间
        *   @param  issearchfiled 是否是查询条件字段,0=否，1为是
        *   @param  searchtype 查询方式,1 = ，2 左like 3 右like 4 全like 5 >= 6 <= 7>=且 <=(传参需要该字段开始条件加start结束条件加end开头) 8 是否下拉框，0是否1为是
        *   @param  coltype 字段类型，1 字符串 2 数字 3 yyyy-mm-dd 时间 4 yyyy-mm-dd h24:mi:ss 时间
        *   @param  isshow 是否展示，0为否，1为是
        *   @param  issort 是否排序字段
        *   @param  sorttype 排序方式，0升序，1降序
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
        *配置表主键
        */
        @RequestParam(required = false) Long  mainid,
        /**
        *删除标志
        */
        @RequestParam(required = false) Long  deleted,
        /**
        *字段名
        */
        @RequestParam(required = false) String  colname,
        /**
        *字段显示名称
        */
        @RequestParam(required = false) String  annotation,
        /**
        *创建时间
        */
        @RequestParam(required = false) Date  startcreatedate,
        @RequestParam(required = false) Date  endcreatedate,
        /**
        *是否是查询条件字段,0=否，1为是
        */
        @RequestParam(required = false) Long  issearchfiled,
        /**
        *查询方式,1 = ，2 左like 3 右like 4 全like 5 >= 6 <= 7>=且 <=(传参需要该字段开始条件加start结束条件加end开头) 8 是否下拉框，0是否1为是
        */
        @RequestParam(required = false) Long  searchtype,
        /**
        *字段类型，1 字符串 2 数字 3 yyyy-mm-dd 时间 4 yyyy-mm-dd h24:mi:ss 时间
        */
        @RequestParam(required = false) Long  coltype,
        /**
        *是否展示，0为否，1为是
        */
        @RequestParam(required = false) Long  isshow,
        /**
        *是否排序字段
        */
        @RequestParam(required = false) Long  issort,
        /**
        *排序方式，0升序，1降序
        */
        @RequestParam(required = false) Long  sorttype,
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
return config_table_filedservice.selectlist(params, page, rows);
} catch (Exception e) {
log.error("用于管理平台配置表的信息的表字段配置表查询列表失败", e);
return paginateResult;
}
}


/**
* 导出列表文件
        *   @param  id 主键
        *   @param  mainid 配置表主键
        *   @param  deleted 删除标志
        *   @param  colname 字段名
        *   @param  annotation 字段显示名称
        *   @param  startcreatedate 创建时间开始时间
        *   @param  endcreatedate 创建时间结束时间
        *   @param  issearchfiled 是否是查询条件字段,0=否，1为是
        *   @param  searchtype 查询方式,1 = ，2 左like 3 右like 4 全like 5 >= 6 <= 7>=且 <=(传参需要该字段开始条件加start结束条件加end开头) 8 是否下拉框，0是否1为是
        *   @param  coltype 字段类型，1 字符串 2 数字 3 yyyy-mm-dd 时间 4 yyyy-mm-dd h24:mi:ss 时间
        *   @param  isshow 是否展示，0为否，1为是
        *   @param  issort 是否排序字段
        *   @param  sorttype 排序方式，0升序，1降序
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
        *配置表主键
        */
        @RequestParam(value = "mainid",required = false) Long  mainid,
        /**
        *删除标志
        */
        @RequestParam(value = "deleted",required = false) Long  deleted,
        /**
        *字段名
        */
        @RequestParam(value = "colname",required = false) String  colname,
        /**
        *字段显示名称
        */
        @RequestParam(value = "annotation",required = false) String  annotation,
        /**
        *创建时间
        */
        @RequestParam(value = "createdate",required = false) Date  startcreatedate,
        @RequestParam(value = "createdate",required = false) Date  endcreatedate,
        /**
        *是否是查询条件字段,0=否，1为是
        */
        @RequestParam(value = "issearchfiled",required = false) Long  issearchfiled,
        /**
        *查询方式,1 = ，2 左like 3 右like 4 全like 5 >= 6 <= 7>=且 <=(传参需要该字段开始条件加start结束条件加end开头) 8 是否下拉框，0是否1为是
        */
        @RequestParam(value = "searchtype",required = false) Long  searchtype,
        /**
        *字段类型，1 字符串 2 数字 3 yyyy-mm-dd 时间 4 yyyy-mm-dd h24:mi:ss 时间
        */
        @RequestParam(value = "coltype",required = false) Long  coltype,
        /**
        *是否展示，0为否，1为是
        */
        @RequestParam(value = "isshow",required = false) Long  isshow,
        /**
        *是否排序字段
        */
        @RequestParam(value = "issort",required = false) Long  issort,
        /**
        *排序方式，0升序，1降序
        */
        @RequestParam(value = "sorttype",required = false) Long  sorttype,
HttpServletRequest request,
HttpServletResponse response
) {
try {
Map params = getParams(request);
Uservehicleauthority uservehicleauthority=getUservehicleauthorityByWeb(depIds);
quanxianUtil.getquanxian(uservehicleauthority, params);
PaginateResult selectmemberlist =config_table_filedservice.selectlist(params, 0, 0);
List lists = selectmemberlist.getRows();
List exldate = new ArrayList<>();
for (int i = 0; i < lists.size(); i++) {
Object row=lists.get(i);
Config_table_filedExlVo config_table_filed = MaptoBeanUtil.mapToBean((Map
<String, Object>) row, Config_table_filedExlVo.class);
config_table_filed.setId(i+1);
exldate.add(config_table_filed);
}
exceClasslUtil.exlport("用于管理平台配置表的信息的表字段配置表", request, response, exldate, Config_table_filedExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
} catch (Exception e) {
log.error("用于管理平台配置表的信息的表字段配置表导出列表失败", e);
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