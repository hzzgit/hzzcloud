package templatestree.flowlimitconfig.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.flowlimitconfig")
public class Flowlimitconfig implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_name = "name";
    public static final String F_limitval = "limitval";
    public static final String F_validstarttime = "validstarttime";
    public static final String F_validendtime = "validendtime";
    public static final String F_createdate = "createdate";
    public static final String F_updatedate = "updatedate";
    public static final String F_userid = "userid";
    public static final String F_isuse = "isuse";
    public static final String F_deleted = "deleted";


    /**  主键修改则传列表中的  */
        @DbId
    private long  id;
    /**  名称修改则传列表中的  */
    private String  name;
    /**  流量控制的阈值修改则传列表中的  */
    private String  limitval;
    /**  有效开始时间修改则传列表中的  */
    private Date  validstarttime;
    /**  有效结束时间修改则传列表中的  */
    private Date  validendtime;
    /**  创建时间修改则传列表中的  */
    private Date  createdate;
    /**  修改时间修改则传列表中的  */
    private Date  updatedate;
    /**  用户id修改则传列表中的  */
    private long  userid;
    /**  是否启用,0禁用,1启用修改则传列表中的  */
    private Boolean  isuse;
    /**  删除标志,1代表删除,0代表正常修改则传列表中的  */
    private long  deleted;

public static void main(String[] args) {
String name="{\n";
        name +="  \"id\":1, //主键\n";
        name +="  \"name\":\"测试\", //名称\n";
        name +="  \"limitval\":\"\", //流量控制的阈值\n";
        name +="  \"validstarttime\":\"2020-09-11 00:00:00\", //有效开始时间\n";
        name +="  \"validendtime\":\"2020-09-11 00:00:00\", //有效结束时间\n";
        name +="  \"isuse\":false, //是否启用,0禁用,1启用\n";
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