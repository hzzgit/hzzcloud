package ${packpath};

import com.cwgj.common.model.dto.ParkCommonDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@Data
public class ${entityName}SearchDTO <#if parkquanxian??  &&parkquanxian=true> extends ParkCommonDTO </#if> {


<#if parkquanxian??  &&parkquanxian=true>
       @ApiModelProperty("运营商ID")
       private Long operatorId;
</#if>
<#list tableColumnList as tablecolumn>
    <#if tablecolumn.columnname!="parkGuid">
   <#if (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>
       @ApiModelProperty("${tablecolumn.columncomment}（开始时间）")
       private String start${tablecolumn.columnname};
       @ApiModelProperty("${tablecolumn.columncomment}（结束时间）")
       private String end${tablecolumn.columnname};
   <#else>
       @ApiModelProperty("${tablecolumn.columncomment}")
       private <#if  tablecolumn.datatype=="varchar">String<#elseif  tablecolumn.datatype=="int" ||  tablecolumn.datatype=="bigint" >Long<#elseif  tablecolumn.datatype=="bit"  || tablecolumn.datatype=="tinyint" >Boolean<#elseif  tablecolumn.datatype=="decimal">Double<#else >String</#if>  ${tablecolumn.columnname};
   </#if>
   </#if>
</#list>



}