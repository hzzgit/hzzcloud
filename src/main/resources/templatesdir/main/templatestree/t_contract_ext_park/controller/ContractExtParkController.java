package templatestree.t_contract_ext_park.controller;

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
* 车场合同附加协议接口层
*/
@Controller
@RequestMapping("/t_contract_ext_park")
@Slf4j
public class ContractExtParkController  extends GenericAction{


@Autowired
private ContractExtParkService t_contract_ext_parkservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;
@Autowired
private JdbcUtil jdbcUtil;

/**
* 保存车场合同附加协议 ,使用json请求
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  ContractExtPark t_contract_ext_park, HttpServletRequest request
)
throws Exception {
OnlineUser onlineUser = getOnlineUser();
t_contract_ext_parkservice.save(t_contract_ext_park,  onlineUser.getEntityId());
return new JsonMessage(true, "操作成功");
}


/**
* 删除车场合同附加协议
* @param id 主键
* @return
*/
@ResponseBody
@RequestMapping("/delete.action")
public JsonMessage delete(
String id) throws Exception {
t_contract_ext_parkservice.fdelete(id);
return new JsonMessage(true, "操作成功");
}

/**
* 查询列表
        *   @param  contractNo 合同编号
        *   @param  contractNoExt 关联合同编号
        *   @param  startsignDate 签约日期开始时间
        *   @param  endsignDate 签约日期结束时间
        *   @param  contractAmountExt 主合同金额(分)
        *   @param  receivableAmount 已回款总金额(分)
        *   @param  contractAmount 合同金额 (分),这个是调整之后的合同金额
        *   @param  contractPeriod 合同剩余年限 (单位 月)
        *   @param  periodsType 周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月
        *   @param  singlePhaseAmount 每月服务费(分)
        *   @param  tryWay 试用方式：0 试用天数 1 试用笔数 2 设置时间
        *   @param  tryNumber 根据试用方式传入相关的参数
        *   @param  deductMoneyType 扣款方式,0 全部优先回款 1 按百分比回款
        *   @param  deductMoneyPercent 扣款百分比
        *   @param  remark 备注
        *   @param  approveStatus 0、审批中  1、审批通过，2、审批失败
        *   @param  processFailedRemark 审批拒绝原因
        *   @param  salePerson 销售负责人姓名
        *   @param  createName 创建人姓名
        *   @param  startcreateTime 创建时间开始时间
        *   @param  endcreateTime 创建时间结束时间
        *   @param  startupdateTime 更新时间开始时间
        *   @param  endupdateTime 更新时间结束时间
        *   @param  handlerName 操作人姓名
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
            *合同编号
            */
            @RequestParam(required = false) String  contractNo,
            /**
            *关联合同编号
            */
            @RequestParam(required = false) String  contractNoExt,
            /**
            *签约日期
            */
            @RequestParam(required = false) Date  startsignDate,
            @RequestParam(required = false) Date  endsignDate,
            /**
            *主合同金额(分)
            */
            @RequestParam(required = false) Long  contractAmountExt,
            /**
            *已回款总金额(分)
            */
            @RequestParam(required = false) Long  receivableAmount,
            /**
            *合同金额 (分),这个是调整之后的合同金额
            */
            @RequestParam(required = false) Long  contractAmount,
            /**
            *合同剩余年限 (单位 月)
            */
            @RequestParam(required = false) Long  contractPeriod,
            /**
            *周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月
            */
            @RequestParam(required = false) Long  periodsType,
            /**
            *每月服务费(分)
            */
            @RequestParam(required = false) Long  singlePhaseAmount,
            /**
            *试用方式：0 试用天数 1 试用笔数 2 设置时间
            */
            @RequestParam(required = false) Long  tryWay,
            /**
            *根据试用方式传入相关的参数
            */
            @RequestParam(required = false) String  tryNumber,
            /**
            *扣款方式,0 全部优先回款 1 按百分比回款
            */
            @RequestParam(required = false) Long  deductMoneyType,
            /**
            *扣款百分比
            */
            @RequestParam(required = false) String  deductMoneyPercent,
            /**
            *备注
            */
            @RequestParam(required = false) String  remark,
            /**
            *0、审批中  1、审批通过，2、审批失败
            */
            @RequestParam(required = false) Long  approveStatus,
            /**
            *审批拒绝原因
            */
            @RequestParam(required = false) String  processFailedRemark,
            /**
            *销售负责人姓名
            */
            @RequestParam(required = false) String  salePerson,
            /**
            *创建人姓名
            */
            @RequestParam(required = false) String  createName,
            /**
            *创建时间
            */
            @RequestParam(required = false) Date  startcreateTime,
            @RequestParam(required = false) Date  endcreateTime,
            /**
            *更新时间
            */
            @RequestParam(required = false) Date  startupdateTime,
            @RequestParam(required = false) Date  endupdateTime,
            /**
            *操作人姓名
            */
            @RequestParam(required = false) String  handlerName,
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
return t_contract_ext_parkservice.selectlist(params, page, rows);
} catch (Exception e) {
log.error("车场合同附加协议查询列表失败", e);
return paginateResult;
}
}


/**
* 导出列表文件
        *   @param  contractNo 合同编号
        *   @param  contractNoExt 关联合同编号
        *   @param  startsignDate 签约日期开始时间
        *   @param  endsignDate 签约日期结束时间
        *   @param  contractAmountExt 主合同金额(分)
        *   @param  receivableAmount 已回款总金额(分)
        *   @param  contractAmount 合同金额 (分),这个是调整之后的合同金额
        *   @param  contractPeriod 合同剩余年限 (单位 月)
        *   @param  periodsType 周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月
        *   @param  singlePhaseAmount 每月服务费(分)
        *   @param  tryWay 试用方式：0 试用天数 1 试用笔数 2 设置时间
        *   @param  tryNumber 根据试用方式传入相关的参数
        *   @param  deductMoneyType 扣款方式,0 全部优先回款 1 按百分比回款
        *   @param  deductMoneyPercent 扣款百分比
        *   @param  remark 备注
        *   @param  approveStatus 0、审批中  1、审批通过，2、审批失败
        *   @param  processFailedRemark 审批拒绝原因
        *   @param  salePerson 销售负责人姓名
        *   @param  createName 创建人姓名
        *   @param  startcreateTime 创建时间开始时间
        *   @param  endcreateTime 创建时间结束时间
        *   @param  startupdateTime 更新时间开始时间
        *   @param  endupdateTime 更新时间结束时间
        *   @param  handlerName 操作人姓名
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
        *合同编号
        */
        @RequestParam(value = "contractNo",required = false) String  contractNo,
        /**
        *关联合同编号
        */
        @RequestParam(value = "contractNoExt",required = false) String  contractNoExt,
        /**
        *签约日期
        */
        @RequestParam(value = "signDate",required = false) Date  startsignDate,
        @RequestParam(value = "signDate",required = false) Date  endsignDate,
        /**
        *主合同金额(分)
        */
        @RequestParam(value = "contractAmountExt",required = false) Long  contractAmountExt,
        /**
        *已回款总金额(分)
        */
        @RequestParam(value = "receivableAmount",required = false) Long  receivableAmount,
        /**
        *合同金额 (分),这个是调整之后的合同金额
        */
        @RequestParam(value = "contractAmount",required = false) Long  contractAmount,
        /**
        *合同剩余年限 (单位 月)
        */
        @RequestParam(value = "contractPeriod",required = false) Long  contractPeriod,
        /**
        *周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月
        */
        @RequestParam(value = "periodsType",required = false) Long  periodsType,
        /**
        *每月服务费(分)
        */
        @RequestParam(value = "singlePhaseAmount",required = false) Long  singlePhaseAmount,
        /**
        *试用方式：0 试用天数 1 试用笔数 2 设置时间
        */
        @RequestParam(value = "tryWay",required = false) Long  tryWay,
        /**
        *根据试用方式传入相关的参数
        */
        @RequestParam(value = "tryNumber",required = false) String  tryNumber,
        /**
        *扣款方式,0 全部优先回款 1 按百分比回款
        */
        @RequestParam(value = "deductMoneyType",required = false) Long  deductMoneyType,
        /**
        *扣款百分比
        */
        @RequestParam(value = "deductMoneyPercent",required = false) String  deductMoneyPercent,
        /**
        *备注
        */
        @RequestParam(value = "remark",required = false) String  remark,
        /**
        *0、审批中  1、审批通过，2、审批失败
        */
        @RequestParam(value = "approveStatus",required = false) Long  approveStatus,
        /**
        *审批拒绝原因
        */
        @RequestParam(value = "processFailedRemark",required = false) String  processFailedRemark,
        /**
        *销售负责人姓名
        */
        @RequestParam(value = "salePerson",required = false) String  salePerson,
        /**
        *创建人姓名
        */
        @RequestParam(value = "createName",required = false) String  createName,
        /**
        *创建时间
        */
        @RequestParam(value = "createTime",required = false) Date  startcreateTime,
        @RequestParam(value = "createTime",required = false) Date  endcreateTime,
        /**
        *更新时间
        */
        @RequestParam(value = "updateTime",required = false) Date  startupdateTime,
        @RequestParam(value = "updateTime",required = false) Date  endupdateTime,
        /**
        *操作人姓名
        */
        @RequestParam(value = "handlerName",required = false) String  handlerName,
HttpServletRequest request,
HttpServletResponse response
) {
try {
Map params = getParams(request);
Uservehicleauthority uservehicleauthority=getUservehicleauthorityByWeb(depIds);
quanxianUtil.getquanxian(uservehicleauthority, params);
PaginateResult selectmemberlist =t_contract_ext_parkservice.selectlist(params, 0, 0);
List lists = selectmemberlist.getRows();
List exldate = new ArrayList<>();
for (int i = 0; i < lists.size(); i++) {
Object row=lists.get(i);
ContractExtParkExlVo t_contract_ext_park = MaptoBeanUtil.mapToBean((Map
<String, Object>) row, ContractExtParkExlVo.class);
t_contract_ext_park.setId(i+1);
exldate.add(t_contract_ext_park);
}
exceClasslUtil.exlport("车场合同附加协议", request, response, exldate, ContractExtParkExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
} catch (Exception e) {
log.error("车场合同附加协议导出列表失败", e);
}
}


/**
* 启用/禁用配置
*
* @param id    主键,contractextId
* @param isuse 1启用，0禁用
* @return
*/
@ResponseBody
@RequestMapping("/updateisuse.action")
public JsonMessage updateIsuse(
Integer id, int isuse
) {
try {
String sql = "update sync.t_contract_ext_park set isuse=?  where contractextId=?";
jdbcUtil.sql(sql).addIndexParam(isuse, id).executeUpdate();
} catch (Exception e) {
log.error("修改配置启用状态失败", e);
return json(false, "修改失败");
}
return json(true, "修改成功");

}


/**
* 查询车场合同附加协议详情
*
* @param id 主键 contractextId
* @return
*/
@ResponseBody
@RequestMapping("/searchdetail.action")
public JsonMessage searchDetail(
Integer id
) {
Map alldata = null;
try {
String sql = "select * from sync.t_contract_ext_park where contractextId=?";
ContractExtPark t_contract_ext_park = jdbcUtil.sql(sql).addIndexParam(id).queryFirst(ContractExtPark.class);
alldata = new HashMap();
alldata.put("t_contract_ext_park", t_contract_ext_park);
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
* @param id           配置表主键,也就是 contractextId
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
log.error("警告!用户进行车场合同附加协议机构配置修改操作,新增的机构有" + addDepIds + ",删除的机构有" + deleteDepIds + ",当前操作用户名为" + onlineUser.getName() + ",用户id为" +
onlineUser.getEntityId() + ",操作的配置表主键= " + id);
if (!StringUtil.isNullOrEmpty(addDepIds)) {
adddepIds = addDepIds.split(",");
}
if (!StringUtil.isNullOrEmpty(deleteDepIds)) {
deletedepIds = deleteDepIds.split(",");
}
t_contract_ext_parkservice.saveByDep(deletedepIds, adddepIds, id);
} catch (Exception ex) {
log.error("车场合同附加协议机构配置报错,组织机构Ids" + addDepIds + "配置id" + id + "错误信息" + ex.getMessage(), ex);
return new JsonMessage(false, "保存失败");
}
return new JsonMessage(true, "保存成功");
}




/**
* 查看该配置的机构授权数据（树），contractextId
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

        Map deptreemap = t_contract_ext_parkservice.depNodeTree(id, depList);
        return json(true, deptreemap);
        } else {
        return json(false, "查询id不能为空");
        }
        }



        /**
        * 保存配置的用户授权表
        *
        * @param id 主键  contractextId
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
        t_contract_ext_parkservice.saveConfigByUser(id, deleteuserIdsArr, adduserIdsArr);
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
                String resql = "select userid from t_contract_ext_parkuserlim where mainid=?";
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
                        log.debug("用户进行车场合同附加协议车辆权限修改操作,新增的车辆有" + addvehicleIds + ",删除的车辆有" + deletevehicleIds + ",当前操作用户名为" + onlineUser.getName() + ",用户id为+" +
                        onlineUser.getEntityId() + ",操作的配置表主键= " + id);
                        if (!com.ltmonitor.util.StringUtil.isNullOrEmpty(deletevehicleIds)) {
                        deletevehicleId = deletevehicleIds.split(",");
                        }
                        if (!com.ltmonitor.util.StringUtil.isNullOrEmpty(addvehicleIds)) {
                        addvehicleId = addvehicleIds.split(",");
                        }
                        if (!t_contract_ext_parkservice.saveByVehicle(addvehicleId, deletevehicleId, id)) {
                        return json(true, "保存失败");
                        }
                        return json(true, "保存成功");
                        }


                        /**
                        * 查看该配置的车辆授权数据（树）
                        *
                        * @param id 要查询的配置表主键，contractextId
                        * @return
                        */
                        @ResponseBody
                        @RequestMapping("/searchvechcle.action")
                        public JsonMessage searchVechcleById(Integer id) {
                        Uservehicleauthority uservehicleauthority = getUservehicleauthorityByWeb(0);
                        if (getOnlineUser().isSuperAdmin()) {
                        uservehicleauthority.setDepIdList(getOnlineUser().getDepIdList());
                        }
                        Map deptreemap = t_contract_ext_parkservice.depandceNodeTree(id, uservehicleauthority);
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