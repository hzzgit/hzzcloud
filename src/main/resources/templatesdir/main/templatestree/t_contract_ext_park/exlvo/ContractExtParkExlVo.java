package templatestree.t_contract_ext_park.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class ContractExtParkExlVo  extends BaseRowModel  {

@ExcelProperty(value = {"序号"} )
private int id;
        /**  合同编号  */
        @ExcelProperty(value = {"合同编号"} )
        private String  contractno;
        /**  关联合同编号  */
        @ExcelProperty(value = {"关联合同编号"} )
        private String  contractnoext;
        /**  车场guid  */
        @ExcelProperty(value = {"车场guid"} )
        private String  parkguid;
        /**  签约日期  */
        @ExcelProperty(value = {"签约日期"} )
        private Date  signdate;
        /**  主合同金额(分)  */
        @ExcelProperty(value = {"主合同金额(分)"} )
        private Long  contractamountext;
        /**  已回款总金额(分)  */
        @ExcelProperty(value = {"已回款总金额(分)"} )
        private Long  receivableamount;
        /**  合同金额 (分),这个是调整之后的合同金额  */
        @ExcelProperty(value = {"合同金额 (分),这个是调整之后的合同金额"} )
        private Long  contractamount;
        /**  合同剩余年限 (单位 月)  */
        @ExcelProperty(value = {"合同剩余年限 (单位 月)"} )
        private Long  contractperiod;
        /**  周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月  */
        @ExcelProperty(value = {"周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月"} )
        private Long  periodstype;
        /**  每月服务费(分)  */
        @ExcelProperty(value = {"每月服务费(分)"} )
        private Long  singlephaseamount;
        /**  试用方式：0 试用天数 1 试用笔数 2 设置时间  */
        @ExcelProperty(value = {"试用方式：0 试用天数 1 试用笔数 2 设置时间"} )
        private Long  tryway;
        /**  根据试用方式传入相关的参数  */
        @ExcelProperty(value = {"根据试用方式传入相关的参数"} )
        private String  trynumber;
        /**  扣款方式,0 全部优先回款 1 按百分比回款  */
        @ExcelProperty(value = {"扣款方式,0 全部优先回款 1 按百分比回款"} )
        private Long  deductmoneytype;
        /**  扣款百分比  */
        @ExcelProperty(value = {"扣款百分比"} )
        private String  deductmoneypercent;
        /**  是否终止原计划  */
        @ExcelProperty(value = {"是否终止原计划"} )
        private String  stopcontract;
        /**  备注  */
        @ExcelProperty(value = {"备注"} )
        private String  remark;
        /**  0、审批中  1、审批通过，2、审批失败  */
        @ExcelProperty(value = {"0、审批中  1、审批通过，2、审批失败"} )
        private Long  approvestatus;
        /**  审批拒绝原因  */
        @ExcelProperty(value = {"审批拒绝原因"} )
        private String  processfailedremark;
        /**  销售负责人账号id  */
        @ExcelProperty(value = {"销售负责人账号id"} )
        private Long  saleuserid;
        /**  销售负责人姓名  */
        @ExcelProperty(value = {"销售负责人姓名"} )
        private String  saleperson;
        /**  创建人ID  */
        @ExcelProperty(value = {"创建人ID"} )
        private Long  createid;
        /**  创建人姓名  */
        @ExcelProperty(value = {"创建人姓名"} )
        private String  createname;
        /**  创建时间  */
        @ExcelProperty(value = {"创建时间"} )
        private Date  createtime;
        /**  更新时间  */
        @ExcelProperty(value = {"更新时间"} )
        private Date  updatetime;
        /**  操作人ID  */
        @ExcelProperty(value = {"操作人ID"} )
        private Long  handlerid;
        /**  操作人姓名  */
        @ExcelProperty(value = {"操作人姓名"} )
        private String  handlername;


}