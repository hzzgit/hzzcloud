package cheweiERP.t_contract_park.dto.contractpark;

import com.cwgj.common.model.dto.ParkCommonDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CreateContractParkDTO  {

    @NotNull
    @ApiModelProperty("")
    private Long  contractId;
    @NotNull
    @ApiModelProperty("车场guid")
    private String  parkGuid;
    @NotEmpty
    @ApiModelProperty("合同编号")
    private String  contractNo;
    @NotNull
    @ApiModelProperty("合同车场车位数")
    private Long  contractSpace;
    @NotNull
    @ApiModelProperty("合同入口数")
    private Long  contractInChannelCount;
    @NotNull
    @ApiModelProperty("合同出口数")
    private Long  contractOutChannelCount;
    @NotNull
    @ApiModelProperty("项目类型 (0:学校 1:企业 2:医院 3:小区 4:写字楼 5:商场)")
    private Long  serviceObject;
    @NotNull
    @ApiModelProperty("运营模式(0 直营-硬件销售 1 代运营-硬件维保分期 2:代运营 3:经销商-硬件销售 4:代运营-无人值守 5:代运营-减员增效 6:直营-维保协议7:自营 8:线上维保 9:线下维保  )")
    private Long  operationalModel;
    @NotNull
    @ApiModelProperty("签约来源(0 渠道代理 1 直营)")
    private Long  signSource;
    @NotNull
    @ApiModelProperty("销售负责人账号id")
    private Long  saleUserId;
    @NotEmpty
    @ApiModelProperty("销售负责人姓名")
    private String  salePerson;
    @NotNull
    @ApiModelProperty("合同状态 0 未生效 1 生效中 2 已过期")
    private Long  contractStatus;
    @NotNull
    @ApiModelProperty("签约日期")
    private Date  signDate;
    @NotNull
    @ApiModelProperty("合同期 (单位 月)")
    private Long  contractPeriod;
    @NotNull
    @ApiModelProperty("车场第一次上线时间")
    private Date  contractStartTime;
    @NotNull
    @ApiModelProperty("车场到期时间")
    private Date  contractExpireTime;
    @NotNull
    @ApiModelProperty("合同金额 (分)")
    private Long  contractAmount;
    @NotNull
    @ApiModelProperty("服务费(分)")
    private Long  serviceChargeAmount;
    @NotNull
    @ApiModelProperty("周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月,5:周")
    private Long  periodsType;
    @NotNull
    @ApiModelProperty("回款方式 1:系统代扣,2:手动收款")
    private Long  receivablesType;
    @NotNull
    @ApiModelProperty("创建人ID")
    private Long  createId;
    @NotEmpty
    @ApiModelProperty("创建人姓名")
    private String  createName;
    @NotNull
    @ApiModelProperty("创建时间")
    private Date  createTime;
    @NotNull
    @ApiModelProperty("更新时间 ")
    private Date  updateTime;
    @NotNull
    @ApiModelProperty("操作人ID")
    private Long  handlerId;
    @NotEmpty
    @ApiModelProperty("操作人姓名")
    private String  handlerName;
    @NotNull
    @ApiModelProperty("试用方式：0 试用天数 1 试用笔数 2 试用时间")
    private Long  tryWay;
    @NotNull
    @ApiModelProperty("0、审批中  1、审批通过，2、审批失败")
    private Long  approveStatus;
    @NotEmpty
    @ApiModelProperty("根据试用方式传入相关的参数")
    private String  tryNumber;
    @NotNull
    @ApiModelProperty("扣款方式,0 全部优先回款 1 按百分比回款")
    private Long  deductMoneyType;
    @NotEmpty
    @ApiModelProperty("扣款百分比")
    private String  deductMoneyPercent;
    @NotNull
    @ApiModelProperty("每月服务费")
    private Long  singlePhaseAmount;
    @NotEmpty
    @ApiModelProperty("拒绝审批的原因")
    private String  processFailedRemark;

}