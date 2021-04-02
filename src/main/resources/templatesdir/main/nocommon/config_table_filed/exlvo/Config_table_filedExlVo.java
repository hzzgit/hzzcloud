package nocommon.config_table_filed.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class Config_table_filedExlVo  extends BaseRowModel  {

@ExcelProperty(value = {"序号"} )
private int id;
        /**  配置表主键  */
        @ExcelProperty(value = {"配置表主键"} )
        private Long  mainid;
        /**  删除标志  */
        @ExcelProperty(value = {"删除标志"} )
        private Long  deleted;
        /**  字段名  */
        @ExcelProperty(value = {"字段名"} )
        private String  colname;
        /**  字段显示名称  */
        @ExcelProperty(value = {"字段显示名称"} )
        private String  annotation;
        /**  创建时间  */
        @ExcelProperty(value = {"创建时间"} )
        private Date  createdate;
        /**  是否是查询条件字段,0=否，1为是  */
        @ExcelProperty(value = {"是否是查询条件字段,0=否，1为是"} )
        private Long  issearchfiled;
        /**  查询方式,1 = ，2 左like 3 右like 4 全like 5 >= 6 <= 7>=且 <=(传参需要该字段开始条件加start结束条件加end开头) 8 是否下拉框，0是否1为是  */
        @ExcelProperty(value = {"查询方式,1 = ，2 左like 3 右like 4 全like 5 >= 6 <= 7>=且 <=(传参需要该字段开始条件加start结束条件加end开头) 8 是否下拉框，0是否1为是"} )
        private Long  searchtype;
        /**  字段类型，1 字符串 2 数字 3 yyyy-mm-dd 时间 4 yyyy-mm-dd h24:mi:ss 时间  */
        @ExcelProperty(value = {"字段类型，1 字符串 2 数字 3 yyyy-mm-dd 时间 4 yyyy-mm-dd h24:mi:ss 时间"} )
        private Long  coltype;
        /**  是否展示，0为否，1为是  */
        @ExcelProperty(value = {"是否展示，0为否，1为是"} )
        private Long  isshow;
        /**  是否排序字段  */
        @ExcelProperty(value = {"是否排序字段"} )
        private Long  issort;
        /**  排序方式，0升序，1降序  */
        @ExcelProperty(value = {"排序方式，0升序，1降序"} )
        private Long  sorttype;


}