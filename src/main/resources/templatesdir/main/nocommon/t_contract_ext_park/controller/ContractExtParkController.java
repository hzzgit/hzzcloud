package nocommon.t_contract_ext_park.controller;

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


/**
* 保存车场合同附加协议 ,使用json请求，表单数据是解释含义，不需要传
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  ContractExtPark t_contract_ext_park, HttpServletRequest request
)
throws Exception {
t_contract_ext_parkservice.save(t_contract_ext_park);
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
if(onlineUser.isSuperAdmin()){
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