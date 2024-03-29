package ${packageVo.controller};

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
* ${tableconment}接口层
*/
@Controller
@RequestMapping("/${tablename}")
@Slf4j
public class ${entityName}Controller  extends GenericAction{

@Autowired
private ${entityName}Service ${tablename}service;

@Autowired
private EasyExceClasslUtil exceClasslUtil;


/**
* 保存${tableconment} ,使用json请求，表单数据是解释含义，不需要传
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  ${entityName} ${tablename}, HttpServletRequest request
)
throws Exception {
${tablename}service.save(${tablename});
return new JsonMessage(true, "操作成功");
}


/**
* 删除${tableconment}
* @param id 主键
* @return
*/
@ResponseBody
@RequestMapping("/delete.action")
public JsonMessage delete(
String id) throws Exception {
${tablename}service.fdelete(id);
return new JsonMessage(true, "操作成功");
}

/**
* 查询列表
<#list tableColumnList as tablecolumn>
    <#if  tablecolumn.datatype=="varchar">
        *   @param  ${tablecolumn.columnname} ${tablecolumn.columncomment}
    <#elseif  tablecolumn.datatype=="int">
        *   @param  ${tablecolumn.columnname} ${tablecolumn.columncomment}
    <#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>
        *   @param  start${tablecolumn.columnname} ${tablecolumn.columncomment}开始时间
        *   @param  end${tablecolumn.columnname} ${tablecolumn.columncomment}结束时间
    </#if>
</#list>
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
<#list tableColumnList as tablecolumn>
    <#if  tablecolumn.datatype=="varchar">
        /**
        *${tablecolumn.columncomment}
        */
        @RequestParam(required = false) String  ${tablecolumn.columnname},
    <#elseif  tablecolumn.datatype=="int">
        /**
        *${tablecolumn.columncomment}
        */
        @RequestParam(required = false) Long  ${tablecolumn.columnname},
    <#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>
        /**
        *${tablecolumn.columncomment}
        */
        @RequestParam(required = false) Date  start${tablecolumn.columnname},
        @RequestParam(required = false) Date  end${tablecolumn.columnname},
    </#if>
</#list>
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
return ${tablename}service.selectlist(params, page, rows);
} catch (Exception e) {
log.error("${tableconment}查询列表失败", e);
return paginateResult;
}
}


/**
* 导出列表文件
<#list tableColumnList as tablecolumn>
    <#if  tablecolumn.datatype=="varchar">
        *   @param  ${tablecolumn.columnname} ${tablecolumn.columncomment}
    <#elseif  tablecolumn.datatype=="int">
        *   @param  ${tablecolumn.columnname} ${tablecolumn.columncomment}
    <#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>
        *   @param  start${tablecolumn.columnname} ${tablecolumn.columncomment}开始时间
        *   @param  end${tablecolumn.columnname} ${tablecolumn.columncomment}结束时间
    </#if>
</#list>
* @return
*/
@ResponseBody
@RequestMapping("/exportlist.action")
public void exportlist(
/**
*要进行查询的机构id,用逗号隔开
*/
@RequestParam(required = false) Long[] depIds,
<#list tableColumnList as tablecolumn>
    <#if  tablecolumn.datatype=="varchar">
        /**
        *${tablecolumn.columncomment}
        */
        @RequestParam(value = "${tablecolumn.columnname}",required = false) String  ${tablecolumn.columnname},
    <#elseif  tablecolumn.datatype=="int">
        /**
        *${tablecolumn.columncomment}
        */
        @RequestParam(value = "${tablecolumn.columnname}",required = false) Long  ${tablecolumn.columnname},
    <#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>
        /**
        *${tablecolumn.columncomment}
        */
        @RequestParam(value = "${tablecolumn.columnname}",required = false) Date  start${tablecolumn.columnname},
        @RequestParam(value = "${tablecolumn.columnname}",required = false) Date  end${tablecolumn.columnname},
    </#if>
</#list>
HttpServletRequest request,
HttpServletResponse response
) {
try {
Map params = getParams(request);
Uservehicleauthority uservehicleauthority=getUservehicleauthorityByWeb(depIds);
quanxianUtil.getquanxian(uservehicleauthority, params);
PaginateResult selectmemberlist =${tablename}service.selectlist(params, 0, 0);
List lists = selectmemberlist.getRows();
List exldate = new ArrayList<>();
for (int i = 0; i < lists.size(); i++) {
Object row=lists.get(i);
${entityName}ExlVo ${tablename} = MaptoBeanUtil.mapToBean((Map
<String, Object>) row, ${entityName}ExlVo.class);
${tablename}.setId(i+1);
exldate.add(${tablename});
}
exceClasslUtil.exlport("${tableconment}", request, response, exldate, ${entityName}ExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
} catch (Exception e) {
log.error("${tableconment}导出列表失败", e);
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