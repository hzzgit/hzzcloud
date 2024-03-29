package ${packageVo.controller};

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import net.fxft.ascswebcommon.online.TokenUser;
import net.fxft.ascswebcommon.util.easyexcel.EasyExceClasslUtil;
import net.fxft.ascswebcommon.vo.PaginateResult;
import net.fxft.ascswebcommon.web.util.JsonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import net.fxft.ascswebcommon.service.impl.UserVehicleRefCacheService;
import net.fxft.ascswebcommon.vo.UserVehicleAuthority;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
/**
* ${tableconment}接口层
*/
@Controller
@RequestMapping("/${tablename}")
@Slf4j
public class ${entityName}Controller {

@Autowired
private ${entityName}Service ${tablename}service;

@Autowired
private EasyExceClasslUtil exceClasslUtil;

@Autowired
private UserVehicleRefCacheService userVehicleRefCacheService;

/**
* 保存${tableconment},使用json请求
* @return
*/
@ResponseBody
@RequestMapping("/save.action")
public JsonMessage save(
@RequestBody  ${entityName} ${tablename}, HttpServletRequest request
) throws Exception {
TokenUser tuser = TokenUser.getFromRequest(request);
${tablename}service.save(${tablename});
return new JsonMessage(true, "操作成功");
}


    /**
    * 删除${tableconment}
    * @param id 主键
    * @return
    */
    @ResponseBody
    @RequestMapping("/delete.action")
    public JsonMessage delete(String id) throws Exception {
       ${tablename}service.fdelete(id);
        return new JsonMessage(true, "操作成功");
    }

    /**
    * 查询列表
<#list tableColumnList as tablecolumn>
    <#if  tablecolumn.datatype=="varchar">
    *   @param  ${tablecolumn.columnname} ${tablecolumn.columncomment}
    <#elseif  tablecolumn.datatype=="int">
    *   @param  ${tablecolumn.columnname} ${tablecolumn.columncomment}
    <#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>
    *   @param  start${tablecolumn.columnname} ${tablecolumn.columncomment}开始时间
    *   @param  end${tablecolumn.columnname} ${tablecolumn.columncomment}结束时间
    </#if>
</#list>
    * @return
    */
    @ResponseBody
    @RequestMapping("/selectlist.action")
    public PaginateResult selectlist(
    @RequestParam(value = "page", defaultValue = "1") int page,
    @RequestParam(value = "rows", defaultValue = "10") int rows,
    @RequestParam(required = false) Long[] depIds,
    <#list tableColumnList as tablecolumn>
        <#if  tablecolumn.datatype=="varchar">
            /**
            *${tablecolumn.columncomment}
            */
            @RequestParam(required = false) String  ${tablecolumn.columnname},
        <#elseif  tablecolumn.datatype=="int">
            /**
            *${tablecolumn.columncomment}
            */
            @RequestParam(required = false) Long  ${tablecolumn.columnname},
        <#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>
            /**
            *${tablecolumn.columncomment}
            */
            @RequestParam(required = false) Date  start${tablecolumn.columnname},
            @RequestParam(required = false) Date  end${tablecolumn.columnname},
        </#if>
    </#list>
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
            return ${tablename}service.selectlist(params, page, rows);
        } catch (Exception e) {
            log.error("${tableconment}查询列表失败", e);
            return paginateResult;
        }
    }


    /**
    * 导出列表文件
<#list tableColumnList as tablecolumn>
    <#if  tablecolumn.datatype=="varchar">
        *   @param  ${tablecolumn.columnname} ${tablecolumn.columncomment}
    <#elseif  tablecolumn.datatype=="int">
        *   @param  ${tablecolumn.columnname} ${tablecolumn.columncomment}
    <#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>
        *   @param  start${tablecolumn.columnname} ${tablecolumn.columncomment}开始时间
        *   @param  end${tablecolumn.columnname} ${tablecolumn.columncomment}结束时间
    </#if>
</#list>
    * @return
    */
    @ResponseBody
    @RequestMapping("/exportlist.action")
    public void exportlist(
    @RequestParam(required = false) Long[] depIds,
    <#list tableColumnList as tablecolumn>
        <#if  tablecolumn.datatype=="varchar">
            /**
            *${tablecolumn.columncomment}
            */
            @RequestParam(value = "${tablecolumn.columnname}",required = false) String  ${tablecolumn.columnname},
        <#elseif  tablecolumn.datatype=="int">
            /**
            *${tablecolumn.columncomment}
            */
            @RequestParam(value = "${tablecolumn.columnname}",required = false) Long  ${tablecolumn.columnname},
        <#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>
            /**
            *${tablecolumn.columncomment}
            */
            @RequestParam(value = "${tablecolumn.columnname}",required = false) Date  start${tablecolumn.columnname},
            @RequestParam(value = "${tablecolumn.columnname}",required = false) Date  end${tablecolumn.columnname},
        </#if>
    </#list>
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
            PaginateResult selectmemberlist =${tablename}service.selectlist(params, 0, 0);
            List lists = selectmemberlist.getRows();
            List exldate = new ArrayList<>();
           for (int i = 0; i < lists.size(); i++) {
             Object row=lists.get(i);
            ${entityName}ExlVo ${tablename} = MaptoBeanUtil.mapToBean((Map <String, Object>) row, ${entityName}ExlVo.class);
            ${tablename}.setId(i+1);
            exldate.add(${tablename});
            }
            exceClasslUtil.exlport("${tableconment}", request, response, exldate, ${entityName}ExlVo.class);//调用easyexl模版进行导出exl特定标题的数据
         } catch (Exception e) {
             log.error("${tableconment}导出列表失败", e);
         }
    }





}