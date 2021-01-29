package templatestree.后台服务所用缓存.takingphotosbytime.DO;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
public class TakingphotosbytimeByVehicleDO implements java.io.Serializable  {
    /*simNO*/
    private String simNo;

    /*车牌号*/
    private String plateNo;

    /**  主键  */
    private Long  id;
    /**  名称  */
    private String  name;
    /**  拍照间隔(单位：分)  */
    private Long  configinterval;
    /**  创建时间  */
    private Date  createdate;
    /**  修改时间  */
    private Date  updatedate;
    /**  配置生效开始时间，时间格式为00:00:00   */
    private String  starttime;
    /**  配置生效结束时间,时间格式为 23:59:59  */
    private String  endtime;
    /**  配置有效期开始时间,都不传则是无限制，只传开始时间就是大于开始时间即可  */
    private Date  validstarttime;
    /**  配置有效期结束时间,都不传则是无限制，只传结束时间就是小于开始时间即可  */
    private Date  validendtime;
    /**  触发条件,0、不限制，1、根据车速，2、根据停车时长  */
    private Long  configcondition;
    /**  车速单位km/h，触发条件为1的车速  */
    private String  speed;
    /**  停车时长单位分钟，触发条件为2的停车时长  */
    private String  packduration;
    /**  拍照的通道,用";"号隔开  */
    private String  channel;
    /**  用户id  */
    private Long  userid;
    /**  是否启用,0禁用,1启用  */
    private Long  isuse;
    /**  删除标志,1代表删除,0代表正常  */
    private Long  deleted;
    /**  用户名  */
    private String  username;

}