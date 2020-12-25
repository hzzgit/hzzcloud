package templatestree.reportpushbytime.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.reportpushbytime")
public class Reportpushbytime implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_username = "username";
    public static final String F_userid = "userid";
    public static final String F_starttime = "starttime";
    public static final String F_endtime = "endtime";
    public static final String F_createdate = "createdate";
    public static final String F_deleted = "deleted";
    public static final String F_loginname = "loginname";
    public static final String F_vehicleid = "vehicleid";
    public static final String F_plateno = "plateno";


    /**  主键  */
        @DbId
    private Long  id;
    /**  中文名  */
    private String  username;
    /**  英文名  */
    private Long  userid;
    /**  开始时间，yyyy-mm  */
    private String  starttime;
    /**  结束时间,yyyy-mm  */
    private String  endtime;
    /**  创建时间  */
    private Date  createdate;
    /**  删除标志  */
    private Long  deleted;
    /**  登录名  */
    private String  loginname;
    /**  车辆主键  */
    private Long  vehicleid;
    /**  车牌号  */
    private String  plateno;

public static void main(String[] args) {
String name="{\n";
    name +="  \"id\":0, //主键\n";
    name +="  \"username\":\"\", //中文名\n";
    name +="  \"userid\":0, //英文名\n";
    name +="  \"starttime\":\"\", //开始时间，yyyy-mm\n";
    name +="  \"endtime\":\"\", //结束时间,yyyy-mm\n";
    name +="  \"createdate\":\"2020-09-11 00:00:00\", //创建时间\n";
    name +="  \"deleted\":0, //删除标志\n";
    name +="  \"loginname\":\"\", //登录名\n";
    name +="  \"vehicleid\":0, //车辆主键\n";
    name +="  \"plateno\":\"\" //车牌号\n";
name+="}";
System.out.println(name);

}

}