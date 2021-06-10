package ${packpath};

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.Date;

@Data
public class ${entityName}VO   {
<#if parkquanxian??  &&parkquanxian=true>
    @ApiModelProperty("车场名称")
    private String parkName;
    @ApiModelProperty("运营商id")
    private String operatorId;
    @ApiModelProperty("运营商名称")
    private String operatorName;
    @ApiModelProperty("城市id")
    private String cityId;
    @ApiModelProperty("城市名称")
    private String cityName;
</#if>
<#list tableColumnList as tablecolumn>
    @ApiModelProperty("${tablecolumn.columncomment}")
    private <#if  tablecolumn.datatype=="varchar">String<#elseif  tablecolumn.datatype=="int"  >Integer<#elseif   tablecolumn.datatype=="bigint" >Long<#elseif  tablecolumn.datatype=="bit"  || tablecolumn.datatype=="tinyint" >Boolean<#elseif  tablecolumn.datatype=="decimal">Double<#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>Date<#else >String</#if>  ${tablecolumn.columnname};
</#list>


}