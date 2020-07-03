package com.hzz.hzzcloud.freemarker.main.gpstransferrepairlog.controller;

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
import com.hzz.hzzcloud.freemarker.main.gpstransferrepairlog.entity.*;
import com.hzz.hzzcloud.freemarker.main.gpstransferrepairlog.service.*;
import com.hzz.hzzcloud.freemarker.main.gpstransferrepairlog.exlvo.*;
import java.io.BufferedReader;
import java.io.IOException;
/**
* 历史轨迹转发补传日志表接口层
*/
@Controller
@RequestMapping("/gpstransferrepairlog")
@Slf4j
public class GpstransferrepairlogController {

@Autowired
private GpstransferrepairlogService gpstransferrepairlogservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;

@Autowired
private UserVehicleRefCacheService userVehicleRefCacheService;

/**
* 保存历史轨迹转发补传日志表
*
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Gpstransferrepairlog gpstransferrepairlog, HttpServletRequest request) throws Exception {
TokenUser tuser = TokenUser.getFromRequest(request);
gpstransferrepairlogservice.save(gpstransferrepairlog);
return new JsonMessage(true, "操作成功");
}


    /**
    * 删除历史轨迹转发补传日志表
    *
    * @return
    */
    @ResponseBody
    @RequestMapping("/delete.action")
    public JsonMessage delete(String id) throws Exception {
       gpstransferrepairlogservice.fdelete(id);
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
            @RequestParam(required = false) Long  id,
            //开始时间
            @RequestParam(required = false) Date  startstarttime,
            @RequestParam(required = false) Date  endstarttime,
            //创建时间
            @RequestParam(required = false) Date  startcreatedate,
            @RequestParam(required = false) Date  endcreatedate,
            //结束时间
            @RequestParam(required = false) Date  startendtime,
            @RequestParam(required = false) Date  endendtime,
            //处理结果
            @RequestParam(required = false) String  msg,
            //请求url
            @RequestParam(required = false) String  url,
            //处理车辆数
            @RequestParam(required = false) Long  veco,
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
            return gpstransferrepairlogservice.selectlist(params, page, rows);
        } catch (Exception e) {
            log.error("历史轨迹转发补传日志表查询列表失败", e);
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
            @RequestParam(value = "id",required = false) Long  id,
            //开始时间
            @RequestParam(value = "starttime",required = false) Date  startstarttime,
            @RequestParam(value = "starttime",required = false) Date  endstarttime,
            //创建时间
            @RequestParam(value = "createdate",required = false) Date  startcreatedate,
            @RequestParam(value = "createdate",required = false) Date  endcreatedate,
            //结束时间
            @RequestParam(value = "endtime",required = false) Date  startendtime,
            @RequestParam(value = "endtime",required = false) Date  endendtime,
            //处理结果
            @RequestParam(value = "msg",required = false) String  msg,
            //请求url
            @RequestParam(value = "url",required = false) String  url,
            //处理车辆数
            @RequestParam(value = "veco",required = false) Long  veco,
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
            PaginateResult selectmemberlist =gpstransferrepairlogservice.selectlist(params, 0, 0);
            List lists = selectmemberlist.getRows();
            List exldate = new ArrayList<>();
           for (int i = 0; i < lists.size(); i++) {
             Object row=lists.get(i);
            GpstransferrepairlogExlVo gpstransferrepairlog = MaptoBeanUtil.mapToBean((Map <String, Object>) row, GpstransferrepairlogExlVo.class);
            gpstransferrepairlog.setId(i+1);
            exldate.add(gpstransferrepairlog);
            }
            exceClasslUtil.exlport("历史轨迹转发补传日志表", request, response, exldate, GpstransferrepairlogExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
         } catch (Exception e) {
             log.error("历史轨迹转发补传日志表导出列表失败", e);
         }
    }





}