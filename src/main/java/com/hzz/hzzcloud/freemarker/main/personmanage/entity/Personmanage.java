package com.hzz.hzzcloud.freemarker.main.personmanage.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.personmanage")
public class Personmanage implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_certificate = "certificate";
    public static final String F_id = "id";
    public static final String F_createdate = "createdate";
    public static final String F_certificatetype = "certificatetype";
    public static final String F_address = "address";
    public static final String F_drivingtype = "drivingtype";
    public static final String F_drivername = "drivername";
    public static final String F_driverimgurl = "driverimgurl";
    public static final String F_updatedate = "updatedate";
    public static final String F_sex = "sex";
    public static final String F_licenseagency = "licenseagency";
    public static final String F_sfzh = "sfzh";
    public static final String F_invaliddate = "invaliddate";
    public static final String F_phone = "phone";
    public static final String F_isusedate = "isusedate";
    public static final String F_carefuldate = "carefuldate";
    public static final String F_monitororg = "monitororg";
    public static final String F_monitorphone = "monitorphone";
    public static final String F_persontype = "persontype";
    public static final String F_fullertoncard = "fullertoncard";
    public static final String F_region = "region";
    public static final String F_otherimg = "otherimg";
    public static final String F_driverlicencefrontimg = "driverlicencefrontimg";
    public static final String F_driverlicencebackimg = "driverlicencebackimg";
    public static final String F_certificateimg = "certificateimg";


    /**  从业资格证号  */
    private String  certificate;
    /**    */
        @DbId
    private Long  id;
    /**  创建时间  */
    private Date  createdate;
    /**  从业资格证类型  */
    private String  certificatetype;
    /**  详细住址  */
    private String  address;
    /**  准驾车型  */
    private String  drivingtype;
    /**  驾驶员姓名  */
    private String  drivername;
    /**  司机图片路径  */
    private String  driverimgurl;
    /**  更新时间  */
    private Date  updatedate;
    /**  性别  */
    private String  sex;
    /**  发证机构名称  */
    private String  licenseagency;
    /**  身份证号  */
    private String  sfzh;
    /**  证件有效期  */
    private Date  invaliddate;
    /**  联系电话  */
    private String  phone;
    /**  发证时间  */
    private Date  isusedate;
    /**  年审日期  */
    private Date  carefuldate;
    /**  监督机构  */
    private String  monitororg;
    /**  监督电话  */
    private String  monitorphone;
    /**  人员类型，暂时是0代表司机，1代表押运员，后续可能会添加  */
    private Long  persontype;
    /**  福路通卡号  */
    private String  fullertoncard;
    /**  组织机构id  */
    private String  region;
    /**  其他司机照片  */
    private String  otherimg;
    /**  驾驶证正面照片  */
    private String  driverlicencefrontimg;
    /**  驾驶证副本照片  */
    private String  driverlicencebackimg;
    /**  从业资格证照片  */
    private String  certificateimg;

public static void main(String[] args) {
String name="{\n";
        name +="  \"certificate\":\"\", //从业资格证号\n";
        name +="  \"id\":0, //\n";
        name +="  \"createdate\":\"2020-09-11 00:00:00\", //创建时间\n";
        name +="  \"certificatetype\":\"\", //从业资格证类型\n";
        name +="  \"address\":\"\", //详细住址\n";
        name +="  \"drivingtype\":\"\", //准驾车型\n";
        name +="  \"drivername\":\"\", //驾驶员姓名\n";
        name +="  \"driverimgurl\":\"\", //司机图片路径\n";
        name +="  \"updatedate\":\"2020-09-11 00:00:00\", //更新时间\n";
        name +="  \"sex\":\"\", //性别\n";
        name +="  \"licenseagency\":\"\", //发证机构名称\n";
        name +="  \"sfzh\":\"\", //身份证号\n";
        name +="  \"invaliddate\":\"2020-09-11 00:00:00\", //证件有效期\n";
        name +="  \"phone\":\"\", //联系电话\n";
        name +="  \"isusedate\":\"2020-09-11 00:00:00\", //发证时间\n";
        name +="  \"carefuldate\":\"2020-09-11 00:00:00\", //年审日期\n";
        name +="  \"monitororg\":\"\", //监督机构\n";
        name +="  \"monitorphone\":\"\", //监督电话\n";
        name +="  \"persontype\":0, //人员类型，暂时是0代表司机，1代表押运员，后续可能会添加\n";
        name +="  \"fullertoncard\":\"\", //福路通卡号\n";
        name +="  \"region\":\"\", //组织机构id\n";
        name +="  \"otherimg\":\"\", //其他司机照片\n";
        name +="  \"driverlicencefrontimg\":\"\", //驾驶证正面照片\n";
        name +="  \"driverlicencebackimg\":\"\", //驾驶证副本照片\n";
        name +="  \"certificateimg\":\"\" //从业资格证照片\n";
name+="}";
System.out.println(name);

}

}