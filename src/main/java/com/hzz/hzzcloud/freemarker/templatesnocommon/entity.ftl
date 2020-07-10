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
    /**  ${tablecolumn.columncomment}  */
    <#if  tablecolumn.columnkey=="PRI">
        @DbId
    </#if>
    private <#if  tablecolumn.datatype=="varchar">String<#elseif  tablecolumn.datatype=="int" ||  tablecolumn.datatype=="bigint" >Long<#elseif  tablecolumn.datatype=="bit"  >Boolean<#elseif  tablecolumn.datatype=="decimal">Double<#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>Date<#else >String</#if>  ${tablecolumn.columnname?lower_case};
</#list>

public static void main(String[] args) {
String name="{\n";
<#list tableColumnList as tablecolumn>
    name +="  \"${tablecolumn.columnname?lower_case}\":<#if  tablecolumn.datatype=="varchar">\"\"<#elseif  tablecolumn.datatype=="int" ||  tablecolumn.datatype=="bigint" >0<#elseif  tablecolumn.datatype=="bit"  >false<#elseif  tablecolumn.datatype=="decimal">0.0<#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>\"2020-09-11 00:00:00\"<#else >\"\"</#if><#if tablecolumn_has_next>,</#if> //${tablecolumn.columncomment}\n";
</#list>
name+="}";
System.out.println(name);

}

}