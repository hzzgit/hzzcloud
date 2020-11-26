package common.yrgpstranspond.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class YrgpstranspondExlVo  extends BaseRowModel  {

    @ExcelProperty(value = {"序号"} )
    private int id;
    /**  名称  */
    @ExcelProperty(value = {"名称"} )
    private String  name;
    /**  consumer为⽤户id由奕人提供  */
    @ExcelProperty(value = {"consumer为⽤户id由奕人提供"} )
    private String  consumer;
    /**  ak由奕人提供  */
    @ExcelProperty(value = {"ak由奕人提供"} )
    private String  ak;
    /**  转发的ip地址  */
    @ExcelProperty(value = {"转发的ip地址"} )
    private String  ip;
    /**  转发的端口号  */
    @ExcelProperty(value = {"转发的端口号"} )
    private Long  port;
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