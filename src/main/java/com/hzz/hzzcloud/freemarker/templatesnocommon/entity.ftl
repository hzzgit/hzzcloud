package ${packageVo.entity};

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="${tableschema}.${tablename}")
public class ${entityName} implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
<#list tableColumnList as tablecolumn>
    public static final String F_${tablecolumn.columnname} = " ${tablecolumn.columnname}";
</#list>


<#list tableColumnList as tablecolumn>
    /**  ${tablecolumn.columncomment}  */
    <#if  tablecolumn.columnkey=="PRI">
        @DbId
    </#if>
    private <#if  tablecolumn.datatype=="varchar">String<#elseif  tablecolumn.datatype=="int" ||  tablecolumn.datatype=="bigint">Long<#elseif  tablecolumn.datatype=="decimal">Double<#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>Date<#else >String</#if>  ${tablecolumn.columnname};
</#list>


}