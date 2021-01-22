package templatestree.后台服务所用缓存.takingphotosbytimeresult.DO;

import lombok.*;
import net.fxft.common.jdbc.*;
import java.util.*;

@Data
public class TakingphotosbytimeresultByVehicleDO implements java.io.Serializable  {
    /*simNO*/
    private String simNo;

    /*车牌号*/
    private String plateNo;

    /**    */
    private Long  id;
    /**  创建时间  */
    private Date  createdate;
    /**  更新时间  */
    private Date  updatedate;
    /**  删除标志  */
    private String  deleted;
    /**  发送命令用户id  */
    private Long  userid;
    /**  拍照总数  */
    private Long  photonum;
    /**  备注  */
    private String  remark;
    /**  维度  */
    private String  latitude;
    /**  经度  */
    private String  longitude;
    /**  定位时间  */
    private Date  sendtime;
    /**  simNo  */
    private String  simno;
    /**  车辆主键  */
    private String  vehicleid;
    /**  当前车速  */
    private String  speed;
    /**  司机姓名  */
    private String  drivername;
    /**  从业资格证号  */
    private String  certificate;
    /**  配置表id,表名takingphotosbytime  */
    private Long  configid;
    /**  上传情况，0,未上传,1上传成功  */
    private Long  status;

}