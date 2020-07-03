package com.hzz.hzzcloud.freemarker.main.gpstransferrepairlog.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class GpstransferrepairlogExlVo  extends BaseRowModel  {

    @ExcelProperty(value = {"序号"} )
    private int id;
    /**  开始时间  */
    @ExcelProperty(value = {"开始时间"} )
    private Date  starttime;
    /**  创建时间  */
    @ExcelProperty(value = {"创建时间"} )
    private Date  createdate;
    /**  结束时间  */
    @ExcelProperty(value = {"结束时间"} )
    private Date  endtime;
    /**  处理结果  */
    @ExcelProperty(value = {"处理结果"} )
    private String  msg;
    /**  请求url  */
    @ExcelProperty(value = {"请求url"} )
    private String  url;
    /**  处理车辆数  */
    @ExcelProperty(value = {"处理车辆数"} )
    private Long  veco;


}