package com.hzz.hzzcloud.freemarker.main.alarmgranter.controller;

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
import com.hzz.hzzcloud.freemarker.main.alarmgranter.entity.*;
import com.hzz.hzzcloud.freemarker.main.alarmgranter.service.*;
import com.hzz.hzzcloud.freemarker.main.alarmgranter.exlvo.*;
import java.io.BufferedReader;
import java.io.IOException;
/**
* 报警转发规则配置接口层
*/
@Controller
@RequestMapping("/alarmgranter")
@Slf4j
public class AlarmgranterController {

@Autowired
private AlarmgranterService alarmgranterservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;

@Autowired
private UserVehicleRefCacheService userVehicleRefCacheService;

/**
* 保存报警转发规则配置
*
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Alarmgranter alarmgranter, HttpServletRequest request) throws Exception {
TokenUser tuser = TokenUser.getFromRequest(request);
alarmgranterservice.save(alarmgranter);
return new JsonMessage(true, "操作成功");
}


    /**
    * 删除报警转发规则配置
    *
    * @return
    */
    @ResponseBody
    @RequestMapping("/delete.action")
    public JsonMessage delete(String id) throws Exception {
       alarmgranterservice.fdelete(id);
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
            //主键
            @RequestParam(required = false) Long  id,
            //规则名称
            @RequestParam(required = false) String  name,
            //创建时间
            @RequestParam(required = false) Date  startcreatedate,
            @RequestParam(required = false) Date  endcreatedate,
            //修改时间
            @RequestParam(required = false) Date  startupdatedate,
            @RequestParam(required = false) Date  endupdatedate,
            //用户id
            @RequestParam(required = false) Long  userid,
            //删除标志,1代表删除,0代表正常
            @RequestParam(required = false) Long  deleted,
            //访问接口
            @RequestParam(required = false) String  url,
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
            return alarmgranterservice.selectlist(params, page, rows);
        } catch (Exception e) {
            log.error("报警转发规则配置查询列表失败", e);
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
            //主键
            @RequestParam(value = "id",required = false) Long  id,
            //规则名称
            @RequestParam(value = "name",required = false) String  name,
            //创建时间
            @RequestParam(value = "createdate",required = false) Date  startcreatedate,
            @RequestParam(value = "createdate",required = false) Date  endcreatedate,
            //修改时间
            @RequestParam(value = "updatedate",required = false) Date  startupdatedate,
            @RequestParam(value = "updatedate",required = false) Date  endupdatedate,
            //用户id
            @RequestParam(value = "userid",required = false) Long  userid,
            //删除标志,1代表删除,0代表正常
            @RequestParam(value = "deleted",required = false) Long  deleted,
            //访问接口
            @RequestParam(value = "url",required = false) String  url,
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
            PaginateResult selectmemberlist =alarmgranterservice.selectlist(params, 0, 0);
            List lists = selectmemberlist.getRows();
            List exldate = new ArrayList<>();
           for (int i = 0; i < lists.size(); i++) {
             Object row=lists.get(i);
            AlarmgranterExlVo alarmgranter = MaptoBeanUtil.mapToBean((Map <String, Object>) row, AlarmgranterExlVo.class);
            alarmgranter.setId(i+1);
            exldate.add(alarmgranter);
            }
            exceClasslUtil.exlport("报警转发规则配置", request, response, exldate, AlarmgranterExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
         } catch (Exception e) {
             log.error("报警转发规则配置导出列表失败", e);
         }
    }





}