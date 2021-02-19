package nocommon.flowlimitconfig.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class FlowlimitconfigExlVo  extends BaseRowModel  {

@ExcelProperty(value = {"序号"} )
private int id;
        /**  名称  */
        @ExcelProperty(value = {"名称"} )
        private String  name;
        /**  流量控制的阈值  */
        @ExcelProperty(value = {"流量控制的阈值"} )
        private String  limitval;
        /**  有效开始时间  */
        @ExcelProperty(value = {"有效开始时间"} )
        private Date  validstarttime;
        /**  有效结束时间  */
        @ExcelProperty(value = {"有效结束时间"} )
        private Date  validendtime;
        /**  创建时间  */
        @ExcelProperty(value = {"创建时间"} )
        private Date  createdate;
        /**  修改时间  */
        @ExcelProperty(value = {"修改时间"} )
        private Date  updatedate;
        /**  用户id  */
        @ExcelProperty(value = {"用户id"} )
        private Long  userid;
        /**  是否启用,0禁用,1启用  */
        @ExcelProperty(value = {"是否启用,0禁用,1启用"} )
        private String  isuse;
        /**  删除标志,1代表删除,0代表正常  */
        @ExcelProperty(value = {"删除标志,1代表删除,0代表正常"} )
        private Long  deleted;


}