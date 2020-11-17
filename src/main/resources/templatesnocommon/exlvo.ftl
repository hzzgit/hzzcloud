package ${packageVo.exlvo};

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class ${entityName}ExlVo  extends BaseRowModel  {

    @ExcelProperty(value = {"序号"} )
    private int id;
<#list tableColumnList as tablecolumn>
    <#if tablecolumn.columnkey !="PRI" >
    /**  ${tablecolumn.columncomment}  */
    @ExcelProperty(value = {"${tablecolumn.columncomment}"} )
    private <#if  tablecolumn.datatype=="varchar">String<#elseif  tablecolumn.datatype=="int" ||  tablecolumn.datatype=="bigint">Long<#elseif  tablecolumn.datatype=="decimal">Double<#elseif (tablecolumn.datatype=="date" || tablecolumn.datatype=="datetime" )>Date<#else >String</#if>  ${tablecolumn.columnname?lower_case};
    </#if>
</#list>


}