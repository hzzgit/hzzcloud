package templatestree.后台服务所用缓存.config_table.cache.DO;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
public class Config_tableCacheDO implements java.io.Serializable  {
    /*车牌号*/
    private String plateNo;

    /**  主键  */
    private Long  id;
    /**  数据库名  */
    private String  database;
    /**  表名  */
    private String  tablename;
    /**  删除标志  */
    private Long  deleted;
    /**  创建时间  */
    private Date  createdate;
    /**  修改时间  */
    private Date  updatedate;

}