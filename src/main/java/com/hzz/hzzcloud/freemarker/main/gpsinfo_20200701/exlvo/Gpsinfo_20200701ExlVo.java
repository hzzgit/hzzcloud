package com.hzz.hzzcloud.freemarker.main.gpsinfo_20200701.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class Gpsinfo_20200701ExlVo  extends BaseRowModel  {

    @ExcelProperty(value = {"序号"} )
    private int id;
    /**    */
    @ExcelProperty(value = {""} )
    private Long  uuid;
    /**    */
    @ExcelProperty(value = {""} )
    private String  simno;
    /**    */
    @ExcelProperty(value = {""} )
    private Long  vehicleid;
    /**    */
    @ExcelProperty(value = {""} )
    private Date  createdate;
    /**    */
    @ExcelProperty(value = {""} )
    private Long  alarmstate;
    /**    */
    @ExcelProperty(value = {""} )
    private Double  altitude;
    /**    */
    @ExcelProperty(value = {""} )
    private Long  direction;
    /**    */
    @ExcelProperty(value = {""} )
    private Double  gas;
    /**    */
    @ExcelProperty(value = {""} )
    private Double  latitude;
    /**    */
    @ExcelProperty(value = {""} )
    private Double  longitude;
    /**    */
    @ExcelProperty(value = {""} )
    private Double  mileage;
    /**    */
    @ExcelProperty(value = {""} )
    private Double  recordvelocity;
    /**    */
    @ExcelProperty(value = {""} )
    private Long  status;
    /**    */
    @ExcelProperty(value = {""} )
    private String  valid;
    /**    */
    @ExcelProperty(value = {""} )
    private Double  velocity;
    /**    */
    @ExcelProperty(value = {""} )
    private Long  signalstate;
    /**    */
    @ExcelProperty(value = {""} )
    private Long  abnormaltype;
    /**    */
    @ExcelProperty(value = {""} )
    private Long  fromflag;
    /**    */
    @ExcelProperty(value = {""} )
    private Long  extendversion;
    /**    */
    @ExcelProperty(value = {""} )
    private String  extendjson;


}