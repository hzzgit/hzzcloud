package com.hzz.hzzcloud.freemarker.main.orderareamanage.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class OrderareamanageExlVo  extends BaseRowModel  {

    @ExcelProperty(value = {"序号"} )
    private int id;
    /**  创建时间  */
    @ExcelProperty(value = {"创建时间"} )
    private Date  createdate;
    /**  车牌号  */
    @ExcelProperty(value = {"车牌号"} )
    private String  plateno;
    /**  simNo,终端卡号  */
    @ExcelProperty(value = {"simNo,终端卡号"} )
    private String  simno;
    /**  订单名称  */
    @ExcelProperty(value = {"订单名称"} )
    private String  name;
    /**  订单开始时间  */
    @ExcelProperty(value = {"订单开始时间"} )
    private Date  starttime;
    /**  订单结束时间  */
    @ExcelProperty(value = {"订单结束时间"} )
    private Date  endtime;
    /**  状态，0、停用，1、启用  */
    @ExcelProperty(value = {"状态，0、停用，1、启用"} )
    private Long  state;


}