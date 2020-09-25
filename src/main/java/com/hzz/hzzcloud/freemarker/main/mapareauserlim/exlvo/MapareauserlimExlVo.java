package com.hzz.hzzcloud.freemarker.main.mapareauserlim.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class MapareauserlimExlVo  extends BaseRowModel  {

    @ExcelProperty(value = {"序号"} )
    private int id;
    /**  转发配置表的主键  */
    @ExcelProperty(value = {"转发配置表的主键"} )
    private Long  configid;
    /**  用户id  */
    @ExcelProperty(value = {"用户id"} )
    private Long  userid;


}