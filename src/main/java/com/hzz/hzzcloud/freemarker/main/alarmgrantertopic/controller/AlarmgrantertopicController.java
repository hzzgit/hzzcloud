package com.hzz.hzzcloud.freemarker.main.alarmgrantertopic.controller;

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
import com.hzz.hzzcloud.freemarker.main.alarmgrantertopic.entity.*;
import com.hzz.hzzcloud.freemarker.main.alarmgrantertopic.service.*;
import com.hzz.hzzcloud.freemarker.main.alarmgrantertopic.exlvo.*;
import java.io.BufferedReader;
import java.io.IOException;
/**
* 报警转发申请开通表,用于存放公司名和topic接口层
*/
@Controller
@RequestMapping("/alarmgrantertopic")
@Slf4j
public class AlarmgrantertopicController {

@Autowired
private AlarmgrantertopicService alarmgrantertopicservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;

@Autowired
private UserVehicleRefCacheService userVehicleRefCacheService;

/**
* 保存报警转发申请开通表,用于存放公司名和topic
*
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Alarmgrantertopic alarmgrantertopic, HttpServletRequest request) throws Exception {
TokenUser tuser = TokenUser.getFromRequest(request);
alarmgrantertopicservice.save(alarmgrantertopic);
return new JsonMessage(true, "操作成功");
}


    /**
    * 删除报警转发申请开通表,用于存放公司名和topic
    *
    * @return
    */
    @ResponseBody
    @RequestMapping("/delete.action")
    public JsonMessage delete(String id) throws Exception {
       alarmgrantertopicservice.fdelete(id);
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
            //公司名称
            @RequestParam(required = false) String  name,
            //topic名称,这边要在新增的时候,自动生成一个字符串.唯一字符串.可以使用uuid
            @RequestParam(required = false) String  topicname,
            //创建时间
            @RequestParam(required = false) Date  startcreatedate,
            @RequestParam(required = false) Date  endcreatedate,
            //修改时间
            @RequestParam(required = false) Date  startupdatedate,
            @RequestParam(required = false) Date  endupdatedate,
            //申请转发开通的时候选择的机构id,唯一的,只能有一条有这个机构
            @RequestParam(required = false) Long  depid,
            //申请转发开通的时候选择的用户ID,用户已经申请转发开通之后,不能再进行申请
            @RequestParam(required = false) Long  userid,
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
            return alarmgrantertopicservice.selectlist(params, page, rows);
        } catch (Exception e) {
            log.error("报警转发申请开通表,用于存放公司名和topic查询列表失败", e);
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
            //公司名称
            @RequestParam(value = "name",required = false) String  name,
            //topic名称,这边要在新增的时候,自动生成一个字符串.唯一字符串.可以使用uuid
            @RequestParam(value = "topicname",required = false) String  topicname,
            //创建时间
            @RequestParam(value = "createdate",required = false) Date  startcreatedate,
            @RequestParam(value = "createdate",required = false) Date  endcreatedate,
            //修改时间
            @RequestParam(value = "updatedate",required = false) Date  startupdatedate,
            @RequestParam(value = "updatedate",required = false) Date  endupdatedate,
            //申请转发开通的时候选择的机构id,唯一的,只能有一条有这个机构
            @RequestParam(value = "depid",required = false) Long  depid,
            //申请转发开通的时候选择的用户ID,用户已经申请转发开通之后,不能再进行申请
            @RequestParam(value = "userid",required = false) Long  userid,
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
            PaginateResult selectmemberlist =alarmgrantertopicservice.selectlist(params, 0, 0);
            List lists = selectmemberlist.getRows();
            List exldate = new ArrayList<>();
           for (int i = 0; i < lists.size(); i++) {
             Object row=lists.get(i);
            AlarmgrantertopicExlVo alarmgrantertopic = MaptoBeanUtil.mapToBean((Map <String, Object>) row, AlarmgrantertopicExlVo.class);
            alarmgrantertopic.setId(i+1);
            exldate.add(alarmgrantertopic);
            }
            exceClasslUtil.exlport("报警转发申请开通表,用于存放公司名和topic", request, response, exldate, AlarmgrantertopicExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
         } catch (Exception e) {
             log.error("报警转发申请开通表,用于存放公司名和topic导出列表失败", e);
         }
    }





}