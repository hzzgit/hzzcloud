package common.t_contract_ext_park.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="sync.t_contract_ext_park")
public class ContractExtPark implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_contractextid = "contractextid";
    public static final String F_contractno = "contractno";
    public static final String F_contractnoext = "contractnoext";
    public static final String F_parkguid = "parkguid";
    public static final String F_signdate = "signdate";
    public static final String F_contractamountext = "contractamountext";
    public static final String F_receivableamount = "receivableamount";
    public static final String F_contractamount = "contractamount";
    public static final String F_contractperiod = "contractperiod";
    public static final String F_periodstype = "periodstype";
    public static final String F_singlephaseamount = "singlephaseamount";
    public static final String F_tryway = "tryway";
    public static final String F_trynumber = "trynumber";
    public static final String F_deductmoneytype = "deductmoneytype";
    public static final String F_deductmoneypercent = "deductmoneypercent";
    public static final String F_stopcontract = "stopcontract";
    public static final String F_remark = "remark";
    public static final String F_approvestatus = "approvestatus";
    public static final String F_processfailedremark = "processfailedremark";
    public static final String F_saleuserid = "saleuserid";
    public static final String F_saleperson = "saleperson";
    public static final String F_createid = "createid";
    public static final String F_createname = "createname";
    public static final String F_createtime = "createtime";
    public static final String F_updatetime = "updatetime";
    public static final String F_handlerid = "handlerid";
    public static final String F_handlername = "handlername";


    /**  附加合同主键  */
        @DbId
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

public static void main(String[] args) {
String name="{\n";
    name +="  \"contractextid\":0, //附加合同主键\n";
    name +="  \"contractno\":\"\", //合同编号\n";
    name +="  \"contractnoext\":\"\", //关联合同编号\n";
    name +="  \"parkguid\":\"\", //车场guid\n";
    name +="  \"signdate\":\"2020-09-11 00:00:00\", //签约日期\n";
    name +="  \"contractamountext\":0, //主合同金额(分)\n";
    name +="  \"receivableamount\":0, //已回款总金额(分)\n";
    name +="  \"contractamount\":0, //合同金额 (分),这个是调整之后的合同金额\n";
    name +="  \"contractperiod\":0, //合同剩余年限 (单位 月)\n";
    name +="  \"periodstype\":0, //周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月\n";
    name +="  \"singlephaseamount\":0, //每月服务费(分)\n";
    name +="  \"tryway\":0, //试用方式：0 试用天数 1 试用笔数 2 设置时间\n";
    name +="  \"trynumber\":\"\", //根据试用方式传入相关的参数\n";
    name +="  \"deductmoneytype\":0, //扣款方式,0 全部优先回款 1 按百分比回款\n";
    name +="  \"deductmoneypercent\":\"\", //扣款百分比\n";
    name +="  \"stopcontract\":\"\", //是否终止原计划\n";
    name +="  \"remark\":\"\", //备注\n";
    name +="  \"approvestatus\":0, //0、审批中  1、审批通过，2、审批失败\n";
    name +="  \"processfailedremark\":\"\", //审批拒绝原因\n";
    name +="  \"saleuserid\":0, //销售负责人账号id\n";
    name +="  \"saleperson\":\"\", //销售负责人姓名\n";
    name +="  \"createid\":0, //创建人ID\n";
    name +="  \"createname\":\"\", //创建人姓名\n";
    name +="  \"createtime\":\"2020-09-11 00:00:00\", //创建时间\n";
    name +="  \"updatetime\":\"2020-09-11 00:00:00\", //更新时间\n";
    name +="  \"handlerid\":0, //操作人ID\n";
    name +="  \"handlername\":\"\" //操作人姓名\n";
name+="}";
System.out.println(name);

}

}