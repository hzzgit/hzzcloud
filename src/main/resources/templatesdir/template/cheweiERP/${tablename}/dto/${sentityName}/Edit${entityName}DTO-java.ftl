package ${packpath};

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Edit${entityName}DTO  {

<#list tableColumnList as tablecolumn>
<#if  tablecolumn.columnname !="createId"
    && tablecolumn.columnname !="createName" && tablecolumn.columnname !="createTime"
    && tablecolumn.columnname !="updateTime" && tablecolumn.columnname !="handlerId"
    && tablecolumn.columnname !="handlerName" >
    <#if tablecolumn.isnull=="NO">
    <#if  tablecolumn.datatype=="varchar" || tablecolumn.datatype=="char">@NotEmpty<#else >@NotNull</#if>
    </#if>
    @ApiModelProperty("${tablecolumn.columncomment}")
    private <#if  tablecolumn.datatype=="varchar">String<#elseif  tablecolumn.datatype=="int"  >Integer<#elseif   tablecolumn.datatype=="bigint" >Long<#elseif  tablecolumn.datatype=="bit"  || tablecolumn.datatype=="tinyint" >Boolean<#elseif  tablecolumn.datatype=="decimal">Double<#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>Date<#else >String</#if>  ${tablecolumn.columnname};
</#if>
</#list>

}