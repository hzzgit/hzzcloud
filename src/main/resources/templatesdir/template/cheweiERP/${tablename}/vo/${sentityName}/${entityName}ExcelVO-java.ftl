package ${packpath};

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.*;

import java.util.Date;

@Data
@EqualsAndHashCode
@ToString(callSuper = true)
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class ${entityName}ExcelVO   {
<#if parkquanxian??  &&parkquanxian=true>
    @ExcelProperty("车场名称")
    private String parkName;
    @ExcelProperty("运营商id")
    private String operatorId;
    @ExcelProperty("运营商名称")
    private String operatorName;
    @ExcelProperty("城市id")
    private String cityId;
    @ExcelProperty("城市名称")
    private String cityName;
</#if>
<#list tableColumnList as tablecolumn>
    @ExcelProperty("${tablecolumn.columncomment}")
    private <#if  tablecolumn.datatype=="varchar">String<#elseif  tablecolumn.datatype=="int"  >Integer<#elseif   tablecolumn.datatype=="bigint" >Long<#elseif  tablecolumn.datatype=="bit"  || tablecolumn.datatype=="tinyint" >Boolean<#elseif  tablecolumn.datatype=="decimal">Double<#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>Date<#else >String</#if>  ${tablecolumn.columnname};
</#list>


}