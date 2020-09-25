package com.hzz.hzzcloud.freemarker.main.mapareauserlim.controller;

import com.alibaba.fastjson.JSON;
import com.hzz.hzzcloud.jdbcutil.util.ConverterUtils;
import lombok.extern.slf4j.Slf4j;
import net.fxft.ascswebcommon.online.TokenUser;
import net.fxft.ascswebcommon.util.easyexcel.EasyExceClasslUtil;
import net.fxft.ascswebcommon.vo.PaginateResult;
import net.fxft.ascswebcommon.web.util.JsonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.hzz.hzzcloud.freemarker.util.RequestUtil;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.hzz.hzzcloud.freemarker.util.MaptoBeanUtil;
import com.hzz.hzzcloud.freemarker.util.quanxianUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import com.hzz.hzzcloud.freemarker.main.mapareauserlim.entity.*;
import com.hzz.hzzcloud.freemarker.main.mapareauserlim.service.*;
import com.hzz.hzzcloud.freemarker.main.mapareauserlim.exlvo.*;
import java.io.BufferedReader;
import java.io.IOException;
/**
* 区域围栏的授权用户可见性配置表接口层
*/
@Controller
@RequestMapping("/mapareauserlim")
@Slf4j
public class MapareauserlimController  extends GenericAction{

@Autowired
private MapareauserlimService mapareauserlimservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;


/**
* 保存区域围栏的授权用户可见性配置表
*
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
        //
        @RequestParam(required = false) Long  id,
        //转发配置表的主键
        @RequestParam(required = false) Long  configid,
        //用户id
        @RequestParam(required = false) Long  userid,
@RequestBody  Mapareauserlim mapareauserlim, HttpServletRequest request
)
throws Exception {
mapareauserlimservice.save(mapareauserlim);
return new JsonMessage(true, "操作成功");
}


/**
* 删除区域围栏的授权用户可见性配置表
* @param id 主键
* @return
*/
@ResponseBody
@RequestMapping("/delete.action")
public JsonMessage delete(
String id) throws Exception {
mapareauserlimservice.fdelete(id);
return new JsonMessage(true, "操作成功");
}

    /**
    * 查询列表
    *   @param  id 
    *   @param  configid 转发配置表的主键
    *   @param  userid 用户id
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
            //转发配置表的主键
            @RequestParam(required = false) Long  configid,
            //用户id
            @RequestParam(required = false) Long  userid,
    HttpServletRequest request
    ) {
        PaginateResult paginateResult = new PaginateResult(500, "服务器错误");
        try {
        Map params = getParams(request);
        OnlineUser onlineUser = getOnlineUser();
        if(onlineUser.isSuperAdmin()){
        params.put("owner", onlineUser.getEntityId());
        params.put("userid", onlineUser.getEntityId());
        }
        Uservehicleauthority uservehicleauthority=getUservehicleauthorityByWeb(depIds);
        quanxianUtil.getquanxian(uservehicleauthority, params);
        return mapareauserlimservice.selectlist(params, page, rows);
        } catch (Exception e) {
        log.error("区域围栏的授权用户可见性配置表查询列表失败", e);
        return paginateResult;
      }
    }


        /**
        * 导出列表文件
        *   @param  id 
        *   @param  configid 转发配置表的主键
        *   @param  userid 用户id
        * @return
        */
        @ResponseBody
        @RequestMapping("/exportlist.action")
        public void exportlist(
        @RequestParam(required = false) Long[] depIds,
                //
                @RequestParam(value = "id",required = false) Long  id,
                //转发配置表的主键
                @RequestParam(value = "configid",required = false) Long  configid,
                //用户id
                @RequestParam(value = "userid",required = false) Long  userid,
        HttpServletRequest request,
        HttpServletResponse response
        ) {
            try {
            Map params = getParams(request);
            Uservehicleauthority uservehicleauthority=getUservehicleauthorityByWeb(depIds);
            quanxianUtil.getquanxian(uservehicleauthority, params);
            PaginateResult selectmemberlist =mapareauserlimservice.selectlist(params, 0, 0);
            List rows = selectmemberlist.getRows();
            List exldate = new ArrayList<>();
          for (int i = 0; i < lists.size(); i++) {
             Object row=lists.get(i);
            MapareauserlimExlVo mapareauserlim = MaptoBeanUtil.mapToBean((Map <String, Object>) row, MapareauserlimExlVo.class);
            mapareauserlim.setId(i+1);
            exldate.add(mapareauserlim);
            }
            exceClasslUtil.exlport("区域围栏的授权用户可见性配置表", request, response, exldate, MapareauserlimExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
            } catch (Exception e) {
            log.error("区域围栏的授权用户可见性配置表导出列表失败", e);
            }
        }


        private  Map getParams(HttpServletRequest request) {
                Map parameters = request.getParameterMap();
                Map paraMap = new HashMap();
                String rowdata = "";
                if (parameters.size() > 0) {
                for (Object key : parameters.keySet()) {
                String strKey = "" + key;
                int index = strKey.indexOf("[]");
                if (index > 0) {
                strKey = strKey.replaceAll("\\[\\]", "");
                }
                String[] values = (String[]) parameters.get(key);
                if (values.length == 1) {
                String strValue = values[0];
                if (strValue != null && strValue.isEmpty() == false)
                paraMap.put(strKey, strValue);
                } else
                paraMap.put(strKey, values);
                }
                } else {
                BufferedReader br = null;

                try {
                br = request.getReader();
                String line = br.readLine();
                if (line == null) {

                } else {
                StringBuilder ret = new StringBuilder();
                ret.append(line);

                while ((line = br.readLine()) != null) {
                ret.append('\n').append(line);
                }

                rowdata = ret.toString();
                }
                paraMap = (Map) JSON.parse(rowdata);
                } catch (IOException var4) {
                throw new RuntimeException(var4);
                }
                }
                return paraMap;
        }




}