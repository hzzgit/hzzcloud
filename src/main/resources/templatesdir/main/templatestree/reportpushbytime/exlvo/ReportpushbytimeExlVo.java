package templatestree.reportpushbytime.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class ReportpushbytimeExlVo  extends BaseRowModel  {

@ExcelProperty(value = {"序号"} )
private int id;
        /**  中文名  */
        @ExcelProperty(value = {"中文名"} )
        private String  username;
        /**  英文名  */
        @ExcelProperty(value = {"英文名"} )
        private Long  userid;
        /**  开始时间，yyyy-mm  */
        @ExcelProperty(value = {"开始时间，yyyy-mm"} )
        private String  starttime;
        /**  结束时间,yyyy-mm  */
        @ExcelProperty(value = {"结束时间,yyyy-mm"} )
        private String  endtime;
        /**  创建时间  */
        @ExcelProperty(value = {"创建时间"} )
        private Date  createdate;
        /**  删除标志  */
        @ExcelProperty(value = {"删除标志"} )
        private Long  deleted;
        /**  登录名  */
        @ExcelProperty(value = {"登录名"} )
        private String  loginname;
        /**  车辆主键  */
        @ExcelProperty(value = {"车辆主键"} )
        private Long  vehicleid;
        /**  车牌号  */
        @ExcelProperty(value = {"车牌号"} )
        private String  plateno;


}