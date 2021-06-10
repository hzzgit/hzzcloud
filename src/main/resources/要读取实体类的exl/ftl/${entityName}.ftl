

@Data
public class ${entityName}  {


<#list readExlToBeanList as tablecolumn>
    <#if tablecolumn.required=="必填">
    <#if  tablecolumn.type=="string" || tablecolumn.type=="String">@NotEmpty(message = "${tablecolumn.name}不能为空")<#else >@NotNull(message = "${tablecolumn.name}不能为空")</#if>
    </#if>
    @ApiModelProperty("${tablecolumn.name} ${tablecolumn.annotion}")
    private <#if  tablecolumn.type=="string" || tablecolumn.type=="String">String<#elseif  tablecolumn.type=="int"  >Integer<#elseif  tablecolumn.type=="boolean" || tablecolumn.type=="Boolean" >Boolean<#elseif  tablecolumn.type=="long" || tablecolumn.type=="Long">Long<#elseif tablecolumn.type=="date"  || tablecolumn.type=="Date" >Date<#else >String</#if>  ${tablecolumn.colname};
</#list>


}

