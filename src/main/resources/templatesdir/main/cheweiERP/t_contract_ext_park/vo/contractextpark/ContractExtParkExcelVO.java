package cheweiERP.t_contract_ext_park.vo.contractextpark;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

import java.util.Date;

@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ContractExtParkExcelVO   {
    @ExcelProperty("车场名称")
    private String parkName;
    @ExcelProperty("运营商id")
    private String operatorId;
    @ExcelProperty("运营商名称")
    private String operatorName;
    @ExcelProperty("城市id")
    private String cityId;
    @ExcelProperty("城市名称")
    private String cityName;
    @ExcelProperty("附加合同主键")
    private Long  contractextId;
    @ExcelProperty("合同编号")
    private String  contractNo;
    @ExcelProperty("关联合同编号")
    private String  contractNoExt;
    @ExcelProperty("车场guid")
    private String  parkGuid;
    @ExcelProperty("签约日期")
    private Date  signDate;
    @ExcelProperty("主合同金额(分)")
    private Integer  contractAmountExt;
    @ExcelProperty("已回款总金额(分)")
    private Integer  receivableAmount;
    @ExcelProperty("合同金额 (分),这个是调整之后的合同金额")
    private Integer  contractAmount;
    @ExcelProperty("合同剩余年限 (单位 月)")
    private Integer  contractPeriod;
    @ExcelProperty("周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月")
    private Integer  periodsType;
    @ExcelProperty("每月服务费(分)")
    private Integer  singlePhaseAmount;
    @ExcelProperty("试用方式：0 试用天数 1 试用笔数 2 设置时间")
    private Integer  tryWay;
    @ExcelProperty("根据试用方式传入相关的参数")
    private String  tryNumber;
    @ExcelProperty("扣款方式,0 全部优先回款 1 按百分比回款")
    private Integer  deductMoneyType;
    @ExcelProperty("扣款百分比")
    private String  deductMoneyPercent;
    @ExcelProperty("是否终止原计划")
    private Boolean  stopContract;
    @ExcelProperty("备注")
    private String  remark;
    @ExcelProperty("0、审批中  1、审批通过，2、审批失败")
    private Integer  approveStatus;
    @ExcelProperty("审批拒绝原因")
    private String  processFailedRemark;
    @ExcelProperty("销售负责人账号id")
    private Long  saleUserId;
    @ExcelProperty("销售负责人姓名")
    private String  salePerson;
    @ExcelProperty("创建人ID")
    private Long  createId;
    @ExcelProperty("创建人姓名")
    private String  createName;
    @ExcelProperty("创建时间")
    private Date  createTime;
    @ExcelProperty("更新时间")
    private Date  updateTime;
    @ExcelProperty("操作人ID")
    private Long  handlerId;
    @ExcelProperty("操作人姓名")
    private String  handlerName;


}