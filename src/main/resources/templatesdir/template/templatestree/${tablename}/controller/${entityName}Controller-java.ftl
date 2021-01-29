package ${packpath};

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
@Autowired
private JdbcUtil jdbcUtil;

/**
* 保存${tableconment} ,使用json请求
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  ${entityName} ${tablename}, HttpServletRequest request
)
throws Exception {
OnlineUser onlineUser = getOnlineUser();
${tablename}service.save(${tablename},  onlineUser.getEntityId());
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
    <#if   tablecolumn.columnname?lower_case!="updatedate" &&  tablecolumn.columnname?lower_case!="userid" &&  tablecolumn.columnname?lower_case!="deleted">
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


/**
* 启用/禁用配置
*
* @param id    主键,${pricolname}
* @param isuse 1启用，0禁用
* @return
*/
@ResponseBody
@RequestMapping("/updateisuse.action")
public JsonMessage updateIsuse(
Integer id, int isuse
) {
try {
String sql = "update ${tableschema}.${tablename} set isuse=?  where ${pricolname}=?";
jdbcUtil.sql(sql).addIndexParam(isuse, id).executeUpdate();
} catch (Exception e) {
log.error("修改配置启用状态失败", e);
return json(false, "修改失败");
}
return json(true, "修改成功");

}


/**
* 查询${tableconment}详情
*
* @param id 主键 ${pricolname}
* @return
*/
@ResponseBody
@RequestMapping("/searchdetail.action")
public JsonMessage searchDetail(
Integer id
) {
Map alldata = null;
try {
String sql = "select * from ${tableschema}.${tablename} where ${pricolname}=?";
${entityName} ${tablename} = jdbcUtil.sql(sql).addIndexParam(id).queryFirst(${entityName}.class);
alldata = new HashMap();
alldata.put("${tablename}", ${tablename});
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
* @param id           配置表主键,也就是 ${pricolname}
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
log.error("警告!用户进行${tableconment}机构配置修改操作,新增的机构有" + addDepIds + ",删除的机构有" + deleteDepIds + ",当前操作用户名为" + onlineUser.getName() + ",用户id为" +
onlineUser.getEntityId() + ",操作的配置表主键= " + id);
if (!StringUtil.isNullOrEmpty(addDepIds)) {
adddepIds = addDepIds.split(",");
}
if (!StringUtil.isNullOrEmpty(deleteDepIds)) {
deletedepIds = deleteDepIds.split(",");
}
${tablename}service.saveByDep(deletedepIds, adddepIds, id);
} catch (Exception ex) {
log.error("${tableconment}机构配置报错,组织机构Ids" + addDepIds + "配置id" + id + "错误信息" + ex.getMessage(), ex);
return new JsonMessage(false, "保存失败");
}
return new JsonMessage(true, "保存成功");
}




/**
* 查看该配置的机构授权数据（树），${pricolname}
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

        Map deptreemap = ${tablename}service.depNodeTree(id, depList);
        return json(true, deptreemap);
        } else {
        return json(false, "查询id不能为空");
        }
        }



        /**
        * 保存配置的用户授权表
        *
        * @param id 主键  ${pricolname}
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
        ${tablename}service.saveConfigByUser(id, deleteuserIdsArr, adduserIdsArr);
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
                String resql = "select userid from ${tableVobyUserLim.tablename} where mainid=?";
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
                        log.debug("用户进行${tableconment}车辆权限修改操作,新增的车辆有" + addvehicleIds + ",删除的车辆有" + deletevehicleIds + ",当前操作用户名为" + onlineUser.getName() + ",用户id为+" +
                        onlineUser.getEntityId() + ",操作的配置表主键= " + id);
                        if (!com.ltmonitor.util.StringUtil.isNullOrEmpty(deletevehicleIds)) {
                        deletevehicleId = deletevehicleIds.split(",");
                        }
                        if (!com.ltmonitor.util.StringUtil.isNullOrEmpty(addvehicleIds)) {
                        addvehicleId = addvehicleIds.split(",");
                        }
                        if (!${tablename}service.saveByVehicle(addvehicleId, deletevehicleId, id)) {
                        return json(true, "保存失败");
                        }
                        return json(true, "保存成功");
                        }


                        /**
                        * 查看该配置的车辆授权数据（树）
                        *
                        * @param id 要查询的配置表主键，${pricolname}
                        * @return
                        */
                        @ResponseBody
                        @RequestMapping("/searchvechcle.action")
                        public JsonMessage searchVechcleById(Integer id) {
                        Uservehicleauthority uservehicleauthority = getUservehicleauthorityByWeb(0);
                        if (getOnlineUser().isSuperAdmin()) {
                        uservehicleauthority.setDepIdList(getOnlineUser().getDepIdList());
                        }
                        Map deptreemap = ${tablename}service.depandceNodeTree(id, uservehicleauthority);
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