package com.hzz.hzzcloud.freemarker.main.alarmgranter.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class AlarmgranterExlVo  extends BaseRowModel  {

    @ExcelProperty(value = {"序号"} )
    private int id;
    /**  规则名称  */
    @ExcelProperty(value = {"规则名称"} )
    private String  name;
    /**  报警要转发的报警类型,格式为,报警来源:报警类型;报警来源:报警类型;(例如:终端报警:超速报警;平台报警:超速报警)  */
    @ExcelProperty(value = {"报警要转发的报警类型,格式为,报警来源:报警类型;报警来源:报警类型;(例如:终端报警:超速报警;平台报警:超速报警)"} )
    private String  alarmtypes;
    /**  创建时间  */
    @ExcelProperty(value = {"创建时间"} )
    private Date  createdate;
    /**  修改时间  */
    @ExcelProperty(value = {"修改时间"} )
    private Date  updatedate;
    /**  用户id  */
    @ExcelProperty(value = {"用户id"} )
    private Long  userid;
    /**  是否启用  */
    @ExcelProperty(value = {"是否启用"} )
    private String  isuse;
    /**  删除标志,1代表删除,0代表正常  */
    @ExcelProperty(value = {"删除标志,1代表删除,0代表正常"} )
    private Long  deleted;
    /**  访问接口  */
    @ExcelProperty(value = {"访问接口"} )
    private String  url;


}