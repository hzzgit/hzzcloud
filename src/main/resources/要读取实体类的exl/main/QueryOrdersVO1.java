

@Data
public class QueryOrdersVO1  {


    @NotEmpty(message = "用户编号不能为空")
    @ApiModelProperty("用户编号 ")
    private String  userId;
    @NotEmpty(message = "合同编号不能为空")
    @ApiModelProperty("合同编号 ")
    private String  contractNo;
    @NotEmpty(message = "车场编号不能为空")
    @ApiModelProperty("车场编号 创建车场或者选择车场的时候获取到的车场编号")
    private String  parkGuid;
    @NotNull(message = "签约时间不能为空")
    @ApiModelProperty("签约时间 yyyy-MM-dd")
    private Date  signDate;
    @NotNull(message = "合作模式不能为空")
    @ApiModelProperty("合作模式 0 代运营 1 硬件销售 2 维保服务")
    private Integer  operationalModel;
    @NotNull(message = "合作模式子项不能为空")
    @ApiModelProperty("合作模式子项 合作模式为0(代运营)时必填 ： 0 无人值守 1 减员增效 2 硬件分期 合作模式为2（维保服务）时必填： 0 线上维保 1 线下维保")
    private Integer  operationalSubModel;
    @NotNull(message = "合同年限不能为空")
    @ApiModelProperty("合同年限 月数")
    private Integer  contractPeriod;
    @NotNull(message = "合同金额不能为空")
    @ApiModelProperty("合同金额 单位为分")
    private Integer  contractAmount;
    @NotNull(message = "每月服务费不能为空")
    @ApiModelProperty("每月服务费 单位为分")
    private Integer  singlePhaseAmount;
    @NotNull(message = "签约来源不能为空")
    @ApiModelProperty("签约来源 0 渠道代理 1 直营")
    private Integer  signSource;
    @NotNull(message = "回款周期不能为空")
    @ApiModelProperty("回款周期 1 一个月 2 三个月 3 六个月 4 十二个月 _x000B_")
    private Integer  periodsType;
    @NotNull(message = "回款方式不能为空")
    @ApiModelProperty("回款方式 回款方式 1:系统代扣,2:手动收款")
    private Integer  receivablesType;
    @NotNull(message = "试用方式不能为空")
    @ApiModelProperty("试用方式 0 试用天数 1 试用笔数 2 设置时间(日)")
    private Integer  tryWay;
    @NotEmpty(message = "试用参数不能为空")
    @ApiModelProperty("试用参数 根据试用方式传入相关的参数")
    private String  tryNumber;
    @NotNull(message = "车位数不能为空")
    @ApiModelProperty("车位数 ")
    private Integer  contractSpace;
    @NotNull(message = "入口数不能为空")
    @ApiModelProperty("入口数 ")
    private Integer  contractInChannelCount;
    @NotNull(message = "出口数不能为空")
    @ApiModelProperty("出口数 ")
    private Integer  contractOutChannelCount;
    @NotNull(message = "项目类型不能为空")
    @ApiModelProperty("项目类型 0 商业停车场 1 写字楼/创意园 2 酒店 3 公共服务 4 住宅小区")
    private Integer  serviceObject;
    @NotNull(message = "扣款方式不能为空")
    @ApiModelProperty("扣款方式 0 全部优先回款 1 按百分比回款")
    private Integer  deductMoneyType;
    @ApiModelProperty("扣款百分比 扣款方式为1时必填")
    private String  deductMoneyPercent;
    @ApiModelProperty("合同文件url ")
    private String  contractUrl;


}

