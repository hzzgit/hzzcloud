package cheweiERP.t_contract_park.vo.contractpark;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cwgj.common.annotation.TypeEnumProperty;
import com.cwgj.common.utils.excel.converter.MoneyConverter;
import com.cwgj.common.utils.excel.converter.TypeEnumConverter;
import com.cwgj.common.utils.json.MoneyLongDeserializer;
import com.cwgj.common.utils.json.MoneySerializer;
import com.cwgj.service.data.common.enums.ContractStatusEnum;
import com.cwgj.service.data.common.enums.ParkOperationalModelEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ContractParkVO   {


    @ApiModelProperty("")
    @ExcelProperty("")
    private Long  contractId;
    @ApiModelProperty("车场guid")
    @ExcelProperty("车场guid")
    private String  parkGuid;
    @ApiModelProperty("合同编号")
    @ExcelProperty("合同编号")
    private String  contractNo;
    @ApiModelProperty("合同车场车位数")
    @ExcelProperty("合同车场车位数")
    private Long  contractSpace;
    @ApiModelProperty("合同入口数")
    @ExcelProperty("合同入口数")
    private Long  contractInChannelCount;
    @ApiModelProperty("合同出口数")
    @ExcelProperty("合同出口数")
    private Long  contractOutChannelCount;
    @ApiModelProperty("项目类型 (0:学校 1:企业 2:医院 3:小区 4:写字楼 5:商场)")
    @ExcelProperty("项目类型 (0:学校 1:企业 2:医院 3:小区 4:写字楼 5:商场)")
    private Long  serviceObject;
    @ApiModelProperty("运营模式(0 直营-硬件销售 1 代运营-硬件维保分期 2:代运营 3:经销商-硬件销售 4:代运营-无人值守 5:代运营-减员增效 6:直营-维保协议7:自营 8:线上维保 9:线下维保  )")
    @ExcelProperty("运营模式(0 直营-硬件销售 1 代运营-硬件维保分期 2:代运营 3:经销商-硬件销售 4:代运营-无人值守 5:代运营-减员增效 6:直营-维保协议7:自营 8:线上维保 9:线下维保  )")
    private Long  operationalModel;
    @ApiModelProperty("签约来源(0 渠道代理 1 直营)")
    @ExcelProperty("签约来源(0 渠道代理 1 直营)")
    private Long  signSource;
    @ApiModelProperty("销售负责人账号id")
    @ExcelProperty("销售负责人账号id")
    private Long  saleUserId;
    @ApiModelProperty("销售负责人姓名")
    @ExcelProperty("销售负责人姓名")
    private String  salePerson;
    @ApiModelProperty("合同状态 0 未生效 1 生效中 2 已过期")
    @ExcelProperty("合同状态 0 未生效 1 生效中 2 已过期")
    private Long  contractStatus;
    @ApiModelProperty("签约日期")
    @ExcelProperty("签约日期")
    private Date  signDate;
    @ApiModelProperty("合同期 (单位 月)")
    @ExcelProperty("合同期 (单位 月)")
    private Long  contractPeriod;
    @ApiModelProperty("车场第一次上线时间")
    @ExcelProperty("车场第一次上线时间")
    private Date  contractStartTime;
    @ApiModelProperty("车场到期时间")
    @ExcelProperty("车场到期时间")
    private Date  contractExpireTime;
    @ApiModelProperty("合同金额 (分)")
    @ExcelProperty("合同金额 (分)")
    private Long  contractAmount;
    @ApiModelProperty("服务费(分)")
    @ExcelProperty("服务费(分)")
    private Long  serviceChargeAmount;
    @ApiModelProperty("周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月,5:周")
    @ExcelProperty("周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月,5:周")
    private Long  periodsType;
    @ApiModelProperty("回款方式 1:系统代扣,2:手动收款")
    @ExcelProperty("回款方式 1:系统代扣,2:手动收款")
    private Long  receivablesType;
    @ApiModelProperty("创建人ID")
    @ExcelProperty("创建人ID")
    private Long  createId;
    @ApiModelProperty("创建人姓名")
    @ExcelProperty("创建人姓名")
    private String  createName;
    @ApiModelProperty("创建时间")
    @ExcelProperty("创建时间")
    private Date  createTime;
    @ApiModelProperty("更新时间 ")
    @ExcelProperty("更新时间 ")
    private Date  updateTime;
    @ApiModelProperty("操作人ID")
    @ExcelProperty("操作人ID")
    private Long  handlerId;
    @ApiModelProperty("操作人姓名")
    @ExcelProperty("操作人姓名")
    private String  handlerName;
    @ApiModelProperty("试用方式：0 试用天数
 1 试用笔数 2 试用时间")
    @ExcelProperty("试用方式：0 试用天数
 1 试用笔数 2 试用时间")
    private Long  tryWay;
    @ApiModelProperty("0、审批中  1、审批通过，2、审批失败")
    @ExcelProperty("0、审批中  1、审批通过，2、审批失败")
    private Long  approveStatus;
    @ApiModelProperty("根据试用方式传入相关的参数")
    @ExcelProperty("根据试用方式传入相关的参数")
    private String  tryNumber;
    @ApiModelProperty("扣款方式,0 全部优先回款 1 按百分比回款")
    @ExcelProperty("扣款方式,0 全部优先回款 1 按百分比回款")
    private Long  deductMoneyType;
    @ApiModelProperty("扣款百分比")
    @ExcelProperty("扣款百分比")
    private String  deductMoneyPercent;
    @ApiModelProperty("每月服务费")
    @ExcelProperty("每月服务费")
    private Long  singlePhaseAmount;
    @ApiModelProperty("拒绝审批的原因")
    @ExcelProperty("拒绝审批的原因")
    private String  processFailedRemark;


}