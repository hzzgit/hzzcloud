package com.hzz.hzzcloud.freemarker.main.datafillingvehicle.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.datafillingvehicle")
public class Datafillingvehicle implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_createdate = "createdate";
    public static final String F_vehicleid = "vehicleid";


    /**    */
        @DbId
    private Long  id;
    /**    */
    private Date  createdate;
    /**    */
    private Long  vehicleid;

public static void main(String[] args) {
String name="{\n";
    name +="  \"id\":0, //\n";
    name +="  \"createdate\":\"2020-09-11 00:00:00\", //\n";
    name +="  \"vehicleid\":0 //\n";
name+="}";
System.out.println(name);

}

}