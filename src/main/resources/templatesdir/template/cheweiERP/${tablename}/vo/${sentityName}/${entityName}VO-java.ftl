package ${packpath};

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.cwgj.common.annotation.TypeEnumProperty;
import com.cwgj.common.utils.excel.converter.MoneyConverter;
import com.cwgj.common.utils.excel.converter.TypeEnumConverter;
import com.cwgj.common.utils.json.MoneyLongDeserializer;
import com.cwgj.common.utils.json.MoneySerializer;
import com.cwgj.service.data.common.enums.ContractStatusEnum;
import com.cwgj.service.data.common.enums.ParkOperationalModelEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ${entityName}VO   {


<#list tableColumnList as tablecolumn>
    @ApiModelProperty("${tablecolumn.columncomment}")
    @ExcelProperty("${tablecolumn.columncomment}")
    private <#if  tablecolumn.datatype=="varchar">String<#elseif  tablecolumn.datatype=="int" ||  tablecolumn.datatype=="bigint" >Long<#elseif  tablecolumn.datatype=="bit"  || tablecolumn.datatype=="tinyint" >Boolean<#elseif  tablecolumn.datatype=="decimal">Double<#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>Date<#else >String</#if>  ${tablecolumn.columnname};
</#list>


}