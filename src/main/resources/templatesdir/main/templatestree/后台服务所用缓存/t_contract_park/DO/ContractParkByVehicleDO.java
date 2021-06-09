package templatestree.后台服务所用缓存.t_contract_park.DO;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
public class ContractParkByVehicleDO implements java.io.Serializable  {
    /*simNO*/
    private String simNo;

    /*车牌号*/
    private String plateNo;

    /**    */
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

}