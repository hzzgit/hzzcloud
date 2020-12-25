package templatestree.后台服务所用缓存.reportpushbytime.cache.DO;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
public class ReportpushbytimeCacheDO implements java.io.Serializable  {
    /*车牌号*/
    private String plateNo;

    /**  主键  */
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

}