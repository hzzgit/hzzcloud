package com.hzz.hzzcloud.freemarker.main.personmanage.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class PersonmanageExlVo  extends BaseRowModel  {

    @ExcelProperty(value = {"序号"} )
    private int id;
    /**  从业资格证号  */
    @ExcelProperty(value = {"从业资格证号"} )
    private String  certificate;
    /**  创建时间  */
    @ExcelProperty(value = {"创建时间"} )
    private Date  createdate;
    /**  从业资格证类型  */
    @ExcelProperty(value = {"从业资格证类型"} )
    private String  certificatetype;
    /**  详细住址  */
    @ExcelProperty(value = {"详细住址"} )
    private String  address;
    /**  准驾车型  */
    @ExcelProperty(value = {"准驾车型"} )
    private String  drivingtype;
    /**  驾驶员姓名  */
    @ExcelProperty(value = {"驾驶员姓名"} )
    private String  drivername;
    /**  司机图片路径  */
    @ExcelProperty(value = {"司机图片路径"} )
    private String  driverimgurl;
    /**  更新时间  */
    @ExcelProperty(value = {"更新时间"} )
    private Date  updatedate;
    /**  性别  */
    @ExcelProperty(value = {"性别"} )
    private String  sex;
    /**  发证机构名称  */
    @ExcelProperty(value = {"发证机构名称"} )
    private String  licenseagency;
    /**  身份证号  */
    @ExcelProperty(value = {"身份证号"} )
    private String  sfzh;
    /**  证件有效期  */
    @ExcelProperty(value = {"证件有效期"} )
    private Date  invaliddate;
    /**  联系电话  */
    @ExcelProperty(value = {"联系电话"} )
    private String  phone;
    /**  发证时间  */
    @ExcelProperty(value = {"发证时间"} )
    private Date  isusedate;
    /**  年审日期  */
    @ExcelProperty(value = {"年审日期"} )
    private Date  carefuldate;
    /**  监督机构  */
    @ExcelProperty(value = {"监督机构"} )
    private String  monitororg;
    /**  监督电话  */
    @ExcelProperty(value = {"监督电话"} )
    private String  monitorphone;
    /**  人员类型，暂时是0代表司机，1代表押运员，后续可能会添加  */
    @ExcelProperty(value = {"人员类型，暂时是0代表司机，1代表押运员，后续可能会添加"} )
    private Long  persontype;
    /**  福路通卡号  */
    @ExcelProperty(value = {"福路通卡号"} )
    private String  fullertoncard;
    /**  组织机构id  */
    @ExcelProperty(value = {"组织机构id"} )
    private String  region;
    /**  其他司机照片  */
    @ExcelProperty(value = {"其他司机照片"} )
    private String  otherimg;
    /**  驾驶证正面照片  */
    @ExcelProperty(value = {"驾驶证正面照片"} )
    private String  driverlicencefrontimg;
    /**  驾驶证副本照片  */
    @ExcelProperty(value = {"驾驶证副本照片"} )
    private String  driverlicencebackimg;
    /**  从业资格证照片  */
    @ExcelProperty(value = {"从业资格证照片"} )
    private String  certificateimg;


}