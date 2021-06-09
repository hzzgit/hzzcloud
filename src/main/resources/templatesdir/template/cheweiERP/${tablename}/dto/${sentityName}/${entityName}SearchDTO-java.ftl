package ${packpath};

import com.cwgj.common.model.dto.ParkCommonDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ${entityName}SearchDTO  extends ParkCommonDTO  {

<#list tableColumnList as tablecolumn>
   <#if (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>
       @ApiModelProperty("${tablecolumn.columncomment}（开始时间）")
       private Date start${tablecolumn.columnname};

       @ApiModelProperty("${tablecolumn.columncomment}（结束时间）")
       private Date end${tablecolumn.columnname};
   <#else>
       @ApiModelProperty("${tablecolumn.columncomment}")
       private <#if  tablecolumn.datatype=="varchar">String<#elseif  tablecolumn.datatype=="int" ||  tablecolumn.datatype=="bigint" >Long<#elseif  tablecolumn.datatype=="bit"  || tablecolumn.datatype=="tinyint" >Boolean<#elseif  tablecolumn.datatype=="decimal">Double<#else >String</#if>  ${tablecolumn.columnname};
   </#if>
</#list>


}