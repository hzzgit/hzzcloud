package com.hzz.hzzcloud.freemarker.main.orderareapoint.controller;

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
import com.hzz.hzzcloud.freemarker.main.orderareapoint.entity.*;
import com.hzz.hzzcloud.freemarker.main.orderareapoint.service.*;
import com.hzz.hzzcloud.freemarker.main.orderareapoint.exlvo.*;
import java.io.BufferedReader;
import java.io.IOException;
/**
* 物流订单围栏点位信息接口层
*/
@Controller
@RequestMapping("/orderareapoint")
@Slf4j
public class OrderareapointController {

@Autowired
private OrderareapointService orderareapointservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;

@Autowired
private UserVehicleRefCacheService userVehicleRefCacheService;

/**
* 保存物流订单围栏点位信息
*
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Orderareapoint orderareapoint, HttpServletRequest request) throws Exception {
TokenUser tuser = TokenUser.getFromRequest(request);
orderareapointservice.save(orderareapoint);
return new JsonMessage(true, "操作成功");
}


    /**
    * 删除物流订单围栏点位信息
    * @param id 主键
    * @return
    */
    @ResponseBody
    @RequestMapping("/delete.action")
    public JsonMessage delete(String id) throws Exception {
       orderareapointservice.fdelete(id);
        return new JsonMessage(true, "操作成功");
    }

    /**
    * 查询列表
    *   @param  id 主键
    *   @param  startcreatedate 创建时间开始时间
    *   @param  endcreatedate 创建时间结束时间
    *   @param  pointtype 点位类型,1，开始点，2，途经点，3，结束点
    *   @param  longitude 经度
    *   @param  latitude 纬度
    *   @param  maptype 地图类型 gps:天地图坐标，baidu:百度坐标，google:谷歌地图
    *   @param  orderid 和orderareamanage表主键绑定
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
            //点位类型,1，开始点，2，途经点，3，结束点
            @RequestParam(required = false) Long  pointtype,
            //经度
            @RequestParam(required = false) String  longitude,
            //纬度
            @RequestParam(required = false) String  latitude,
            //地图类型 gps:天地图坐标，baidu:百度坐标，google:谷歌地图
            @RequestParam(required = false) String  maptype,
            //和orderareamanage表主键绑定
            @RequestParam(required = false) Long  orderid,
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
            return orderareapointservice.selectlist(params, page, rows);
        } catch (Exception e) {
            log.error("物流订单围栏点位信息查询列表失败", e);
            return paginateResult;
        }
    }


    /**
    * 导出列表文件
    *   @param  id 主键
    *   @param  startcreatedate 创建时间开始时间
    *   @param  endcreatedate 创建时间结束时间
    *   @param  pointtype 点位类型,1，开始点，2，途经点，3，结束点
    *   @param  longitude 经度
    *   @param  latitude 纬度
    *   @param  maptype 地图类型 gps:天地图坐标，baidu:百度坐标，google:谷歌地图
    *   @param  orderid 和orderareamanage表主键绑定
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
            //点位类型,1，开始点，2，途经点，3，结束点
            @RequestParam(value = "pointtype",required = false) Long  pointtype,
            //经度
            @RequestParam(value = "longitude",required = false) String  longitude,
            //纬度
            @RequestParam(value = "latitude",required = false) String  latitude,
            //地图类型 gps:天地图坐标，baidu:百度坐标，google:谷歌地图
            @RequestParam(value = "maptype",required = false) String  maptype,
            //和orderareamanage表主键绑定
            @RequestParam(value = "orderid",required = false) Long  orderid,
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
            PaginateResult selectmemberlist =orderareapointservice.selectlist(params, 0, 0);
            List lists = selectmemberlist.getRows();
            List exldate = new ArrayList<>();
           for (int i = 0; i < lists.size(); i++) {
             Object row=lists.get(i);
            OrderareapointExlVo orderareapoint = MaptoBeanUtil.mapToBean((Map <String, Object>) row, OrderareapointExlVo.class);
            orderareapoint.setId(i+1);
            exldate.add(orderareapoint);
            }
            exceClasslUtil.exlport("物流订单围栏点位信息", request, response, exldate, OrderareapointExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
         } catch (Exception e) {
             log.error("物流订单围栏点位信息导出列表失败", e);
         }
    }





}