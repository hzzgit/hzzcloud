package templatestree.takingphotosbytime.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.takingphotosbytime")
public class Takingphotosbytime implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_name = "name";
    public static final String F_configinterval = "configinterval";
    public static final String F_createdate = "createdate";
    public static final String F_updatedate = "updatedate";
    public static final String F_starttime = "starttime";
    public static final String F_endtime = "endtime";
    public static final String F_validstarttime = "validstarttime";
    public static final String F_validendtime = "validendtime";
    public static final String F_configcondition = "configcondition";
    public static final String F_speed = "speed";
    public static final String F_packduration = "packduration";
    public static final String F_channel = "channel";
    public static final String F_userid = "userid";
    public static final String F_isuse = "isuse";
    public static final String F_deleted = "deleted";
    public static final String F_username = "username";


    /**  主键修改则传列表中的  */
        @DbId
    private long  id;
    /**  名称修改则传列表中的  */
    private String  name;
    /**  拍照间隔(单位：分)修改则传列表中的  */
    private long  configinterval;
    /**  创建时间修改则传列表中的  */
    private Date  createdate;
    /**  修改时间修改则传列表中的  */
    private Date  updatedate;
    /**  配置生效开始时间，时间格式为00:00:00 修改则传列表中的  */
    private String  starttime;
    /**  配置生效结束时间,时间格式为 23:59:59修改则传列表中的  */
    private String  endtime;
    /**  配置有效期开始时间,都不传则是无限制，只传开始时间就是大于开始时间即可修改则传列表中的  */
    private Date  validstarttime;
    /**  配置有效期结束时间,都不传则是无限制，只传结束时间就是小于开始时间即可修改则传列表中的  */
    private Date  validendtime;
    /**  触发条件,0、不限制，1、根据车速，2、根据停车时长修改则传列表中的  */
    private long  configcondition;
    /**  车速单位km/h，触发条件为1的车速修改则传列表中的  */
    private String  speed;
    /**  停车时长单位分钟，触发条件为2的停车时长修改则传列表中的  */
    private String  packduration;
    /**  拍照的通道,用";"号隔开修改则传列表中的  */
    private String  channel;
    /**  用户id修改则传列表中的  */
    private long  userid;
    /**  是否启用,0禁用,1启用修改则传列表中的  */
    private long  isuse;
    /**  删除标志,1代表删除,0代表正常修改则传列表中的  */
    private long  deleted;
    /**  用户名修改则传列表中的  */
    private String  username;

public static void main(String[] args) {
String name="{\n";
        name +="  \"id\":1, //主键\n";
        name +="  \"name\":\"测试\", //名称\n";
        name +="  \"configinterval\":1, //拍照间隔(单位：分)\n";
        name +="  \"starttime\":\"测试\", //配置生效开始时间，时间格式为00:00:00 \n";
        name +="  \"endtime\":\"测试\", //配置生效结束时间,时间格式为 23:59:59\n";
        name +="  \"validstarttime\":\"2020-09-11 00:00:00\", //配置有效期开始时间,都不传则是无限制，只传开始时间就是大于开始时间即可\n";
        name +="  \"validendtime\":\"2020-09-11 00:00:00\", //配置有效期结束时间,都不传则是无限制，只传结束时间就是小于开始时间即可\n";
        name +="  \"configcondition\":1, //触发条件,0、不限制，1、根据车速，2、根据停车时长\n";
        name +="  \"speed\":\"\", //车速单位km/h，触发条件为1的车速\n";
        name +="  \"packduration\":\"\", //停车时长单位分钟，触发条件为2的停车时长\n";
        name +="  \"channel\":\"测试\", //拍照的通道,用";"号隔开\n";
        name +="  \"isuse\":1, //是否启用,0禁用,1启用\n";
        name +="  \"username\":\"测试\" //用户名\n";
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