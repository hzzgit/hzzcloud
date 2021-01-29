package templatestree.takingphotosbytime.controller;

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
@Autowired
private JdbcUtil jdbcUtil;

/**
* 保存定时拍照配置表 ,使用json请求
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Takingphotosbytime takingphotosbytime, HttpServletRequest request
)
throws Exception {
OnlineUser onlineUser = getOnlineUser();
takingphotosbytimeservice.save(takingphotosbytime,  onlineUser.getEntityId());
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
            *是否启用,0禁用,1启用
            */
            @RequestParam(required = false) Long  isuse,
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
String sql = "update subiaodb.takingphotosbytime set isuse=?  where id=?";
jdbcUtil.sql(sql).addIndexParam(isuse, id).executeUpdate();
} catch (Exception e) {
log.error("修改配置启用状态失败", e);
return json(false, "修改失败");
}
return json(true, "修改成功");

}


/**
* 查询定时拍照配置表详情
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
String sql = "select * from subiaodb.takingphotosbytime where id=?";
Takingphotosbytime takingphotosbytime = jdbcUtil.sql(sql).addIndexParam(id).queryFirst(Takingphotosbytime.class);
alldata = new HashMap();
alldata.put("takingphotosbytime", takingphotosbytime);
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
log.error("警告!用户进行定时拍照配置表机构配置修改操作,新增的机构有" + addDepIds + ",删除的机构有" + deleteDepIds + ",当前操作用户名为" + onlineUser.getName() + ",用户id为" +
onlineUser.getEntityId() + ",操作的配置表主键= " + id);
if (!StringUtil.isNullOrEmpty(addDepIds)) {
adddepIds = addDepIds.split(",");
}
if (!StringUtil.isNullOrEmpty(deleteDepIds)) {
deletedepIds = deleteDepIds.split(",");
}
takingphotosbytimeservice.saveByDep(deletedepIds, adddepIds, id);
} catch (Exception ex) {
log.error("定时拍照配置表机构配置报错,组织机构Ids" + addDepIds + "配置id" + id + "错误信息" + ex.getMessage(), ex);
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

        Map deptreemap = takingphotosbytimeservice.depNodeTree(id, depList);
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
        takingphotosbytimeservice.saveConfigByUser(id, deleteuserIdsArr, adduserIdsArr);
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
                String resql = "select userid from FreeMarker template error (DEBUG mode; use RETHROW in production!):
The following has evaluated to null or missing:
==> tableVobyUserLim  [in template "${entityName}Controller-java.ftl" at line 365, column 54]

----
Tip: If the failing expression is known to legally refer to something that's sometimes null or missing, either specify a default value like myOptionalVar!myDefault, or use <#if myOptionalVar??>when-present<#else>when-missing</#if>. (These only cover the last step of the expression; to cover the whole expression, use parenthesis: (myOptionalVar.foo)!myDefault, (myOptionalVar.foo)??
----

----
FTL stack trace ("~" means nesting-related):
	- Failed at: ${tableVobyUserLim.tablename}  [in template "${entityName}Controller-java.ftl" at line 365, column 52]
----

Java stack trace (for programmers):
----
freemarker.core.InvalidReferenceException: [... Exception message was already printed; see it above ...]
	at freemarker.core.InvalidReferenceException.getInstance(InvalidReferenceException.java:134)
	at freemarker.core.UnexpectedTypeException.newDescriptionBuilder(UnexpectedTypeException.java:85)
	at freemarker.core.UnexpectedTypeException.<init>(UnexpectedTypeException.java:48)
	at freemarker.core.NonHashException.<init>(NonHashException.java:49)
	at freemarker.core.Dot._eval(Dot.java:48)
	at freemarker.core.Expression.eval(Expression.java:101)
	at freemarker.core.DollarVariable.calculateInterpolatedStringOrMarkup(DollarVariable.java:100)
	at freemarker.core.DollarVariable.accept(DollarVariable.java:63)
	at freemarker.core.Environment.visit(Environment.java:334)
	at freemarker.core.Environment.visit(Environment.java:340)
	at freemarker.core.Environment.process(Environment.java:313)
	at freemarker.template.Template.process(Template.java:383)
	at com.hzz.hzzcloud.freemarkerbydir.FreeMarkbydirExcuter.writetem(FreeMarkbydirExcuter.java:109)
	at com.hzz.hzzcloud.freemarkerbydir.FreeMarkbydirExcuter.readTable(FreeMarkbydirExcuter.java:83)
	at com.hzz.hzzcloud.freemarkerbydir.FreeMarkbydirMain.main(FreeMarkbydirMain.java:31)
