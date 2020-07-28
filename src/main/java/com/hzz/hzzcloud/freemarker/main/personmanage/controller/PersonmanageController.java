package com.hzz.hzzcloud.freemarker.main.personmanage.controller;

import com.alibaba.fastjson.JSON;
import com.hzz.hzzcloud.jdbcutil.util.ConverterUtils;
import lombok.extern.slf4j.Slf4j;
import net.fxft.ascswebcommon.online.TokenUser;
import net.fxft.ascswebcommon.util.easyexcel.EasyExceClasslUtil;
import net.fxft.ascswebcommon.vo.PaginateResult;
import net.fxft.ascswebcommon.web.util.JsonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.hzz.hzzcloud.freemarker.util.quanxianUtil;
import com.hzz.hzzcloud.freemarker.util.RequestUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hzz.hzzcloud.freemarker.util.MaptoBeanUtil;
import net.fxft.ascswebcommon.service.impl.UserVehicleRefCacheService;
import net.fxft.ascswebcommon.vo.UserVehicleAuthority;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.hzz.hzzcloud.freemarker.main.personmanage.entity.*;
import com.hzz.hzzcloud.freemarker.main.personmanage.service.*;
import com.hzz.hzzcloud.freemarker.main.personmanage.exlvo.*;
import java.io.BufferedReader;
import java.io.IOException;
/**
* 人员表（唯一从业资格证和姓名，保存司机信息和押运员信息）接口层
*/
@Controller
@RequestMapping("/personmanage")
@Slf4j
public class PersonmanageController {

@Autowired
private PersonmanageService personmanageservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;

@Autowired
private UserVehicleRefCacheService userVehicleRefCacheService;

/**
* 保存人员表（唯一从业资格证和姓名，保存司机信息和押运员信息）
*
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Personmanage personmanage, HttpServletRequest request) throws Exception {
TokenUser tuser = TokenUser.getFromRequest(request);
personmanageservice.save(personmanage);
return new JsonMessage(true, "操作成功");
}


    /**
    * 删除人员表（唯一从业资格证和姓名，保存司机信息和押运员信息）
    * @param id 主键
    * @return
    */
    @ResponseBody
    @RequestMapping("/delete.action")
    public JsonMessage delete(String id) throws Exception {
       personmanageservice.fdelete(id);
        return new JsonMessage(true, "操作成功");
    }

    /**
    * 查询列表
    *   @param  certificate 从业资格证号
    *   @param  id 
    *   @param  startcreatedate 创建时间开始时间
    *   @param  endcreatedate 创建时间结束时间
    *   @param  certificatetype 从业资格证类型
    *   @param  address 详细住址
    *   @param  drivingtype 准驾车型
    *   @param  drivername 驾驶员姓名
    *   @param  driverimgurl 司机图片路径
    *   @param  startupdatedate 更新时间开始时间
    *   @param  endupdatedate 更新时间结束时间
    *   @param  sex 性别
    *   @param  licenseagency 发证机构名称
    *   @param  sfzh 身份证号
    *   @param  startinvaliddate 证件有效期开始时间
    *   @param  endinvaliddate 证件有效期结束时间
    *   @param  phone 联系电话
    *   @param  startisusedate 发证时间开始时间
    *   @param  endisusedate 发证时间结束时间
    *   @param  startcarefuldate 年审日期开始时间
    *   @param  endcarefuldate 年审日期结束时间
    *   @param  monitororg 监督机构
    *   @param  monitorphone 监督电话
    *   @param  persontype 人员类型，暂时是0代表司机，1代表押运员，后续可能会添加
    *   @param  fullertoncard 福路通卡号
    *   @param  region 组织机构id
    *   @param  otherimg 其他司机照片
    *   @param  driverlicencefrontimg 驾驶证正面照片
    *   @param  driverlicencebackimg 驾驶证副本照片
    *   @param  certificateimg 从业资格证照片
    * @return
    */
    @ResponseBody
    @RequestMapping("/selectlist.action")
    public PaginateResult selectlist(
    @RequestParam(value = "page", defaultValue = "1") int page,
    @RequestParam(value = "rows", defaultValue = "10") int rows,
    @RequestParam(required = false) Long[] depIds,
            //从业资格证号
            @RequestParam(required = false) String  certificate,
            //
            @RequestParam(required = false) Long  id,
            //创建时间
            @RequestParam(required = false) Date  startcreatedate,
            @RequestParam(required = false) Date  endcreatedate,
            //从业资格证类型
            @RequestParam(required = false) String  certificatetype,
            //详细住址
            @RequestParam(required = false) String  address,
            //准驾车型
            @RequestParam(required = false) String  drivingtype,
            //驾驶员姓名
            @RequestParam(required = false) String  drivername,
            //司机图片路径
            @RequestParam(required = false) String  driverimgurl,
            //更新时间
            @RequestParam(required = false) Date  startupdatedate,
            @RequestParam(required = false) Date  endupdatedate,
            //性别
            @RequestParam(required = false) String  sex,
            //发证机构名称
            @RequestParam(required = false) String  licenseagency,
            //身份证号
            @RequestParam(required = false) String  sfzh,
            //证件有效期
            @RequestParam(required = false) Date  startinvaliddate,
            @RequestParam(required = false) Date  endinvaliddate,
            //联系电话
            @RequestParam(required = false) String  phone,
            //发证时间
            @RequestParam(required = false) Date  startisusedate,
            @RequestParam(required = false) Date  endisusedate,
            //年审日期
            @RequestParam(required = false) Date  startcarefuldate,
            @RequestParam(required = false) Date  endcarefuldate,
            //监督机构
            @RequestParam(required = false) String  monitororg,
            //监督电话
            @RequestParam(required = false) String  monitorphone,
            //人员类型，暂时是0代表司机，1代表押运员，后续可能会添加
            @RequestParam(required = false) Long  persontype,
            //福路通卡号
            @RequestParam(required = false) String  fullertoncard,
            //组织机构id
            @RequestParam(required = false) String  region,
            //其他司机照片
            @RequestParam(required = false) String  otherimg,
            //驾驶证正面照片
            @RequestParam(required = false) String  driverlicencefrontimg,
            //驾驶证副本照片
            @RequestParam(required = false) String  driverlicencebackimg,
            //从业资格证照片
            @RequestParam(required = false) String  certificateimg,
    HttpServletRequest request
    ) {
        PaginateResult paginateResult = new PaginateResult(500, "服务器错误");
        try {
            Map params = RequestUtil.getParams(request);
            TokenUser tokenUser = TokenUser.getFromRequest(request);
            if (!tokenUser.isSuperAdmin()) {
            params.put("owner", tokenUser.getUserId());
            params.put("userid", tokenUser.getUserId());
            }
            UserVehicleAuthority uservehicleauthority=userVehicleRefCacheService.getUserVehicleAuthorityByWeb(depIds,request);
            quanxianUtil.getquanxian(uservehicleauthority, params);
            return personmanageservice.selectlist(params, page, rows);
        } catch (Exception e) {
            log.error("人员表（唯一从业资格证和姓名，保存司机信息和押运员信息）查询列表失败", e);
            return paginateResult;
        }
    }


    /**
    * 导出列表文件
    *   @param  certificate 从业资格证号
    *   @param  id 
    *   @param  startcreatedate 创建时间开始时间
    *   @param  endcreatedate 创建时间结束时间
    *   @param  certificatetype 从业资格证类型
    *   @param  address 详细住址
    *   @param  drivingtype 准驾车型
    *   @param  drivername 驾驶员姓名
    *   @param  driverimgurl 司机图片路径
    *   @param  startupdatedate 更新时间开始时间
    *   @param  endupdatedate 更新时间结束时间
    *   @param  sex 性别
    *   @param  licenseagency 发证机构名称
    *   @param  sfzh 身份证号
    *   @param  startinvaliddate 证件有效期开始时间
    *   @param  endinvaliddate 证件有效期结束时间
    *   @param  phone 联系电话
    *   @param  startisusedate 发证时间开始时间
    *   @param  endisusedate 发证时间结束时间
    *   @param  startcarefuldate 年审日期开始时间
    *   @param  endcarefuldate 年审日期结束时间
    *   @param  monitororg 监督机构
    *   @param  monitorphone 监督电话
    *   @param  persontype 人员类型，暂时是0代表司机，1代表押运员，后续可能会添加
    *   @param  fullertoncard 福路通卡号
    *   @param  region 组织机构id
    *   @param  otherimg 其他司机照片
    *   @param  driverlicencefrontimg 驾驶证正面照片
    *   @param  driverlicencebackimg 驾驶证副本照片
    *   @param  certificateimg 从业资格证照片
    * @return
    */
    @ResponseBody
    @RequestMapping("/exportlist.action")
    public void exportlist(
    @RequestParam(required = false) Long[] depIds,
            //从业资格证号
            @RequestParam(value = "certificate",required = false) String  certificate,
            //
            @RequestParam(value = "id",required = false) Long  id,
            //创建时间
            @RequestParam(value = "createdate",required = false) Date  startcreatedate,
            @RequestParam(value = "createdate",required = false) Date  endcreatedate,
            //从业资格证类型
            @RequestParam(value = "certificatetype",required = false) String  certificatetype,
            //详细住址
            @RequestParam(value = "address",required = false) String  address,
            //准驾车型
            @RequestParam(value = "drivingtype",required = false) String  drivingtype,
            //驾驶员姓名
            @RequestParam(value = "drivername",required = false) String  drivername,
            //司机图片路径
            @RequestParam(value = "driverimgurl",required = false) String  driverimgurl,
            //更新时间
            @RequestParam(value = "updatedate",required = false) Date  startupdatedate,
            @RequestParam(value = "updatedate",required = false) Date  endupdatedate,
            //性别
            @RequestParam(value = "sex",required = false) String  sex,
            //发证机构名称
            @RequestParam(value = "licenseagency",required = false) String  licenseagency,
            //身份证号
            @RequestParam(value = "sfzh",required = false) String  sfzh,
            //证件有效期
            @RequestParam(value = "invaliddate",required = false) Date  startinvaliddate,
            @RequestParam(value = "invaliddate",required = false) Date  endinvaliddate,
            //联系电话
            @RequestParam(value = "phone",required = false) String  phone,
            //发证时间
            @RequestParam(value = "isusedate",required = false) Date  startisusedate,
            @RequestParam(value = "isusedate",required = false) Date  endisusedate,
            //年审日期
            @RequestParam(value = "carefuldate",required = false) Date  startcarefuldate,
            @RequestParam(value = "carefuldate",required = false) Date  endcarefuldate,
            //监督机构
            @RequestParam(value = "monitororg",required = false) String  monitororg,
            //监督电话
            @RequestParam(value = "monitorphone",required = false) String  monitorphone,
            //人员类型，暂时是0代表司机，1代表押运员，后续可能会添加
            @RequestParam(value = "persontype",required = false) Long  persontype,
            //福路通卡号
            @RequestParam(value = "fullertoncard",required = false) String  fullertoncard,
            //组织机构id
            @RequestParam(value = "region",required = false) String  region,
            //其他司机照片
            @RequestParam(value = "otherimg",required = false) String  otherimg,
            //驾驶证正面照片
            @RequestParam(value = "driverlicencefrontimg",required = false) String  driverlicencefrontimg,
            //驾驶证副本照片
            @RequestParam(value = "driverlicencebackimg",required = false) String  driverlicencebackimg,
            //从业资格证照片
            @RequestParam(value = "certificateimg",required = false) String  certificateimg,
    HttpServletRequest request,
    HttpServletResponse response
    ) {
          try {
            Map params = RequestUtil.getParams(request);
            TokenUser tokenUser = TokenUser.getFromRequest(request);
            if (!tokenUser.isSuperAdmin()) {
            params.put("owner", tokenUser.getUserId());
            params.put("userid", tokenUser.getUserId());
            }
            UserVehicleAuthority uservehicleauthority=userVehicleRefCacheService.getUserVehicleAuthorityByWeb(depIds,request);
            quanxianUtil.getquanxian(uservehicleauthority, params);
            PaginateResult selectmemberlist =personmanageservice.selectlist(params, 0, 0);
            List lists = selectmemberlist.getRows();
            List exldate = new ArrayList<>();
           for (int i = 0; i < lists.size(); i++) {
             Object row=lists.get(i);
            PersonmanageExlVo personmanage = MaptoBeanUtil.mapToBean((Map <String, Object>) row, PersonmanageExlVo.class);
            personmanage.setId(i+1);
            exldate.add(personmanage);
            }
            exceClasslUtil.exlport("人员表（唯一从业资格证和姓名，保存司机信息和押运员信息）", request, response, exldate, PersonmanageExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
         } catch (Exception e) {
             log.error("人员表（唯一从业资格证和姓名，保存司机信息和押运员信息）导出列表失败", e);
         }
    }





}