package common.flowlimitconfig.entity;

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


    /**  主键  */
        @DbId
    private long  id;
    /**  名称  */
    private String  name;
    /**  流量控制的阈值  */
    private String  limitval;
    /**  有效开始时间  */
    private Date  validstarttime;
    /**  有效结束时间  */
    private Date  validendtime;
    /**  创建时间  */
    private Date  createdate;
    /**  修改时间  */
    private Date  updatedate;
    /**  用户id  */
    private long  userid;
    /**  是否启用,0禁用,1启用  */
    private Boolean  isuse;
    /**  删除标志,1代表删除,0代表正常  */
    private long  deleted;

public static void main(String[] args) {
String name="{\n";
    name +="  \"id\":0, //主键\n";
    name +="  \"name\":\"\", //名称\n";
    name +="  \"limitval\":\"\", //流量控制的阈值\n";
    name +="  \"validstarttime\":\"2020-09-11 00:00:00\", //有效开始时间\n";
    name +="  \"validendtime\":\"2020-09-11 00:00:00\", //有效结束时间\n";
    name +="  \"createdate\":\"2020-09-11 00:00:00\", //创建时间\n";
    name +="  \"updatedate\":\"2020-09-11 00:00:00\", //修改时间\n";
    name +="  \"userid\":0, //用户id\n";
    name +="  \"isuse\":false, //是否启用,0禁用,1启用\n";
    name +="  \"deleted\":0 //删除标志,1代表删除,0代表正常\n";
name+="}";
System.out.println(name);

}

}