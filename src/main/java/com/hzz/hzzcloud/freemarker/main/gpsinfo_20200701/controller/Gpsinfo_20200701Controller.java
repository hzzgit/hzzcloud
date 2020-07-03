package com.hzz.hzzcloud.freemarker.main.gpsinfo_20200701.controller;

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
import com.hzz.hzzcloud.freemarker.main.gpsinfo_20200701.entity.*;
import com.hzz.hzzcloud.freemarker.main.gpsinfo_20200701.service.*;
import com.hzz.hzzcloud.freemarker.main.gpsinfo_20200701.exlvo.*;
import java.io.BufferedReader;
import java.io.IOException;
/**
* 接口层
*/
@Controller
@RequestMapping("/gpsinfo_20200701")
@Slf4j
public class Gpsinfo_20200701Controller {

@Autowired
private Gpsinfo_20200701Service gpsinfo_20200701service;

@Autowired
private EasyExceClasslUtil exceClasslUtil;

@Autowired
private UserVehicleRefCacheService userVehicleRefCacheService;

/**
* 保存
*
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Gpsinfo_20200701 gpsinfo_20200701, HttpServletRequest request) throws Exception {
TokenUser tuser = TokenUser.getFromRequest(request);
gpsinfo_20200701service.save(gpsinfo_20200701);
return new JsonMessage(true, "操作成功");
}


    /**
    * 删除
    *
    * @return
    */
    @ResponseBody
    @RequestMapping("/delete.action")
    public JsonMessage delete(String id) throws Exception {
       gpsinfo_20200701service.fdelete(id);
        return new JsonMessage(true, "操作成功");
    }

    /**
    * 查询列表
    *
    * @return
    */
    @ResponseBody
    @RequestMapping("/selectlist.action")
    public PaginateResult selectlist(
    @RequestParam(value = "page", defaultValue = "1") int page,
    @RequestParam(value = "rows", defaultValue = "10") int rows,
    @RequestParam(required = false) Long[] depIds,
            //
            @RequestParam(required = false) Long  simnohash,
            //
            @RequestParam(required = false) String  simno,
            //
            @RequestParam(required = false) Long  vehicleid,
            //
            @RequestParam(required = false) Date  startcreatedate,
            @RequestParam(required = false) Date  endcreatedate,
            //
            @RequestParam(required = false) Date  startsendtime,
            @RequestParam(required = false) Date  endsendtime,
            //
            @RequestParam(required = false) Long  alarmstate,
            //
            @RequestParam(required = false) Long  direction,
            //
            @RequestParam(required = false) Long  status,
            //
            @RequestParam(required = false) Long  signalstate,
            //
            @RequestParam(required = false) Long  abnormaltype,
            //
            @RequestParam(required = false) Long  fromflag,
            //
            @RequestParam(required = false) Long  extendversion,
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
            return gpsinfo_20200701service.selectlist(params, page, rows);
        } catch (Exception e) {
            log.error("查询列表失败", e);
            return paginateResult;
        }
    }


    /**
    * 导出列表文件
    *
    */
    @ResponseBody
    @RequestMapping("/exportlist.action")
    public void exportlist(
    @RequestParam(required = false) Long[] depIds,
            //
            @RequestParam(value = "simnohash",required = false) Long  simnohash,
            //
            @RequestParam(value = "simno",required = false) String  simno,
            //
            @RequestParam(value = "vehicleid",required = false) Long  vehicleid,
            //
            @RequestParam(value = "createdate",required = false) Date  startcreatedate,
            @RequestParam(value = "createdate",required = false) Date  endcreatedate,
            //
            @RequestParam(value = "sendtime",required = false) Date  startsendtime,
            @RequestParam(value = "sendtime",required = false) Date  endsendtime,
            //
            @RequestParam(value = "alarmstate",required = false) Long  alarmstate,
            //
            @RequestParam(value = "direction",required = false) Long  direction,
            //
            @RequestParam(value = "status",required = false) Long  status,
            //
            @RequestParam(value = "signalstate",required = false) Long  signalstate,
            //
            @RequestParam(value = "abnormaltype",required = false) Long  abnormaltype,
            //
            @RequestParam(value = "fromflag",required = false) Long  fromflag,
            //
            @RequestParam(value = "extendversion",required = false) Long  extendversion,
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
            PaginateResult selectmemberlist =gpsinfo_20200701service.selectlist(params, 0, 0);
            List lists = selectmemberlist.getRows();
            List exldate = new ArrayList<>();
           for (int i = 0; i < lists.size(); i++) {
             Object row=lists.get(i);
            Gpsinfo_20200701ExlVo gpsinfo_20200701 = MaptoBeanUtil.mapToBean((Map <String, Object>) row, Gpsinfo_20200701ExlVo.class);
            gpsinfo_20200701.setId(i+1);
            exldate.add(gpsinfo_20200701);
            }
            exceClasslUtil.exlport("", request, response, exldate, Gpsinfo_20200701ExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
         } catch (Exception e) {
             log.error("导出列表失败", e);
         }
    }





}