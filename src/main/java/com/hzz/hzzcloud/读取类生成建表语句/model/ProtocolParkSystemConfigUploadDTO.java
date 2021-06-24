package com.hzz.hzzcloud.读取类生成建表语句.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProtocolParkSystemConfigUploadDTO {

    /**
     * 使用单位
     */
    @JSONField(name = "SysUseName")
    private String sysUseName;

    /**
     * 服务器图片存储路径
     */
    @JSONField(name = "SysServerpic")
    private String sysServerpic;

    /**
     * 总车位数
     */
    @JSONField(name = "SysCarNum")
    private String sysCarNum;

    /**
     * 临时车A
     */
    @JSONField(name = "GroupChangerType")
    private String groupChangerType;

    /**
     * 宽容时间
     */
    @JSONField(name = "SysFreeTime")
    private Integer toleranceTime;

    /**
     * 月租车到期提示时间
     */
    @JSONField(name = "SysExpireNum")
    private Integer monthlyExpireReminderDay;

    /**
     * 是否启用手动抬闸按钮
     */
    @JSONField(name = "SysOpenGate")
    private String sysOpenGate;

    /**
     * 语音播报是否先播报车牌
     */
    @JSONField(name = "SysCarSP")
    private String sysCarSP;

    /**
     * 车位组已满转临时车
     */
    @JSONField(name = "SysGroup")
    private String sysGroup;

    /**
     * 元收费自动抬闸
     */
    @JSONField(name = "SysZeroHandle")
    private String sysZeroHandle;

    /**
     * 是否启用无牌车时间戳
     */
    @JSONField(name = "IsUnlicensedCarTime")
    private String isUnlicensedCarTime;

    /**
     * 是否支持月租车线上续费
     */
    @JSONField(name = "IsLinecharge")
    private String isLinecharge;

    /**
     * 月租车过期时间线上不能续费
     */
    @JSONField(name = "Postpone_Day")
    private Integer renewalGraceDay;

    /**
     * 重复进场时间
     */
    @JSONField(name = "TimeSecondsParkIn")
    private String timeSecondsParkIn;

    /**
     * 是否服务商模式
     */
    @JSONField(name = "ServiceMode")
    private String serviceMode;

    /**
     * 是否隐藏应收
     */
    @JSONField(name = "HeddenOpMoney")
    private String heddenOpMoney;

    /**
     * 是否隐藏现金
     */
    @JSONField(name = "HeddenChargeMoney")
    private String heddenChargeMoney;

    /**
     * 是否隐藏现金优惠
     */
    @JSONField(name = "HeddenAgioMoney")
    private String heddenAgioMoney;

    /**
     * 是否隐藏线上支付
     */
    @JSONField(name = "HeddenOnlineMoney")
    private String heddenOnlineMoney;

    /**
     * 是否隐藏停车券抵扣
     */
    @JSONField(name = "HeddenCouSetMoney")
    private String heddenCouSetMoney;

    /**
     * 月租车过期是否转临时车收费
     */
    @JSONField(name = "SysCarMmoney")
    private String sysCarMmoney;

    /**
     * 是否启用消费打折
     */
    @JSONField(name = "SysDiscount")
    private String sysDiscount;

    /**
     * 是否启用免费车
     */
    @JSONField(name = "SysFreeBtn")
    private String sysFreeBtn;

    /**
     * 启用手动抬闸
     */
    @JSONField(name = "SysOpenHandlee")
    private String sysOpenHandlee;

    /**
     * 车牌抓拍模式
     */
    @JSONField(name = "SysCarModel")
    private String sysCarModel;

    /**
     * 启用照相拍照
     */
    @JSONField(name = "SysPhoto")
    private String sysPhoto;

    /**
     * 车牌过滤时间
     */
    @JSONField(name = "SysCarNumFilter")
    private String sysCarNumFilter;

    /**
     * 数据查询每页显示行数
     */
    @JSONField(name = "sysPageNum")
    private String sys_page_num;

    /**
     * 是否启用虚假车牌过滤
     */
    @JSONField(name = "isfalse")
    private String Isfalse;

    /**
     * 是否启用线上对账0否1是
     */
    @JSONField(name = "isOnLineReport")
    private String is_on_line_report;
    //
    /**
     * D内容
     */
    @JSONField(name = "SysLedUseName")
    private String sysLedUseName;

    /**
     * 经度
     */
    @JSONField(name = "Longitude")
    private String longitude;

    /**
     * 纬度
     */
    @JSONField(name = "Latitude")
    private String latitude;

    /**
     * 总车位数
     */
    @JSONField(name = "SysTotalNum")
    private String sysTotalNum;

    /**
     * 图片临时存储路径)
     */
    @JSONField(name = "SysTempic")
    private String sysTempic;

    /**
     * 是否支持线上支付
     */
    @JSONField(name = "OnlineCharge")
    private String onlineCharge;

    /**
     * 是否启用大小周
     */
    @JSONField(name = "SysBigSmallWeek")
    private String sysBigSmallWeek;

    /**
     * 满位是否允许入场
     */
    @JSONField(name = "IsAllowIn")
    private String isAllowIn;

    /**
     * 图片同步服务器地址
     */
    @JSONField(name = "PictureAsynUrl")
    private String pictureAsynUrl;

    /**
     * 是否启用通道机设备
     */
    @JSONField(name = "EnabledChannelMachine")
    private String enabledChannelMachine;

    /**
     * 标记是否允许无牌车扫码出入场
     */
    @JSONField(name = "EnabledNoPlateCode")
    private String enabledNoPlateCode;

    /**
     * 免费出场是否需要免费原因
     */
    @JSONField(name = "IsFreeReson")
    private String isFreeReson;

    /**
     * 免费放行是否允许输入理由
     */
    @JSONField(name = "IsFreeReasonInput")
    private String isFreeReasonInput;

    /**
     * 手动抬闸是允许手动输入理由
     */
    @JSONField(name = "IsGateOpenReasonInput")
    private String isGateOpenReasonInput;

    /**
     * 月租车到期后最大容忍时间)
     */
    @JSONField(name = "SysCarMaxMinute")
    private String sysCarMaxMinute;

    /**
     * 月租车不计车位数)
     */
    @JSONField(name = "NotCountMonthCar")
    private String notCountMonthCar;

    /**
     * 免费车不计车位数)
     */
    @JSONField(name = "NotCountFreeCar")
    private String notCountFreeCar;

    /**
     * 是否启用大小场)
     */
    @JSONField(name = "AreaPark")
    private boolean areaPark;

    /**
     * 月租车A过期转换临时车类型)
     */
    @JSONField(name = "MonthlyAChangerType")
    private Integer monthlyAChangerType;

    /**
     * 月租车B过期转换临时车类型)
     */
    @JSONField(name = "MonthlyBChangerType")
    private Integer monthlyBChangerType;

    /**
     * 月租车C过期转换临时车类型)
     */
    @JSONField(name = "MonthlyCChangerType")
    private Integer monthlyCChangerType;

    /**
     * 月租车D过期转换临时车类型)
     */
    @JSONField(name = "MonthlyDChangerType")
    private Integer monthlyDChangerType;

    /**
     * 同步服务绑定地址
     */
    @JSONField(name = "SysServerAddress")
    private String sysServerAddress;

    /**
     * 同步服务绑定端口
     */
    @JSONField(name = "SysServerPort")
    private String sysServerPort;

    /**
     * 启用自定义月租车充值)
     */
    @JSONField(name = "MonthCarMoney")
    private Boolean enableCustomMonthlyCarRecharge;

    /**
     * 启用新版计费
     */
    @JSONField(name = "NewCharges")
    private boolean newChargesRule;


}
