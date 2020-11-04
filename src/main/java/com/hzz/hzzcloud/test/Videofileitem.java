package com.hzz.hzzcloud.test;

import com.hzz.hzzjdbc.jdbcutil.annotation.DbTableName;
import lombok.Data;
import net.fxft.common.jdbc.DbId;

import java.util.Date;

@Data
@DbTableName(value="subiaodb.videofileitem")
public class Videofileitem implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_baseid = "baseid";
    public static final String F_alarmstatus = "alarmstatus";
    public static final String F_channelid = "channelid";
    public static final String F_commandid = "commandid";
    public static final String F_createdate = "createdate";
    public static final String F_datatype = "datatype";
    public static final String F_enddate = "enddate";
    public static final String F_filelength = "filelength";
    public static final String F_startdate = "startdate";
    public static final String F_storetype = "storetype";
    public static final String F_streamtype = "streamtype";
    public static final String F_status = "status";
    public static final String F_plateno = "plateno";
    public static final String F_vehicleid = "vehicleid";
    public static final String F_filepath = "filepath";
    public static final String F_filesource = "filesource";
    public static final String F_uploaddate = "uploaddate";
    public static final String F_simno = "simno";
    public static final String F_latitude1 = "latitude1";
    public static final String F_latitude2 = "latitude2";
    public static final String F_longitude1 = "longitude1";
    public static final String F_longitude2 = "longitude2";
    public static final String F_fileurl = "fileurl";
    public static final String F_downname = "downname";
    public static final String F_playname = "playname";


    /**    */
        @DbId
    private Long  baseid;
    /**    */
    private Long  alarmstatus;
    /**    */
    private String  channelid;
    /**    */
    private Long  commandid;
    /**    */
    private Date  createdate;
    /**    */
    private String  datatype;
    /**    */
    private Date  enddate;
    /**    */
    private Long  filelength;
    /**    */
    private Date  startdate;
    /**    */
    private String  storetype;
    /**    */
    private String  streamtype;
    /**    */
    private String  status;
    /**    */
    private String  plateno;
    /**    */
    private Long  vehicleid;
    /**    */
    private String  filepath;
    /**    */
    private String  filesource;
    /**    */
    private Date  uploaddate;
    /**    */
    private String  simno;
    /**    */
    private String  latitude1;
    /**    */
    private String  latitude2;
    /**    */
    private String  longitude1;
    /**    */
    private String  longitude2;
    /**  服务器URL  */
    private String  fileurl;
    /**  下载文件名  */
    private String  downname;
    /**  播放文件名  */
    private String  playname;

public static void main(String[] args) {
String name="{\n";
    name +="  \"baseid\":0, //\n";
    name +="  \"alarmstatus\":0, //\n";
    name +="  \"channelid\":\"\", //\n";
    name +="  \"commandid\":0, //\n";
    name +="  \"createdate\":\"2020-09-11 00:00:00\", //\n";
    name +="  \"datatype\":\"\", //\n";
    name +="  \"enddate\":\"2020-09-11 00:00:00\", //\n";
    name +="  \"filelength\":0, //\n";
    name +="  \"startdate\":\"2020-09-11 00:00:00\", //\n";
    name +="  \"storetype\":\"\", //\n";
    name +="  \"streamtype\":\"\", //\n";
    name +="  \"status\":\"\", //\n";
    name +="  \"plateno\":\"\", //\n";
    name +="  \"vehicleid\":0, //\n";
    name +="  \"filepath\":\"\", //\n";
    name +="  \"filesource\":\"\", //\n";
    name +="  \"uploaddate\":\"2020-09-11 00:00:00\", //\n";
    name +="  \"simno\":\"\", //\n";
    name +="  \"latitude1\":\"\", //\n";
    name +="  \"latitude2\":\"\", //\n";
    name +="  \"longitude1\":\"\", //\n";
    name +="  \"longitude2\":\"\", //\n";
    name +="  \"fileurl\":\"\", //服务器URL\n";
    name +="  \"downname\":\"\", //下载文件名\n";
    name +="  \"playname\":\"\" //播放文件名\n";
name+="}";
System.out.println(name);

}

}