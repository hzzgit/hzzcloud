package templatestree.后台服务所用缓存.flowlimitconfig.cache.DO;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
public class FlowlimitconfigCacheDO implements java.io.Serializable  {
    /*车牌号*/
    private String plateNo;

    /**  主键  */
    private Long  id;
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
    private Long  userid;
    /**  是否启用,0禁用,1启用  */
    private Boolean  isuse;
    /**  删除标志,1代表删除,0代表正常  */
    private Long  deleted;

}