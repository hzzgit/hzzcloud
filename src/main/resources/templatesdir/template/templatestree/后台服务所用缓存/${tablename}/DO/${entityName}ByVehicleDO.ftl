package ${packpath};

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
public class ${entityName}ByVehicleDO implements java.io.Serializable  {
    /*simNO*/
    private String simNo;

    /*车牌号*/
    private String plateNo;

<#list tableColumnList as tablecolumn>
    /**  ${tablecolumn.columncomment}  */
    private <#if  tablecolumn.datatype=="varchar">String<#elseif  tablecolumn.datatype=="int" ||  tablecolumn.datatype=="bigint" >Long<#elseif  tablecolumn.datatype=="bit"  >Boolean<#elseif  tablecolumn.datatype=="decimal">Double<#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>Date<#else >String</#if>  ${tablecolumn.columnname?lower_case};
</#list>

}