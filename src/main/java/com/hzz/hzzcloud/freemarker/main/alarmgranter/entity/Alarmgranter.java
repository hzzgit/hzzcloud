package com.hzz.hzzcloud.freemarker.main.alarmgranter.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.alarmgranter")
public class Alarmgranter implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_name = "name";
    public static final String F_alarmtypes = "alarmtypes";
    public static final String F_createdate = "createdate";
    public static final String F_updatedate = "updatedate";
    public static final String F_userid = "userid";
    public static final String F_isuse = "isuse";
    public static final String F_deleted = "deleted";
    public static final String F_url = "url";


    /**  主键  */
        @DbId
    private Long  id;
    /**  规则名称  */
    private String  name;
    /**  报警要转发的报警类型,格式为,报警来源:报警类型;报警来源:报警类型;(例如:终端报警:超速报警;平台报警:超速报警)  */
    private String  alarmtypes;
    /**  创建时间  */
    private Date  createdate;
    /**  修改时间  */
    private Date  updatedate;
    /**  用户id  */
    private Long  userid;
    /**  是否启用  */
    private Boolean  isuse;
    /**  删除标志,1代表删除,0代表正常  */
    private Long  deleted;
    /**  访问接口  */
    private String  url;


}