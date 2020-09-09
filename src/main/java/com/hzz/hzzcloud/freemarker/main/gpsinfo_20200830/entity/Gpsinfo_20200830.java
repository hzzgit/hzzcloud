package com.hzz.hzzcloud.freemarker.main.gpsinfo_20200830.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="gps_hisdata.gpsinfo_20200830")
public class Gpsinfo_20200830 implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_gpsid = "gpsid";
    public static final String F_uuid = "uuid";
    public static final String F_simnohash = "simnohash";
    public static final String F_simno = "simno";
    public static final String F_vehicleid = "vehicleid";
    public static final String F_createdate = "createdate";
    public static final String F_sendtime = "sendtime";
    public static final String F_alarmstate = "alarmstate";
    public static final String F_altitude = "altitude";
    public static final String F_direction = "direction";
    public static final String F_gas = "gas";
    public static final String F_latitude = "latitude";
    public static final String F_longitude = "longitude";
    public static final String F_mileage = "mileage";
    public static final String F_recordvelocity = "recordvelocity";
    public static final String F_status = "status";
    public static final String F_valid = "valid";
    public static final String F_velocity = "velocity";
    public static final String F_signalstate = "signalstate";
    public static final String F_abnormaltype = "abnormaltype";
    public static final String F_fromflag = "fromflag";
    public static final String F_extendversion = "extendversion";
    public static final String F_extendjson = "extendjson";


    /**    */
        @DbId
    private Long  gpsid;
    /**    */
    private Long  uuid;
    /**    */
        @DbId
    private Long  simnohash;
    /**    */
    private String  simno;
    /**    */
    private Long  vehicleid;
    /**    */
    private Date  createdate;
    /**    */
        @DbId
    private Date  sendtime;
    /**    */
    private Long  alarmstate;
    /**    */
    private Double  altitude;
    /**    */
    private Long  direction;
    /**    */
    private Double  gas;
    /**    */
    private Double  latitude;
    /**    */
    private Double  longitude;
    /**    */
    private Double  mileage;
    /**    */
    private Double  recordvelocity;
    /**    */
    private Long  status;
    /**    */
    private Boolean  valid;
    /**    */
    private Double  velocity;
    /**    */
    private Long  signalstate;
    /**    */
    private Long  abnormaltype;
    /**    */
    private Long  fromflag;
    /**    */
    private Long  extendversion;
    /**    */
    private String  extendjson;

public static void main(String[] args) {
String name="{\n";
        name +="  \"gpsid\":0, //\n";
        name +="  \"uuid\":0, //\n";
        name +="  \"simnohash\":0, //\n";
        name +="  \"simno\":\"\", //\n";
        name +="  \"vehicleid\":0, //\n";
        name +="  \"createdate\":\"2020-09-11 00:00:00\", //\n";
        name +="  \"sendtime\":\"2020-09-11 00:00:00\", //\n";
        name +="  \"alarmstate\":0, //\n";
        name +="  \"altitude\":0.0, //\n";
        name +="  \"direction\":0, //\n";
        name +="  \"gas\":0.0, //\n";
        name +="  \"latitude\":0.0, //\n";
        name +="  \"longitude\":0.0, //\n";
        name +="  \"mileage\":0.0, //\n";
        name +="  \"recordvelocity\":0.0, //\n";
        name +="  \"status\":0, //\n";
        name +="  \"valid\":false, //\n";
        name +="  \"velocity\":0.0, //\n";
        name +="  \"signalstate\":0, //\n";
        name +="  \"abnormaltype\":0, //\n";
        name +="  \"fromflag\":0, //\n";
        name +="  \"extendversion\":0, //\n";
        name +="  \"extendjson\":\"\" //\n";
name+="}";
System.out.println(name);

}

}