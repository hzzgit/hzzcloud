package templatestree.config_table.entity;

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


    /**  主键修改则传列表中的  */
        @DbId
    private long  id;
    /**  数据库名修改则传列表中的  */
    private String  database;
    /**  表名修改则传列表中的  */
    private String  tablename;
    /**  删除标志修改则传列表中的  */
    private long  deleted;
    /**  创建时间修改则传列表中的  */
    private Date  createdate;
    /**  修改时间修改则传列表中的  */
    private Date  updatedate;

public static void main(String[] args) {
String name="{\n";
        name +="  \"id\":1, //主键\n";
        name +="  \"database\":\"测试\", //数据库名\n";
        name +="  \"tablename\":\"测试\", //表名\n";
name+="}";
System.out.println(name);
name ="    {\"success\":true, \n "+
" \"code\":200,\n " +
" \"message\":\"success\", \n " +
" \"data\": " +name+
", \n \"total\":1} ";
System.out.println(name);
}

}