package com.hzz.hzzcloud.freemarker.main.orderareapoint.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.orderareapoint")
public class Orderareapoint implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_createdate = "createdate";
    public static final String F_pointtype = "pointtype";
    public static final String F_longitude = "longitude";
    public static final String F_latitude = "latitude";
    public static final String F_maptype = "maptype";
    public static final String F_orderid = "orderid";


    /**  主键  */
        @DbId
    private Long  id;
    /**  创建时间  */
    private Date  createdate;
    /**  点位类型,1，开始点，2，途经点，3，结束点  */
    private Long  pointtype;
    /**  经度  */
    private String  longitude;
    /**  纬度  */
    private String  latitude;
    /**  地图类型 gps:天地图坐标，baidu:百度坐标，google:谷歌地图  */
    private String  maptype;
    /**  和orderareamanage表主键绑定  */
    private Long  orderid;

public static void main(String[] args) {
String name="{\n";
        name +="  \"id\":0, //主键\n";
        name +="  \"createdate\":\"2020-09-11 00:00:00\", //创建时间\n";
        name +="  \"pointtype\":0, //点位类型,1，开始点，2，途经点，3，结束点\n";
        name +="  \"longitude\":\"\", //经度\n";
        name +="  \"latitude\":\"\", //纬度\n";
        name +="  \"maptype\":\"\", //地图类型 gps:天地图坐标，baidu:百度坐标，google:谷歌地图\n";
        name +="  \"orderid\":0 //和orderareamanage表主键绑定\n";
name+="}";
System.out.println(name);

}

}