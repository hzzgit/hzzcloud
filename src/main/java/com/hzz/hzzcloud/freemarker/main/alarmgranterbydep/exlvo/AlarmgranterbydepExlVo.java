package com.hzz.hzzcloud.freemarker.main.alarmgranterbydep.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class AlarmgranterbydepExlVo  extends BaseRowModel  {

    @ExcelProperty(value = {"序号"} )
    private int id;
    /**  配置id  */
    @ExcelProperty(value = {"配置id"} )
    private Long  configid;
    /**  绑定机构的id  */
    @ExcelProperty(value = {"绑定机构的id"} )
    private Long  depid;
    /**  创建时间  */
    @ExcelProperty(value = {"创建时间"} )
    private Date  createdate;
    /**  删除标志,0代表未删除,1代表删除  */
    @ExcelProperty(value = {"删除标志,0代表未删除,1代表删除"} )
    private Long  deleted;


}