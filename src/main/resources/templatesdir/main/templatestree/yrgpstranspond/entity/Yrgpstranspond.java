package templatestree.yrgpstranspond.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.yrgpstranspond")
public class Yrgpstranspond implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_name = "name";
    public static final String F_consumer = "consumer";
    public static final String F_ak = "ak";
    public static final String F_ip = "ip";
    public static final String F_port = "port";
    public static final String F_createdate = "createdate";
    public static final String F_updatedate = "updatedate";
    public static final String F_userid = "userid";
    public static final String F_isuse = "isuse";
    public static final String F_deleted = "deleted";


    /**  主键  */
        @DbId
    private Long  id;
    /**  名称  */
    private String  name;
    /**  consumer为⽤户id由奕人提供  */
    private String  consumer;
    /**  ak由奕人提供  */
    private String  ak;
    /**  转发的ip地址  */
    private String  ip;
    /**  转发的端口号  */
    private Long  port;
    /**  创建时间  */
    private Date  createdate;
    /**  修改时间  */
    private Date  updatedate;
    /**  用户id  */
    private Long  userid;
    /**  是否启用,0禁用,1启用  */
    private Boolean  isuse;
    /**  删除标志,1代表删除,0代表正常  */
    private Long  deleted;

public static void main(String[] args) {
String name="{\n";
    name +="  \"id\":0, //主键\n";
    name +="  \"name\":\"\", //名称\n";
    name +="  \"consumer\":\"\", //consumer为⽤户id由奕人提供\n";
    name +="  \"ak\":\"\", //ak由奕人提供\n";
    name +="  \"ip\":\"\", //转发的ip地址\n";
    name +="  \"port\":0, //转发的端口号\n";
    name +="  \"createdate\":\"2020-09-11 00:00:00\", //创建时间\n";
    name +="  \"updatedate\":\"2020-09-11 00:00:00\", //修改时间\n";
    name +="  \"userid\":0, //用户id\n";
    name +="  \"isuse\":false, //是否启用,0禁用,1启用\n";
    name +="  \"deleted\":0 //删除标志,1代表删除,0代表正常\n";
name+="}";
System.out.println(name);

}

}