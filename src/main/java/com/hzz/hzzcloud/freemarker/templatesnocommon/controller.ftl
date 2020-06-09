package ${packageVo.controller};

import com.alibaba.fastjson.JSON;
import com.hzz.hzzcloud.jdbcutil.util.ConverterUtils;
import lombok.extern.slf4j.Slf4j;
import net.fxft.ascswebcommon.online.TokenUser;
import net.fxft.ascswebcommon.util.easyexcel.EasyExceClasslUtil;
import net.fxft.ascswebcommon.vo.PaginateResult;
import net.fxft.ascswebcommon.web.util.JsonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hzz.hzzcloud.freemarker.util.MaptoBeanUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import ${packageVo.entity}.*;
import ${packageVo.service}.*;
import ${packageVo.exlvo}.*;
import java.io.BufferedReader;
import java.io.IOException;
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
* 保存${tableconment}
*
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  ${entityName} ${tablename}, HttpServletRequest request) throws Exception {
${tablename}service.save(${tablename});
return new JsonMessage(true, "操作成功");
}


/**
* 删除${tableconment}
*
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
*
* @return
*/
@ResponseBody
@RequestMapping("/selectlist.action")
public PaginateResult selectlist(
@RequestParam(value = "page", defaultValue = "1") int page,
@RequestParam(value = "rows", defaultValue = "10") int rows,
@RequestParam(required = true) Long[] depIds,
<#list tableColumnList as tablecolumn>
    <#if  tablecolumn.datatype=="varchar">
        //${tablecolumn.columncomment}
        @RequestParam(required = false) String  ${tablecolumn.columnname},
    <#elseif  tablecolumn.datatype=="int">
        //${tablecolumn.columncomment}
        @RequestParam(required = false) Long  ${tablecolumn.columnname},
    <#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>
        //${tablecolumn.columncomment}
        @RequestParam(required = false) Date  start${tablecolumn.columnname},
        @RequestParam(required = false) Date  end${tablecolumn.columnname},
    </#if>
</#list>
HttpServletRequest request
) {
PaginateResult paginateResult = new PaginateResult(500, "服务器错误");
try {
Map params = getParams(request);
Uservehicleauthority uservehicleauthority=getUservehicleauthorityByWeb(depIds);
getquanxian(uservehicleauthority, params);
return ${tablename}service.selectlist(params, page, rows);
} catch (Exception e) {
log.error("${tableconment}查询列表失败", e);
return paginateResult;
}
}


/**
* 导出列表文件
*
*/
@ResponseBody
@RequestMapping("/exportlist.action")
public void exportlist(
@RequestParam(required = false) Long[] depIds,
<#list tableColumnList as tablecolumn>
    <#if  tablecolumn.datatype=="varchar">
        //${tablecolumn.columncomment}
        @RequestParam(value = "${tablecolumn.columnname}",required = false) String  ${tablecolumn.columnname},
    <#elseif  tablecolumn.datatype=="int">
        //${tablecolumn.columncomment}
        @RequestParam(value = "${tablecolumn.columnname}",required = false) Long  ${tablecolumn.columnname},
    <#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>
        //${tablecolumn.columncomment}
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
getquanxian(uservehicleauthority, params);
PaginateResult selectmemberlist =${tablename}service.selectlist(params, 0, 0);
List rows = selectmemberlist.getRows();
List exldate = new ArrayList<>();
for (Object row : rows) {
${entityName}ExlVo ${tablename} = MaptoBeanUtil.mapToBean((Map<String, Object>) row, ${entityName}ExlVo.class);
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

//获取权限的Map
private Map getquanxian(Uservehicleauthority uservehicleauthority, Map param){
if(uservehicleauthority!=null) {
if (ConverterUtils.isList(uservehicleauthority.getDepIdList())) {
param.put("depIdList", uservehicleauthority.getDepIdList());
}
if (ConverterUtils.isList(uservehicleauthority.getVehicleIdList())) {
param.put("vehicleIdList", uservehicleauthority.getVehicleIdList());
}
}
return param;
}




}