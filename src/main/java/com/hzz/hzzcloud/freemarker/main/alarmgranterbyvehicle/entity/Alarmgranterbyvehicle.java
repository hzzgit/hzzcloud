package com.hzz.hzzcloud.freemarker.main.alarmgranterbyvehicle.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.alarmgranterbyvehicle")
public class Alarmgranterbyvehicle implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_configid = "configid";
    public static final String F_vehicleid = "vehicleid";
    public static final String F_createdate = "createdate";
    public static final String F_deleted = "deleted";


    /**  主键  */
        @DbId
    private Long  id;
    /**  配置id  */
    private Long  configid;
    /**  车辆主键  */
    private Long  vehicleid;
    /**  创建时间  */
    private Date  createdate;
    /**  删除标志,0代表未删除,1代表删除  */
    private Long  deleted;


}