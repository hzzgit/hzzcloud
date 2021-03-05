package templatestree.config_table_filed.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.config_table_fileduserlim")
public class Config_table_fileduserlim implements java.io.Serializable  {

private static final long serialVersionUID = 1L;

public static final String F_id = "id";
public static final String F_mainid = "mainid";
public static final String F_userid = "userid";


/**  修改则传列表中的  */
@DbId
private Long  id;
/**  配置表id修改则传列表中的  */
private Long  mainid;
/**  用户id修改则传列表中的  */
private Long  userid;

public static void main(String[] args) {
String name="{\n";
name +="  \"id\":1, //\n";
name +="  \"mainid\":1, //配置表id\n";
name +="  \"userid\":1 //用户id\n";
name+="}";
System.out.println(name);
name ="    \"success\":true, \n "+
" \"code\":200,\n " +
" \"message\":\"success\", \n " +
" \"data\": " +name+
", \n \"total\":1 ";
System.out.println(name);
}


}