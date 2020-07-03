package com.hzz.hzzcloud.高德地图api.行人规划;

import lombok.Data;
import net.fxft.common.jdbc.DbId;
import net.fxft.common.jdbc.DbTable;

import java.util.Date;

@Data
@DbTable(value="gps_hisdata.gpsinfo_20200701")
public class Gpsinfo_20200701 implements java.io.Serializable  {

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


}