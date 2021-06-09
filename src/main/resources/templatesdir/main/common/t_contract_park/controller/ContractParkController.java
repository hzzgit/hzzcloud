package common.t_contract_park.controller;

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
* 车场合同接口层
*/
@Controller
@RequestMapping("/t_contract_park")
@Slf4j
public class ContractParkController  extends GenericAction{

@Autowired
private ContractParkService t_contract_parkservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;


/**
* 保存车场合同 ,使用json请求，表单数据是解释含义，不需要传
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  ContractPark t_contract_park, HttpServletRequest request
)
throws Exception {
t_contract_parkservice.save(t_contract_park);
return new JsonMessage(true, "操作成功");
}


/**
* 删除车场合同
* @param id 主键
* @return
*/
@ResponseBody
@RequestMapping("/delete.action")
public JsonMessage delete(
String id) throws Exception {
t_contract_parkservice.fdelete(id);
return new JsonMessage(true, "操作成功");
}

/**
* 查询列表
        *   @param  contractNo 合同编号
        *   @param  contractSpace 合同车场车位数
        *   @param  contractInChannelCount 合同入口数
        *   @param  contractOutChannelCount 合同出口数
        *   @param  serviceObject 项目类型 (0:学校 1:企业 2:医院 3:小区 4:写字楼 5:商场)
        *   @param  operationalModel 运营模式(0 直营-硬件销售 1 代运营-硬件维保分期 2:代运营 3:经销商-硬件销售 4:代运营-无人值守 5:代运营-减员增效 6:直营-维保协议7:自营 8:线上维保 9:线下维保  )
        *   @param  signSource 签约来源(0 渠道代理 1 直营)
        *   @param  salePerson 销售负责人姓名
        *   @param  contractStatus 合同状态 0 未生效 1 生效中 2 已过期
        *   @param  startsignDate 签约日期开始时间
        *   @param  endsignDate 签约日期结束时间
        *   @param  contractPeriod 合同期 (单位 月)
        *   @param  startcontractStartTime 车场第一次上线时间开始时间
        *   @param  endcontractStartTime 车场第一次上线时间结束时间
        *   @param  startcontractExpireTime 车场到期时间开始时间
        *   @param  endcontractExpireTime 车场到期时间结束时间
        *   @param  contractAmount 合同金额 (分)
        *   @param  serviceChargeAmount 服务费(分)
        *   @param  periodsType 周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月,5:周
        *   @param  receivablesType 回款方式 1:系统代扣,2:手动收款
        *   @param  createName 创建人姓名
        *   @param  startcreateTime 创建时间开始时间
        *   @param  endcreateTime 创建时间结束时间
        *   @param  startupdateTime 更新时间 开始时间
        *   @param  endupdateTime 更新时间 结束时间
        *   @param  handlerName 操作人姓名
        *   @param  tryWay 试用方式：0 试用天数
 1 试用笔数 2 试用时间
        *   @param  approveStatus 0、审批中  1、审批通过，2、审批失败
        *   @param  tryNumber 根据试用方式传入相关的参数
        *   @param  deductMoneyType 扣款方式,0 全部优先回款 1 按百分比回款
        *   @param  deductMoneyPercent 扣款百分比
        *   @param  singlePhaseAmount 每月服务费
        *   @param  processFailedRemark 拒绝审批的原因
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
        *合同车场车位数
        */
        @RequestParam(required = false) Long  contractSpace,
        /**
        *合同入口数
        */
        @RequestParam(required = false) Long  contractInChannelCount,
        /**
        *合同出口数
        */
        @RequestParam(required = false) Long  contractOutChannelCount,
        /**
        *项目类型 (0:学校 1:企业 2:医院 3:小区 4:写字楼 5:商场)
        */
        @RequestParam(required = false) Long  serviceObject,
        /**
        *运营模式(0 直营-硬件销售 1 代运营-硬件维保分期 2:代运营 3:经销商-硬件销售 4:代运营-无人值守 5:代运营-减员增效 6:直营-维保协议7:自营 8:线上维保 9:线下维保  )
        */
        @RequestParam(required = false) Long  operationalModel,
        /**
        *签约来源(0 渠道代理 1 直营)
        */
        @RequestParam(required = false) Long  signSource,
        /**
        *销售负责人姓名
        */
        @RequestParam(required = false) String  salePerson,
        /**
        *合同状态 0 未生效 1 生效中 2 已过期
        */
        @RequestParam(required = false) Long  contractStatus,
        /**
        *签约日期
        */
        @RequestParam(required = false) Date  startsignDate,
        @RequestParam(required = false) Date  endsignDate,
        /**
        *合同期 (单位 月)
        */
        @RequestParam(required = false) Long  contractPeriod,
        /**
        *车场第一次上线时间
        */
        @RequestParam(required = false) Date  startcontractStartTime,
        @RequestParam(required = false) Date  endcontractStartTime,
        /**
        *车场到期时间
        */
        @RequestParam(required = false) Date  startcontractExpireTime,
        @RequestParam(required = false) Date  endcontractExpireTime,
        /**
        *合同金额 (分)
        */
        @RequestParam(required = false) Long  contractAmount,
        /**
        *服务费(分)
        */
        @RequestParam(required = false) Long  serviceChargeAmount,
        /**
        *周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月,5:周
        */
        @RequestParam(required = false) Long  periodsType,
        /**
        *回款方式 1:系统代扣,2:手动收款
        */
        @RequestParam(required = false) Long  receivablesType,
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
        /**
        *试用方式：0 试用天数
 1 试用笔数 2 试用时间
        */
        @RequestParam(required = false) Long  tryWay,
        /**
        *0、审批中  1、审批通过，2、审批失败
        */
        @RequestParam(required = false) Long  approveStatus,
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
        *每月服务费
        */
        @RequestParam(required = false) Long  singlePhaseAmount,
        /**
        *拒绝审批的原因
        */
        @RequestParam(required = false) String  processFailedRemark,
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
return t_contract_parkservice.selectlist(params, page, rows);
} catch (Exception e) {
log.error("车场合同查询列表失败", e);
return paginateResult;
}
}


/**
* 导出列表文件
        *   @param  contractNo 合同编号
        *   @param  contractSpace 合同车场车位数
        *   @param  contractInChannelCount 合同入口数
        *   @param  contractOutChannelCount 合同出口数
        *   @param  serviceObject 项目类型 (0:学校 1:企业 2:医院 3:小区 4:写字楼 5:商场)
        *   @param  operationalModel 运营模式(0 直营-硬件销售 1 代运营-硬件维保分期 2:代运营 3:经销商-硬件销售 4:代运营-无人值守 5:代运营-减员增效 6:直营-维保协议7:自营 8:线上维保 9:线下维保  )
        *   @param  signSource 签约来源(0 渠道代理 1 直营)
        *   @param  salePerson 销售负责人姓名
        *   @param  contractStatus 合同状态 0 未生效 1 生效中 2 已过期
        *   @param  startsignDate 签约日期开始时间
        *   @param  endsignDate 签约日期结束时间
        *   @param  contractPeriod 合同期 (单位 月)
        *   @param  startcontractStartTime 车场第一次上线时间开始时间
        *   @param  endcontractStartTime 车场第一次上线时间结束时间
        *   @param  startcontractExpireTime 车场到期时间开始时间
        *   @param  endcontractExpireTime 车场到期时间结束时间
        *   @param  contractAmount 合同金额 (分)
        *   @param  serviceChargeAmount 服务费(分)
        *   @param  periodsType 周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月,5:周
        *   @param  receivablesType 回款方式 1:系统代扣,2:手动收款
        *   @param  createName 创建人姓名
        *   @param  startcreateTime 创建时间开始时间
        *   @param  endcreateTime 创建时间结束时间
        *   @param  startupdateTime 更新时间 开始时间
        *   @param  endupdateTime 更新时间 结束时间
        *   @param  handlerName 操作人姓名
        *   @param  tryWay 试用方式：0 试用天数
 1 试用笔数 2 试用时间
        *   @param  approveStatus 0、审批中  1、审批通过，2、审批失败
        *   @param  tryNumber 根据试用方式传入相关的参数
        *   @param  deductMoneyType 扣款方式,0 全部优先回款 1 按百分比回款
        *   @param  deductMoneyPercent 扣款百分比
        *   @param  singlePhaseAmount 每月服务费
        *   @param  processFailedRemark 拒绝审批的原因
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
        *合同车场车位数
        */
        @RequestParam(value = "contractSpace",required = false) Long  contractSpace,
        /**
        *合同入口数
        */
        @RequestParam(value = "contractInChannelCount",required = false) Long  contractInChannelCount,
        /**
        *合同出口数
        */
        @RequestParam(value = "contractOutChannelCount",required = false) Long  contractOutChannelCount,
        /**
        *项目类型 (0:学校 1:企业 2:医院 3:小区 4:写字楼 5:商场)
        */
        @RequestParam(value = "serviceObject",required = false) Long  serviceObject,
        /**
        *运营模式(0 直营-硬件销售 1 代运营-硬件维保分期 2:代运营 3:经销商-硬件销售 4:代运营-无人值守 5:代运营-减员增效 6:直营-维保协议7:自营 8:线上维保 9:线下维保  )
        */
        @RequestParam(value = "operationalModel",required = false) Long  operationalModel,
        /**
        *签约来源(0 渠道代理 1 直营)
        */
        @RequestParam(value = "signSource",required = false) Long  signSource,
        /**
        *销售负责人姓名
        */
        @RequestParam(value = "salePerson",required = false) String  salePerson,
        /**
        *合同状态 0 未生效 1 生效中 2 已过期
        */
        @RequestParam(value = "contractStatus",required = false) Long  contractStatus,
        /**
        *签约日期
        */
        @RequestParam(value = "signDate",required = false) Date  startsignDate,
        @RequestParam(value = "signDate",required = false) Date  endsignDate,
        /**
        *合同期 (单位 月)
        */
        @RequestParam(value = "contractPeriod",required = false) Long  contractPeriod,
        /**
        *车场第一次上线时间
        */
        @RequestParam(value = "contractStartTime",required = false) Date  startcontractStartTime,
        @RequestParam(value = "contractStartTime",required = false) Date  endcontractStartTime,
        /**
        *车场到期时间
        */
        @RequestParam(value = "contractExpireTime",required = false) Date  startcontractExpireTime,
        @RequestParam(value = "contractExpireTime",required = false) Date  endcontractExpireTime,
        /**
        *合同金额 (分)
        */
        @RequestParam(value = "contractAmount",required = false) Long  contractAmount,
        /**
        *服务费(分)
        */
        @RequestParam(value = "serviceChargeAmount",required = false) Long  serviceChargeAmount,
        /**
        *周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月,5:周
        */
        @RequestParam(value = "periodsType",required = false) Long  periodsType,
        /**
        *回款方式 1:系统代扣,2:手动收款
        */
        @RequestParam(value = "receivablesType",required = false) Long  receivablesType,
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
        /**
        *试用方式：0 试用天数
 1 试用笔数 2 试用时间
        */
        @RequestParam(value = "tryWay",required = false) Long  tryWay,
        /**
        *0、审批中  1、审批通过，2、审批失败
        */
        @RequestParam(value = "approveStatus",required = false) Long  approveStatus,
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
        *每月服务费
        */
        @RequestParam(value = "singlePhaseAmount",required = false) Long  singlePhaseAmount,
        /**
        *拒绝审批的原因
        */
        @RequestParam(value = "processFailedRemark",required = false) String  processFailedRemark,
HttpServletRequest request,
HttpServletResponse response
) {
try {
Map params = getParams(request);
Uservehicleauthority uservehicleauthority=getUservehicleauthorityByWeb(depIds);
quanxianUtil.getquanxian(uservehicleauthority, params);
PaginateResult selectmemberlist =t_contract_parkservice.selectlist(params, 0, 0);
List lists = selectmemberlist.getRows();
List exldate = new ArrayList<>();
for (int i = 0; i < lists.size(); i++) {
Object row=lists.get(i);
ContractParkExlVo t_contract_park = MaptoBeanUtil.mapToBean((Map
<String, Object>) row, ContractParkExlVo.class);
t_contract_park.setId(i+1);
exldate.add(t_contract_park);
}
exceClasslUtil.exlport("车场合同", request, response, exldate, ContractParkExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
} catch (Exception e) {
log.error("车场合同导出列表失败", e);
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