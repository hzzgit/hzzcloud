package com.hzz.hzzcloud.freemarker.main.mapareabydep.controller;

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
import com.hzz.hzzcloud.freemarker.main.mapareabydep.entity.*;
import com.hzz.hzzcloud.freemarker.main.mapareabydep.service.*;
import com.hzz.hzzcloud.freemarker.main.mapareabydep.exlvo.*;
import java.io.BufferedReader;
import java.io.IOException;
/**
* 区域围栏和机构权限表接口层
*/
@Controller
@RequestMapping("/mapareabydep")
@Slf4j
public class MapareabydepController  extends GenericAction{

@Autowired
private MapareabydepService mapareabydepservice;

@Autowired
private EasyExceClasslUtil exceClasslUtil;


/**
* 保存区域围栏和机构权限表
*
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
        //主键
        @RequestParam(required = false) Long  id,
        //配置id
        @RequestParam(required = false) Long  configid,
        //绑定机构的id
        @RequestParam(required = false) Long  depid,
        //创建时间
        @RequestParam(required = false) Date  createdate,
        //删除标志,0代表未删除,1代表删除
        @RequestParam(required = false) Long  deleted,
@RequestBody  Mapareabydep mapareabydep, HttpServletRequest request
)
throws Exception {
mapareabydepservice.save(mapareabydep);
return new JsonMessage(true, "操作成功");
}


/**
* 删除区域围栏和机构权限表
* @param id 主键
* @return
*/
@ResponseBody
@RequestMapping("/delete.action")
public JsonMessage delete(
String id) throws Exception {
mapareabydepservice.fdelete(id);
return new JsonMessage(true, "操作成功");
}

    /**
    * 查询列表
    *   @param  id 主键
    *   @param  configid 配置id
    *   @param  depid 绑定机构的id
    *   @param  startcreatedate 创建时间开始时间
    *   @param  endcreatedate 创建时间结束时间
    *   @param  deleted 删除标志,0代表未删除,1代表删除
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
        Map params = getParams(request);
        OnlineUser onlineUser = getOnlineUser();
        if(onlineUser.isSuperAdmin()){
        params.put("owner", onlineUser.getEntityId());
        params.put("userid", onlineUser.getEntityId());
        }
        Uservehicleauthority uservehicleauthority=getUservehicleauthorityByWeb(depIds);
        quanxianUtil.getquanxian(uservehicleauthority, params);
        return mapareabydepservice.selectlist(params, page, rows);
        } catch (Exception e) {
        log.error("区域围栏和机构权限表查询列表失败", e);
        return paginateResult;
      }
    }


        /**
        * 导出列表文件
        *   @param  id 主键
        *   @param  configid 配置id
        *   @param  depid 绑定机构的id
        *   @param  startcreatedate 创建时间开始时间
        *   @param  endcreatedate 创建时间结束时间
        *   @param  deleted 删除标志,0代表未删除,1代表删除
        * @return
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
            Map params = getParams(request);
            Uservehicleauthority uservehicleauthority=getUservehicleauthorityByWeb(depIds);
            quanxianUtil.getquanxian(uservehicleauthority, params);
            PaginateResult selectmemberlist =mapareabydepservice.selectlist(params, 0, 0);
            List rows = selectmemberlist.getRows();
            List exldate = new ArrayList<>();
          for (int i = 0; i < lists.size(); i++) {
             Object row=lists.get(i);
            MapareabydepExlVo mapareabydep = MaptoBeanUtil.mapToBean((Map <String, Object>) row, MapareabydepExlVo.class);
            mapareabydep.setId(i+1);
            exldate.add(mapareabydep);
            }
            exceClasslUtil.exlport("区域围栏和机构权限表", request, response, exldate, MapareabydepExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
            } catch (Exception e) {
            log.error("区域围栏和机构权限表导出列表失败", e);
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