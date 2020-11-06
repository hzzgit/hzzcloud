package com.hzz.hzzcloud.freemarker.main.datafillingvehicle.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class DatafillingvehicleExlVo  extends BaseRowModel  {

    @ExcelProperty(value = {"序号"} )
    private int id;
    /**    */
    @ExcelProperty(value = {""} )
    private Date  createdate;
    /**    */
    @ExcelProperty(value = {""} )
    private Long  vehicleid;


}