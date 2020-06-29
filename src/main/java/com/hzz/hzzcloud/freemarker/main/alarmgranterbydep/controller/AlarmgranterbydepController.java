package com.hzz.hzzcloud.freemarker.main.alarmgranterbydep.controller;

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
import com.hzz.hzzcloud.freemarker.main.alarmgranterbydep.entity.*;
import com.hzz.hzzcloud.freemarker.main.alarmgranterbydep.service.*;
import com.hzz.hzzcloud.freemarker.main.alarmgranterbydep.exlvo.*;
import java.io.BufferedReader;
import java.io.IOException;
/**
* 报警转发规则和机构权限表接口层
*/
@Controller
@RequestMapping("/alarmgranterbydep")
@Slf4j
public class AlarmgranterbydepController {

@Autowired
private AlarmgranterbydepService alarmgranterbydepservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;

@Autowired
private UserVehicleRefCacheService userVehicleRefCacheService;

/**
* 保存报警转发规则和机构权限表
*
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Alarmgranterbydep alarmgranterbydep, HttpServletRequest request) throws Exception {
TokenUser tuser = TokenUser.getFromRequest(request);
alarmgranterbydepservice.save(alarmgranterbydep);
return new JsonMessage(true, "操作成功");
}


    /**
    * 删除报警转发规则和机构权限表
    *
    * @return
    */
    @ResponseBody
    @RequestMapping("/delete.action")
    public JsonMessage delete(String id) throws Exception {
       alarmgranterbydepservice.fdelete(id);
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
            //配置id
            @RequestParam(required = false) Long  configid,
            //绑定机构的id
            @RequestParam(required = false) Long  depid,
            //创建时间
            @RequestParam(required = false) Date  startcreatedate,
            @RequestParam(required = false) Date  endcreatedate,
            //删除标志,0代表未删除,1代表删除
            @RequestParam(required = false) Long  deleted,
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
            return alarmgranterbydepservice.selectlist(params, page, rows);
        } catch (Exception e) {
            log.error("报警转发规则和机构权限表查询列表失败", e);
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
            //配置id
            @RequestParam(value = "configid",required = false) Long  configid,
            //绑定机构的id
            @RequestParam(value = "depid",required = false) Long  depid,
            //创建时间
            @RequestParam(value = "createdate",required = false) Date  startcreatedate,
            @RequestParam(value = "createdate",required = false) Date  endcreatedate,
            //删除标志,0代表未删除,1代表删除
            @RequestParam(value = "deleted",required = false) Long  deleted,
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
            PaginateResult selectmemberlist =alarmgranterbydepservice.selectlist(params, 0, 0);
            List lists = selectmemberlist.getRows();
            List exldate = new ArrayList<>();
           for (int i = 0; i < lists.size(); i++) {
             Object row=lists.get(i);
            AlarmgranterbydepExlVo alarmgranterbydep = MaptoBeanUtil.mapToBean((Map <String, Object>) row, AlarmgranterbydepExlVo.class);
            alarmgranterbydep.setId(i+1);
            exldate.add(alarmgranterbydep);
            }
            exceClasslUtil.exlport("报警转发规则和机构权限表", request, response, exldate, AlarmgranterbydepExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
         } catch (Exception e) {
             log.error("报警转发规则和机构权限表导出列表失败", e);
         }
    }





}