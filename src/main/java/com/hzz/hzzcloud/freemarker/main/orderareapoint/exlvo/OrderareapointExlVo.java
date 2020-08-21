package com.hzz.hzzcloud.freemarker.main.orderareapoint.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class OrderareapointExlVo  extends BaseRowModel  {

    @ExcelProperty(value = {"序号"} )
    private int id;
    /**  创建时间  */
    @ExcelProperty(value = {"创建时间"} )
    private Date  createdate;
    /**  点位类型,1，开始点，2，途经点，3，结束点  */
    @ExcelProperty(value = {"点位类型,1，开始点，2，途经点，3，结束点"} )
    private Long  pointtype;
    /**  经度  */
    @ExcelProperty(value = {"经度"} )
    private String  longitude;
    /**  纬度  */
    @ExcelProperty(value = {"纬度"} )
    private String  latitude;
    /**  地图类型 gps:天地图坐标，baidu:百度坐标，google:谷歌地图  */
    @ExcelProperty(value = {"地图类型 gps:天地图坐标，baidu:百度坐标，google:谷歌地图"} )
    private String  maptype;
    /**  和orderareamanage表主键绑定  */
    @ExcelProperty(value = {"和orderareamanage表主键绑定"} )
    private Long  orderid;


}