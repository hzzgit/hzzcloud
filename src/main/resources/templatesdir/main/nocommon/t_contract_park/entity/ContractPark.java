package nocommon.t_contract_park.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="sync.t_contract_park")
public class ContractPark implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_contractid = "contractid";
    public static final String F_parkguid = "parkguid";
    public static final String F_contractno = "contractno";
    public static final String F_contractspace = "contractspace";
    public static final String F_contractinchannelcount = "contractinchannelcount";
    public static final String F_contractoutchannelcount = "contractoutchannelcount";
    public static final String F_serviceobject = "serviceobject";
    public static final String F_operationalmodel = "operationalmodel";
    public static final String F_signsource = "signsource";
    public static final String F_saleuserid = "saleuserid";
    public static final String F_saleperson = "saleperson";
    public static final String F_contractstatus = "contractstatus";
    public static final String F_signdate = "signdate";
    public static final String F_contractperiod = "contractperiod";
    public static final String F_contractstarttime = "contractstarttime";
    public static final String F_contractexpiretime = "contractexpiretime";
    public static final String F_contractamount = "contractamount";
    public static final String F_servicechargeamount = "servicechargeamount";
    public static final String F_periodstype = "periodstype";
    public static final String F_receivablestype = "receivablestype";
    public static final String F_createid = "createid";
    public static final String F_createname = "createname";
    public static final String F_createtime = "createtime";
    public static final String F_updatetime = "updatetime";
    public static final String F_handlerid = "handlerid";
    public static final String F_handlername = "handlername";
    public static final String F_tryway = "tryway";
    public static final String F_approvestatus = "approvestatus";
    public static final String F_trynumber = "trynumber";
    public static final String F_deductmoneytype = "deductmoneytype";
    public static final String F_deductmoneypercent = "deductmoneypercent";
    public static final String F_singlephaseamount = "singlephaseamount";
    public static final String F_processfailedremark = "processfailedremark";


    /**    */
        @DbId
    private Long  contractid;
    /**  车场guid  */
    private String  parkguid;
    /**  合同编号  */
    private String  contractno;
    /**  合同车场车位数  */
    private Long  contractspace;
    /**  合同入口数  */
    private Long  contractinchannelcount;
    /**  合同出口数  */
    private Long  contractoutchannelcount;
    /**  项目类型 (0:学校 1:企业 2:医院 3:小区 4:写字楼 5:商场)  */
    private Long  serviceobject;
    /**  运营模式(0 直营-硬件销售 1 代运营-硬件维保分期 2:代运营 3:经销商-硬件销售 4:代运营-无人值守 5:代运营-减员增效 6:直营-维保协议7:自营 8:线上维保 9:线下维保  )  */
    private Long  operationalmodel;
    /**  签约来源(0 渠道代理 1 直营)  */
    private Long  signsource;
    /**  销售负责人账号id  */
    private Long  saleuserid;
    /**  销售负责人姓名  */
    private String  saleperson;
    /**  合同状态 0 未生效 1 生效中 2 已过期  */
    private Long  contractstatus;
    /**  签约日期  */
    private Date  signdate;
    /**  合同期 (单位 月)  */
    private Long  contractperiod;
    /**  车场第一次上线时间  */
    private Date  contractstarttime;
    /**  车场到期时间  */
    private Date  contractexpiretime;
    /**  合同金额 (分)  */
    private Long  contractamount;
    /**  服务费(分)  */
    private Long  servicechargeamount;
    /**  周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月,5:周  */
    private Long  periodstype;
    /**  回款方式 1:系统代扣,2:手动收款  */
    private Long  receivablestype;
    /**  创建人ID  */
    private Long  createid;
    /**  创建人姓名  */
    private String  createname;
    /**  创建时间  */
    private Date  createtime;
    /**  更新时间   */
    private Date  updatetime;
    /**  操作人ID  */
    private Long  handlerid;
    /**  操作人姓名  */
    private String  handlername;
    /**  试用方式：0 试用天数
 1 试用笔数 2 试用时间  */
    private Long  tryway;
    /**  0、审批中  1、审批通过，2、审批失败  */
    private Long  approvestatus;
    /**  根据试用方式传入相关的参数  */
    private String  trynumber;
    /**  扣款方式,0 全部优先回款 1 按百分比回款  */
    private Long  deductmoneytype;
    /**  扣款百分比  */
    private String  deductmoneypercent;
    /**  每月服务费  */
    private Long  singlephaseamount;
    /**  拒绝审批的原因  */
    private String  processfailedremark;

public static void main(String[] args) {
String name="{\n";
    name +="  \"contractid\":0, //\n";
    name +="  \"parkguid\":\"\", //车场guid\n";
    name +="  \"contractno\":\"\", //合同编号\n";
    name +="  \"contractspace\":0, //合同车场车位数\n";
    name +="  \"contractinchannelcount\":0, //合同入口数\n";
    name +="  \"contractoutchannelcount\":0, //合同出口数\n";
    name +="  \"serviceobject\":0, //项目类型 (0:学校 1:企业 2:医院 3:小区 4:写字楼 5:商场)\n";
    name +="  \"operationalmodel\":0, //运营模式(0 直营-硬件销售 1 代运营-硬件维保分期 2:代运营 3:经销商-硬件销售 4:代运营-无人值守 5:代运营-减员增效 6:直营-维保协议7:自营 8:线上维保 9:线下维保  )\n";
    name +="  \"signsource\":0, //签约来源(0 渠道代理 1 直营)\n";
    name +="  \"saleuserid\":0, //销售负责人账号id\n";
    name +="  \"saleperson\":\"\", //销售负责人姓名\n";
    name +="  \"contractstatus\":0, //合同状态 0 未生效 1 生效中 2 已过期\n";
    name +="  \"signdate\":\"2020-09-11 00:00:00\", //签约日期\n";
    name +="  \"contractperiod\":0, //合同期 (单位 月)\n";
    name +="  \"contractstarttime\":\"2020-09-11 00:00:00\", //车场第一次上线时间\n";
    name +="  \"contractexpiretime\":\"2020-09-11 00:00:00\", //车场到期时间\n";
    name +="  \"contractamount\":0, //合同金额 (分)\n";
    name +="  \"servicechargeamount\":0, //服务费(分)\n";
    name +="  \"periodstype\":0, //周期类型 0:自定义, 1：1个月, 2:3个月,3:6个月,4:12个月,5:周\n";
    name +="  \"receivablestype\":0, //回款方式 1:系统代扣,2:手动收款\n";
    name +="  \"createid\":0, //创建人ID\n";
    name +="  \"createname\":\"\", //创建人姓名\n";
    name +="  \"createtime\":\"2020-09-11 00:00:00\", //创建时间\n";
    name +="  \"updatetime\":\"2020-09-11 00:00:00\", //更新时间 \n";
    name +="  \"handlerid\":0, //操作人ID\n";
    name +="  \"handlername\":\"\", //操作人姓名\n";
    name +="  \"tryway\":0, //试用方式：0 试用天数
 1 试用笔数 2 试用时间\n";
    name +="  \"approvestatus\":0, //0、审批中  1、审批通过，2、审批失败\n";
    name +="  \"trynumber\":\"\", //根据试用方式传入相关的参数\n";
    name +="  \"deductmoneytype\":0, //扣款方式,0 全部优先回款 1 按百分比回款\n";
    name +="  \"deductmoneypercent\":\"\", //扣款百分比\n";
    name +="  \"singlephaseamount\":0, //每月服务费\n";
    name +="  \"processfailedremark\":\"\" //拒绝审批的原因\n";
name+="}";
System.out.println(name);

}

}