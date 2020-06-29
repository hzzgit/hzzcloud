package com.hzz.hzzcloud.freemarker.main.alarmgrantertopic.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.alarmgrantertopic")
public class Alarmgrantertopic implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_name = "name";
    public static final String F_topicname = "topicname";
    public static final String F_createdate = "createdate";
    public static final String F_updatedate = "updatedate";
    public static final String F_deleted = "deleted";
    public static final String F_isuse = "isuse";
    public static final String F_depid = "depid";
    public static final String F_userid = "userid";


    /**  主键  */
        @DbId
    private Long  id;
    /**  公司名称  */
    private String  name;
    /**  topic名称,这边要在新增的时候,自动生成一个字符串.唯一字符串.可以使用uuid  */
    private String  topicname;
    /**  创建时间  */
    private Date  createdate;
    /**  修改时间  */
    private Date  updatedate;
    /**  删除标志,0未删除,1已删除  */
    private Boolean  deleted;
    /**  是否开启,0关闭,1开启  */
    private Boolean  isuse;
    /**  申请转发开通的时候选择的机构id,唯一的,只能有一条有这个机构  */
    private Long  depid;
    /**  申请转发开通的时候选择的用户ID,用户已经申请转发开通之后,不能再进行申请  */
    private Long  userid;


}