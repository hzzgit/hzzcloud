package com.hzz.hzzcloud.freemarker.main.alarmgranteruserlim.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.alarmgranteruserlim")
public class Alarmgranteruserlim implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_configid = "configid";
    public static final String F_userid = "userid";


    /**    */
        @DbId
    private Long  id;
    /**  转发配置表的主键  */
    private Long  configid;
    /**  用户id  */
    private Long  userid;


}