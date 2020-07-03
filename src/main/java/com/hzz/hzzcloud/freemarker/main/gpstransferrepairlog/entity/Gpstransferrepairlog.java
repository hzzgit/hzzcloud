package com.hzz.hzzcloud.freemarker.main.gpstransferrepairlog.entity;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
@DbTable(value="subiaodb.gpstransferrepairlog")
public class Gpstransferrepairlog implements java.io.Serializable  {

private static final long serialVersionUID = 1L;
    public static final String F_id = "id";
    public static final String F_starttime = "starttime";
    public static final String F_createdate = "createdate";
    public static final String F_endtime = "endtime";
    public static final String F_msg = "msg";
    public static final String F_url = "url";
    public static final String F_veco = "veco";


    /**    */
        @DbId
    private Long  id;
    /**  开始时间  */
    private Date  starttime;
    /**  创建时间  */
    private Date  createdate;
    /**  结束时间  */
    private Date  endtime;
    /**  处理结果  */
    private String  msg;
    /**  请求url  */
    private String  url;
    /**  处理车辆数  */
    private Long  veco;


}