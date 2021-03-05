package templatestree.config_table_filed.controller;

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
@Autowired
private JdbcUtil jdbcUtil;

/**
* 保存用于管理平台配置表的信息的表字段配置表 ,使用json请求
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Config_table_filed config_table_filed, HttpServletRequest request
)
throws Exception {
OnlineUser onlineUser = getOnlineUser();
config_table_filedservice.save(config_table_filed,  onlineUser.getEntityId());
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
if(!onlineUser.isSuperAdmin()){
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


/**
* 启用/禁用配置
*
* @param id    主键,id
* @param isuse 1启用，0禁用
* @return
*/
@ResponseBody
@RequestMapping("/updateisuse.action")
public JsonMessage updateIsuse(
Integer id, int isuse
) {
try {
String sql = "update subiaodb.config_table_filed set isuse=?  where id=?";
jdbcUtil.sql(sql).addIndexParam(isuse, id).executeUpdate();
} catch (Exception e) {
log.error("修改配置启用状态失败", e);
return json(false, "修改失败");
}
return json(true, "修改成功");

}


/**
* 查询用于管理平台配置表的信息的表字段配置表详情
*
* @param id 主键 id
* @return
*/
@ResponseBody
@RequestMapping("/searchdetail.action")
public JsonMessage searchDetail(
Integer id
) {
Map alldata = null;
try {
String sql = "select * from subiaodb.config_table_filed where id=?";
Config_table_filed config_table_filed = jdbcUtil.sql(sql).addIndexParam(id).queryFirst(Config_table_filed.class);
alldata = new HashMap();
alldata.put("config_table_filed", config_table_filed);
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
* @param id           配置表主键,也就是 id
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
log.error("警告!用户进行用于管理平台配置表的信息的表字段配置表机构配置修改操作,新增的机构有" + addDepIds + ",删除的机构有" + deleteDepIds + ",当前操作用户名为" + onlineUser.getName() + ",用户id为" +
onlineUser.getEntityId() + ",操作的配置表主键= " + id);
if (!StringUtil.isNullOrEmpty(addDepIds)) {
adddepIds = addDepIds.split(",");
}
if (!StringUtil.isNullOrEmpty(deleteDepIds)) {
deletedepIds = deleteDepIds.split(",");
}
config_table_filedservice.saveByDep(deletedepIds, adddepIds, id);
} catch (Exception ex) {
log.error("用于管理平台配置表的信息的表字段配置表机构配置报错,组织机构Ids" + addDepIds + "配置id" + id + "错误信息" + ex.getMessage(), ex);
return new JsonMessage(false, "保存失败");
}
return new JsonMessage(true, "保存成功");
}




/**
* 查看该配置的机构授权数据（树），id
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

        Map deptreemap = config_table_filedservice.depNodeTree(id, depList);
        return json(true, deptreemap);
        } else {
        return json(false, "查询id不能为空");
        }
        }



        /**
        * 保存配置的用户授权表
        *
        * @param id 主键  id
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
        config_table_filedservice.saveConfigByUser(id, deleteuserIdsArr, adduserIdsArr);
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
                String resql = "select userid from config_table_fileduserlim where mainid=?";
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
                        log.debug("用户进行用于管理平台配置表的信息的表字段配置表车辆权限修改操作,新增的车辆有" + addvehicleIds + ",删除的车辆有" + deletevehicleIds + ",当前操作用户名为" + onlineUser.getName() + ",用户id为+" +
                        onlineUser.getEntityId() + ",操作的配置表主键= " + id);
                        if (!com.ltmonitor.util.StringUtil.isNullOrEmpty(deletevehicleIds)) {
                        deletevehicleId = deletevehicleIds.split(",");
                        }
                        if (!com.ltmonitor.util.StringUtil.isNullOrEmpty(addvehicleIds)) {
                        addvehicleId = addvehicleIds.split(",");
                        }
                        if (!config_table_filedservice.saveByVehicle(addvehicleId, deletevehicleId, id)) {
                        return json(true, "保存失败");
                        }
                        return json(true, "保存成功");
                        }


                        /**
                        * 查看该配置的车辆授权数据（树）
                        *
                        * @param id 要查询的配置表主键，id
                        * @return
                        */
                        @ResponseBody
                        @RequestMapping("/searchvechcle.action")
                        public JsonMessage searchVechcleById(Integer id) {
                        Uservehicleauthority uservehicleauthority = getUservehicleauthorityByWeb(0);
                        if (getOnlineUser().isSuperAdmin()) {
                        uservehicleauthority.setDepIdList(getOnlineUser().getDepIdList());
                        }
                        Map deptreemap = config_table_filedservice.depandceNodeTree(id, uservehicleauthority);
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