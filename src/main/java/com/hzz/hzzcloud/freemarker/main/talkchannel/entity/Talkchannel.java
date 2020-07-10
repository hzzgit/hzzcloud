package com.hzz.hzzcloud.freemarker.main.talkchannel.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.talkchannel")
public class Talkchannel implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_channelid = "channelid";
    public static final String F_channelname = "channelname";
    public static final String F_createdate = "createdate";
    public static final String F_membernum = "membernum";
    public static final String F_remark = "remark";
    public static final String F_owner = "owner";
    public static final String F_status = "status";
    public static final String F_type = "type";
    public static final String F_deleted = "deleted";
    public static final String F_updatedate = "updatedate";


    /**  主键  */
        @DbId
    private Long  id;
    /**  频道Id  */
    private String  channelid;
    /**  频道名称  */
    private String  channelname;
    /**  创建时间  */
    private Date  createdate;
    /**  成员数量  */
    private Long  membernum;
    /**  频道备注  */
    private String  remark;
    /**  创建用户id  */
    private Long  owner;
    /**  状态,0,正常,4禁用  */
    private Long  status;
    /**  类型,0频道(该字段暂时无用,预留)  */
    private Long  type;
    /**  0代表未删除,1以上代表删除  */
    private Long  deleted;
    /**  更新时间  */
    private Date  updatedate;

public static void main(String[] args) {
String name="{\n";
        name +="  \"id\":0  , //主键\n";
        name +="  \"channelid\":\"\"  , //频道Id\n";
        name +="  \"channelname\":\"\"  , //频道名称\n";
        name +="  \"createdate\":\"2020-09-11 00:00:00\"  , //创建时间\n";
        name +="  \"membernum\":0  , //成员数量\n";
        name +="  \"remark\":\"\"  , //频道备注\n";
        name +="  \"owner\":0  , //创建用户id\n";
        name +="  \"status\":0  , //状态,0,正常,4禁用\n";
        name +="  \"type\":0  , //类型,0频道(该字段暂时无用,预留)\n";
        name +="  \"deleted\":0  , //0代表未删除,1以上代表删除\n";
        name +="  \"updatedate\":\"2020-09-11 00:00:00\"   //更新时间\n";
name+="}";
System.out.println(name);

}

}