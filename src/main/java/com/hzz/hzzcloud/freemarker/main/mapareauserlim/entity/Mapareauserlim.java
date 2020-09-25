package com.hzz.hzzcloud.freemarker.main.mapareauserlim.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.mapareauserlim")
public class Mapareauserlim implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_configid = "configid";
    public static final String F_userid = "userid";


    /**    */
        @DbId
    private Long  id;
    /**  转发配置表的主键  */
    private Long  configid;
    /**  用户id  */
    private Long  userid;

public static void main(String[] args) {
String name="{\n";
    name +="  \"id\":0, //\n";
    name +="  \"configid\":0, //转发配置表的主键\n";
    name +="  \"userid\":0 //用户id\n";
name+="}";
System.out.println(name);

}

}