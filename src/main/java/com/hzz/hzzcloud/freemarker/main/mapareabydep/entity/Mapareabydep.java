package com.hzz.hzzcloud.freemarker.main.mapareabydep.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.mapareabydep")
public class Mapareabydep implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_configid = "configid";
    public static final String F_depid = "depid";
    public static final String F_createdate = "createdate";
    public static final String F_deleted = "deleted";


    /**  主键  */
        @DbId
    private Long  id;
    /**  配置id  */
    private Long  configid;
    /**  绑定机构的id  */
    private Long  depid;
    /**  创建时间  */
    private Date  createdate;
    /**  删除标志,0代表未删除,1代表删除  */
    private Long  deleted;

public static void main(String[] args) {
String name="{\n";
    name +="  \"id\":0, //主键\n";
    name +="  \"configid\":0, //配置id\n";
    name +="  \"depid\":0, //绑定机构的id\n";
    name +="  \"createdate\":\"2020-09-11 00:00:00\", //创建时间\n";
    name +="  \"deleted\":0 //删除标志,0代表未删除,1代表删除\n";
name+="}";
System.out.println(name);

}

}