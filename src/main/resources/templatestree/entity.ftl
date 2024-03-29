package ${packageVo.entity};

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="${tableschema}.${tablename}")
public class ${entityName} implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
<#list tableColumnList as tablecolumn>
    public static final String F_${tablecolumn.columnname?lower_case} = "${tablecolumn.columnname?lower_case}";
</#list>


<#list tableColumnList as tablecolumn>
    /**  ${tablecolumn.columncomment}修改则传列表中的  */
    <#if  tablecolumn.columnkey=="PRI">
        @DbId
    </#if>
    private <#if  tablecolumn.datatype=="varchar">String<#elseif  tablecolumn.datatype=="int" ||  tablecolumn.datatype=="bigint" >long<#elseif  tablecolumn.datatype=="bit"  >Boolean<#elseif  tablecolumn.datatype=="decimal">Double<#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>Date<#else >String</#if>  ${tablecolumn.columnname?lower_case};
</#list>

public static void main(String[] args) {
String name="{\n";
<#list tableColumnList as tablecolumn>
    <#if tablecolumn.columnname?lower_case!="createdate"  &&  tablecolumn.columnname?lower_case!="updatedate" &&  tablecolumn.columnname?lower_case!="userid" &&  tablecolumn.columnname?lower_case!="deleted">
    name +="  \"${tablecolumn.columnname?lower_case}\":<#if  tablecolumn.datatype=="varchar">\"测试\"<#elseif  tablecolumn.datatype=="int" ||  tablecolumn.datatype=="bigint" >1<#elseif  tablecolumn.datatype=="bit"  >false<#elseif  tablecolumn.datatype=="decimal">0.0<#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>\"2020-09-11 00:00:00\"<#else >\"\"</#if><#if tablecolumn_has_next>,</#if> //${tablecolumn.columncomment}\n";
    </#if>
</#list>
name+="}";
System.out.println(name);
name ="    {\"success\":true, \n "+
" \"code\":200,\n " +
" \"message\":\"success\", \n " +
" \"data\": " +name+
", \n \"total\":1} ";
System.out.println(name);
}

}