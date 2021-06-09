package ${packpath};

import com.cwgj.common.model.dto.ParkCommonDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Create${entityName}DTO  {

<#list tableColumnList as tablecolumn>
    <#if tablecolumn.columnkey  !="PRI">
    <#if  tablecolumn.datatype=="varchar">@NotEmpty<#elseif  tablecolumn.datatype=="int" ||  tablecolumn.datatype=="bigint" >@NotNull<#elseif  tablecolumn.datatype=="decimal">@NotNull<#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>@NotNull<#else >@NotNull</#if>
    @ApiModelProperty("${tablecolumn.columncomment}")
    private <#if  tablecolumn.datatype=="varchar">String<#elseif  tablecolumn.datatype=="int" ||  tablecolumn.datatype=="bigint" >Long<#elseif  tablecolumn.datatype=="bit"  || tablecolumn.datatype=="tinyint" >Boolean<#elseif  tablecolumn.datatype=="decimal">Double<#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>Date<#else >String</#if>  ${tablecolumn.columnname};
        </#if>
</#list>

}