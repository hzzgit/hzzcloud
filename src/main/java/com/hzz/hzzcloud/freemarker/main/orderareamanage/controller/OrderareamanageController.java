package com.hzz.hzzcloud.freemarker.main.orderareamanage.controller;

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
import com.hzz.hzzcloud.freemarker.main.orderareamanage.entity.*;
import com.hzz.hzzcloud.freemarker.main.orderareamanage.service.*;
import com.hzz.hzzcloud.freemarker.main.orderareamanage.exlvo.*;
import java.io.BufferedReader;
import java.io.IOException;
/**
* 物流订单围栏管理接口层
*/
@Controller
@RequestMapping("/orderareamanage")
@Slf4j
public class OrderareamanageController {

@Autowired
private OrderareamanageService orderareamanageservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;

@Autowired
private UserVehicleRefCacheService userVehicleRefCacheService;

/**
* 保存物流订单围栏管理
*
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Orderareamanage orderareamanage, HttpServletRequest request) throws Exception {
TokenUser tuser = TokenUser.getFromRequest(request);
orderareamanageservice.save(orderareamanage);
return new JsonMessage(true, "操作成功");
}


    /**
    * 删除物流订单围栏管理
    * @param id 主键
    * @return
    */
    @ResponseBody
    @RequestMapping("/delete.action")
    public JsonMessage delete(String id) throws Exception {
       orderareamanageservice.fdelete(id);
        return new JsonMessage(true, "操作成功");
    }

    /**
    * 查询列表
    *   @param  id 主键
    *   @param  startcreatedate 创建时间开始时间
    *   @param  endcreatedate 创建时间结束时间
    *   @param  plateno 车牌号
    *   @param  simno simNo,终端卡号
    *   @param  name 订单名称
    *   @param  startstarttime 订单开始时间开始时间
    *   @param  endstarttime 订单开始时间结束时间
    *   @param  startendtime 订单结束时间开始时间
    *   @param  endendtime 订单结束时间结束时间
    *   @param  state 状态，0、停用，1、启用
    * @return
    */
    @ResponseBody
    @RequestMapping("/selectlist.action")
    public PaginateResult selectlist(
    @RequestParam(value = "page", defaultValue = "1") int page,
    @RequestParam(value = "rows", defaultValue = "10") int rows,
    @RequestParam(required = false) Long[] depIds,
            //主键
            @RequestParam(required = false) Long  id,
            //创建时间
            @RequestParam(required = false) Date  startcreatedate,
            @RequestParam(required = false) Date  endcreatedate,
            //车牌号
            @RequestParam(required = false) String  plateno,
            //simNo,终端卡号
            @RequestParam(required = false) String  simno,
            //订单名称
            @RequestParam(required = false) String  name,
            //订单开始时间
            @RequestParam(required = false) Date  startstarttime,
            @RequestParam(required = false) Date  endstarttime,
            //订单结束时间
            @RequestParam(required = false) Date  startendtime,
            @RequestParam(required = false) Date  endendtime,
            //状态，0、停用，1、启用
            @RequestParam(required = false) Long  state,
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
            return orderareamanageservice.selectlist(params, page, rows);
        } catch (Exception e) {
            log.error("物流订单围栏管理查询列表失败", e);
            return paginateResult;
        }
    }


    /**
    * 导出列表文件
    *   @param  id 主键
    *   @param  startcreatedate 创建时间开始时间
    *   @param  endcreatedate 创建时间结束时间
    *   @param  plateno 车牌号
    *   @param  simno simNo,终端卡号
    *   @param  name 订单名称
    *   @param  startstarttime 订单开始时间开始时间
    *   @param  endstarttime 订单开始时间结束时间
    *   @param  startendtime 订单结束时间开始时间
    *   @param  endendtime 订单结束时间结束时间
    *   @param  state 状态，0、停用，1、启用
    * @return
    */
    @ResponseBody
    @RequestMapping("/exportlist.action")
    public void exportlist(
    @RequestParam(required = false) Long[] depIds,
            //主键
            @RequestParam(value = "id",required = false) Long  id,
            //创建时间
            @RequestParam(value = "createdate",required = false) Date  startcreatedate,
            @RequestParam(value = "createdate",required = false) Date  endcreatedate,
            //车牌号
            @RequestParam(value = "plateno",required = false) String  plateno,
            //simNo,终端卡号
            @RequestParam(value = "simno",required = false) String  simno,
            //订单名称
            @RequestParam(value = "name",required = false) String  name,
            //订单开始时间
            @RequestParam(value = "starttime",required = false) Date  startstarttime,
            @RequestParam(value = "starttime",required = false) Date  endstarttime,
            //订单结束时间
            @RequestParam(value = "endtime",required = false) Date  startendtime,
            @RequestParam(value = "endtime",required = false) Date  endendtime,
            //状态，0、停用，1、启用
            @RequestParam(value = "state",required = false) Long  state,
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
            PaginateResult selectmemberlist =orderareamanageservice.selectlist(params, 0, 0);
            List lists = selectmemberlist.getRows();
            List exldate = new ArrayList<>();
           for (int i = 0; i < lists.size(); i++) {
             Object row=lists.get(i);
            OrderareamanageExlVo orderareamanage = MaptoBeanUtil.mapToBean((Map <String, Object>) row, OrderareamanageExlVo.class);
            orderareamanage.setId(i+1);
            exldate.add(orderareamanage);
            }
            exceClasslUtil.exlport("物流订单围栏管理", request, response, exldate, OrderareamanageExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
         } catch (Exception e) {
             log.error("物流订单围栏管理导出列表失败", e);
         }
    }





}