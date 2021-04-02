package nocommon.config_table.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class Config_tableExlVo  extends BaseRowModel  {

@ExcelProperty(value = {"序号"} )
private int id;
        /**  数据库名  */
        @ExcelProperty(value = {"数据库名"} )
        private String  database;
        /**  表名  */
        @ExcelProperty(value = {"表名"} )
        private String  tablename;
        /**  删除标志  */
        @ExcelProperty(value = {"删除标志"} )
        private Long  deleted;
        /**  创建时间  */
        @ExcelProperty(value = {"创建时间"} )
        private Date  createdate;
        /**  修改时间  */
        @ExcelProperty(value = {"修改时间"} )
        private Date  updatedate;


}