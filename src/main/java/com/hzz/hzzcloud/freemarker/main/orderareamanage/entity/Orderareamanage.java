package com.hzz.hzzcloud.freemarker.main.orderareamanage.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.orderareamanage")
public class Orderareamanage implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_createdate = "createdate";
    public static final String F_plateno = "plateno";
    public static final String F_simno = "simno";
    public static final String F_name = "name";
    public static final String F_starttime = "starttime";
    public static final String F_endtime = "endtime";
    public static final String F_state = "state";


    /**  主键  */
        @DbId
    private Long  id;
    /**  创建时间  */
    private Date  createdate;
    /**  车牌号  */
    private String  plateno;
    /**  simNo,终端卡号  */
    private String  simno;
    /**  订单名称  */
    private String  name;
    /**  订单开始时间  */
    private Date  starttime;
    /**  订单结束时间  */
    private Date  endtime;
    /**  状态，0、停用，1、启用  */
    private Long  state;

public static void main(String[] args) {
String name="{\n";
        name +="  \"id\":0, //主键\n";
        name +="  \"createdate\":\"2020-09-11 00:00:00\", //创建时间\n";
        name +="  \"plateno\":\"\", //车牌号\n";
        name +="  \"simno\":\"\", //simNo,终端卡号\n";
        name +="  \"name\":\"\", //订单名称\n";
        name +="  \"starttime\":\"2020-09-11 00:00:00\", //订单开始时间\n";
        name +="  \"endtime\":\"2020-09-11 00:00:00\", //订单结束时间\n";
        name +="  \"state\":0 //状态，0、停用，1、启用\n";
name+="}";
System.out.println(name);

}

}