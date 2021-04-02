package nocommon.config_table.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.config_table")
public class Config_table implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_database = "database";
    public static final String F_tablename = "tablename";
    public static final String F_deleted = "deleted";
    public static final String F_createdate = "createdate";
    public static final String F_updatedate = "updatedate";


    /**  主键  */
        @DbId
    private Long  id;
    /**  数据库名  */
    private String  database;
    /**  表名  */
    private String  tablename;
    /**  删除标志  */
    private Long  deleted;
    /**  创建时间  */
    private Date  createdate;
    /**  修改时间  */
    private Date  updatedate;

public static void main(String[] args) {
String name="{\n";
    name +="  \"id\":0, //主键\n";
    name +="  \"database\":\"\", //数据库名\n";
    name +="  \"tablename\":\"\", //表名\n";
    name +="  \"deleted\":0, //删除标志\n";
    name +="  \"createdate\":\"2020-09-11 00:00:00\", //创建时间\n";
    name +="  \"updatedate\":\"2020-09-11 00:00:00\" //修改时间\n";
name+="}";
System.out.println(name);

}

}