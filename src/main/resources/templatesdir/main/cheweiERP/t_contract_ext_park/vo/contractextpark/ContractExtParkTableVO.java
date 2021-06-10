package cheweiERP.t_contract_ext_park.vo.contractextpark;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
public class ContractExtParkTableVO   {

    @ApiModelProperty("车场名称")
    private String parkName;
    @ApiModelProperty("运营商id")
    private String operatorId;
    @ApiModelProperty("运营商名称")
    private String operatorName;
    @ApiModelProperty("城市id")
    private String cityId;
    @ApiModelProperty("城市名称")
    private String cityName;
    @ApiModelProperty("附加合同主键")
    private Long  contractextId;
    @ApiModelProperty("合同编号")
    private String  contractNo;
    @ApiModelProperty("关联合同编号")
    private String  contractNoExt;
    @ApiModelProperty("车场guid")
    private String  parkGuid;
    @ApiModelProperty("签约日期")
    private Date  signDate;
    @ApiModelProperty("主合同金额(分)")
    private Integer  contractAmountExt;
    @ApiModelProperty("已回款总金额(分)")
    private Integer  receivableAmount;
    @ApiModelProperty("合同金额 (分),这个是调整之后的合同金额")
    private Integer  contractAmount;
    @ApiModelProperty("合同剩余年限 (单位 月)")
    private Integer  contractPeriod;
    @ApiModelProperty("周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月")
    private Integer  periodsType;
    @ApiModelProperty("每月服务费(分)")
    private Integer  singlePhaseAmount;
    @ApiModelProperty("试用方式：0 试用天数 1 试用笔数 2 设置时间")
    private Integer  tryWay;
    @ApiModelProperty("根据试用方式传入相关的参数")
    private String  tryNumber;
    @ApiModelProperty("扣款方式,0 全部优先回款 1 按百分比回款")
    private Integer  deductMoneyType;
    @ApiModelProperty("扣款百分比")
    private String  deductMoneyPercent;
    @ApiModelProperty("是否终止原计划")
    private Boolean  stopContract;
    @ApiModelProperty("备注")
    private String  remark;
    @ApiModelProperty("0、审批中  1、审批通过，2、审批失败")
    private Integer  approveStatus;
    @ApiModelProperty("审批拒绝原因")
    private String  processFailedRemark;
    @ApiModelProperty("销售负责人账号id")
    private Long  saleUserId;
    @ApiModelProperty("销售负责人姓名")
    private String  salePerson;
    @ApiModelProperty("创建人ID")
    private Long  createId;
    @ApiModelProperty("创建人姓名")
    private String  createName;
    @ApiModelProperty("创建时间")
    private Date  createTime;
    @ApiModelProperty("更新时间")
    private Date  updateTime;
    @ApiModelProperty("操作人ID")
    private Long  handlerId;
    @ApiModelProperty("操作人姓名")
    private String  handlerName;


}