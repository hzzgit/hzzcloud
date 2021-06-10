package templatestree.后台服务所用缓存.t_contract_ext_park.cache.DO;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
public class ContractExtParkCacheDO implements java.io.Serializable  {
    /*车牌号*/
    private String plateNo;

    /**  附加合同主键  */
    private Long  contractextid;
    /**  合同编号  */
    private String  contractno;
    /**  关联合同编号  */
    private String  contractnoext;
    /**  车场guid  */
    private String  parkguid;
    /**  签约日期  */
    private Date  signdate;
    /**  主合同金额(分)  */
    private Long  contractamountext;
    /**  已回款总金额(分)  */
    private Long  receivableamount;
    /**  合同金额 (分),这个是调整之后的合同金额  */
    private Long  contractamount;
    /**  合同剩余年限 (单位 月)  */
    private Long  contractperiod;
    /**  周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月  */
    private Long  periodstype;
    /**  每月服务费(分)  */
    private Long  singlephaseamount;
    /**  试用方式：0 试用天数 1 试用笔数 2 设置时间  */
    private Long  tryway;
    /**  根据试用方式传入相关的参数  */
    private String  trynumber;
    /**  扣款方式,0 全部优先回款 1 按百分比回款  */
    private Long  deductmoneytype;
    /**  扣款百分比  */
    private String  deductmoneypercent;
    /**  是否终止原计划  */
    private String  stopcontract;
    /**  备注  */
    private String  remark;
    /**  0、审批中  1、审批通过，2、审批失败  */
    private Long  approvestatus;
    /**  审批拒绝原因  */
    private String  processfailedremark;
    /**  销售负责人账号id  */
    private Long  saleuserid;
    /**  销售负责人姓名  */
    private String  saleperson;
    /**  创建人ID  */
    private Long  createid;
    /**  创建人姓名  */
    private String  createname;
    /**  创建时间  */
    private Date  createtime;
    /**  更新时间  */
    private Date  updatetime;
    /**  操作人ID  */
    private Long  handlerid;
    /**  操作人姓名  */
    private String  handlername;

}