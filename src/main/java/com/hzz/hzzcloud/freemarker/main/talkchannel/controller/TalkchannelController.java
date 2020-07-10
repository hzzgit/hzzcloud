package com.hzz.hzzcloud.freemarker.main.talkchannel.controller;

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
import com.hzz.hzzcloud.freemarker.main.talkchannel.entity.*;
import com.hzz.hzzcloud.freemarker.main.talkchannel.service.*;
import com.hzz.hzzcloud.freemarker.main.talkchannel.exlvo.*;
import java.io.BufferedReader;
import java.io.IOException;
/**
* 对讲频道管理接口层
*/
@Controller
@RequestMapping("/talkchannel")
@Slf4j
public class TalkchannelController {

@Autowired
private TalkchannelService talkchannelservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;

@Autowired
private UserVehicleRefCacheService userVehicleRefCacheService;

/**
* 保存对讲频道管理
*
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  Talkchannel talkchannel, HttpServletRequest request) throws Exception {
TokenUser tuser = TokenUser.getFromRequest(request);
talkchannelservice.save(talkchannel);
return new JsonMessage(true, "操作成功");
}


    /**
    * 删除对讲频道管理
    *
    * @return
    */
    @ResponseBody
    @RequestMapping("/delete.action")
    public JsonMessage delete(String id) throws Exception {
       talkchannelservice.fdelete(id);
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
            //频道Id
            @RequestParam(required = false) String  channelid,
            //频道名称
            @RequestParam(required = false) String  channelname,
            //创建时间
            @RequestParam(required = false) Date  startcreatedate,
            @RequestParam(required = false) Date  endcreatedate,
            //成员数量
            @RequestParam(required = false) Long  membernum,
            //频道备注
            @RequestParam(required = false) String  remark,
            //创建用户id
            @RequestParam(required = false) Long  owner,
            //状态,0,正常,4禁用
            @RequestParam(required = false) Long  status,
            //类型,0频道(该字段暂时无用,预留)
            @RequestParam(required = false) Long  type,
            //0代表未删除,1以上代表删除
            @RequestParam(required = false) Long  deleted,
            //更新时间
            @RequestParam(required = false) Date  startupdatedate,
            @RequestParam(required = false) Date  endupdatedate,
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
            return talkchannelservice.selectlist(params, page, rows);
        } catch (Exception e) {
            log.error("对讲频道管理查询列表失败", e);
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
            //频道Id
            @RequestParam(value = "channelid",required = false) String  channelid,
            //频道名称
            @RequestParam(value = "channelname",required = false) String  channelname,
            //创建时间
            @RequestParam(value = "createdate",required = false) Date  startcreatedate,
            @RequestParam(value = "createdate",required = false) Date  endcreatedate,
            //成员数量
            @RequestParam(value = "membernum",required = false) Long  membernum,
            //频道备注
            @RequestParam(value = "remark",required = false) String  remark,
            //创建用户id
            @RequestParam(value = "owner",required = false) Long  owner,
            //状态,0,正常,4禁用
            @RequestParam(value = "status",required = false) Long  status,
            //类型,0频道(该字段暂时无用,预留)
            @RequestParam(value = "type",required = false) Long  type,
            //0代表未删除,1以上代表删除
            @RequestParam(value = "deleted",required = false) Long  deleted,
            //更新时间
            @RequestParam(value = "updatedate",required = false) Date  startupdatedate,
            @RequestParam(value = "updatedate",required = false) Date  endupdatedate,
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
            PaginateResult selectmemberlist =talkchannelservice.selectlist(params, 0, 0);
            List lists = selectmemberlist.getRows();
            List exldate = new ArrayList<>();
           for (int i = 0; i < lists.size(); i++) {
             Object row=lists.get(i);
            TalkchannelExlVo talkchannel = MaptoBeanUtil.mapToBean((Map <String, Object>) row, TalkchannelExlVo.class);
            talkchannel.setId(i+1);
            exldate.add(talkchannel);
            }
            exceClasslUtil.exlport("对讲频道管理", request, response, exldate, TalkchannelExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
         } catch (Exception e) {
             log.error("对讲频道管理导出列表失败", e);
         }
    }





}