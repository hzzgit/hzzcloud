package templatestree.takingphotosbytimeresult.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class TakingphotosbytimeresultExlVo  extends BaseRowModel  {

@ExcelProperty(value = {"序号"} )
private int id;
        /**  创建时间  */
        @ExcelProperty(value = {"创建时间"} )
        private Date  createdate;
        /**  更新时间  */
        @ExcelProperty(value = {"更新时间"} )
        private Date  updatedate;
        /**  删除标志  */
        @ExcelProperty(value = {"删除标志"} )
        private String  deleted;
        /**  发送命令用户id  */
        @ExcelProperty(value = {"发送命令用户id"} )
        private Long  userid;
        /**  拍照总数  */
        @ExcelProperty(value = {"拍照总数"} )
        private Long  photonum;
        /**  备注  */
        @ExcelProperty(value = {"备注"} )
        private String  remark;
        /**  维度  */
        @ExcelProperty(value = {"维度"} )
        private String  latitude;
        /**  经度  */
        @ExcelProperty(value = {"经度"} )
        private String  longitude;
        /**  定位时间  */
        @ExcelProperty(value = {"定位时间"} )
        private Date  sendtime;
        /**  simNo  */
        @ExcelProperty(value = {"simNo"} )
        private String  simno;
        /**  车辆主键  */
        @ExcelProperty(value = {"车辆主键"} )
        private String  vehicleid;
        /**  当前车速  */
        @ExcelProperty(value = {"当前车速"} )
        private String  speed;
        /**  司机姓名  */
        @ExcelProperty(value = {"司机姓名"} )
        private String  drivername;
        /**  从业资格证号  */
        @ExcelProperty(value = {"从业资格证号"} )
        private String  certificate;
        /**  配置表id,表名takingphotosbytime  */
        @ExcelProperty(value = {"配置表id,表名takingphotosbytime"} )
        private Long  configid;
        /**  上传情况，0,未上传,1上传成功  */
        @ExcelProperty(value = {"上传情况，0,未上传,1上传成功"} )
        private Long  status;


}