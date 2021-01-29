package nocommon.takingphotosbytime.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class TakingphotosbytimeExlVo  extends BaseRowModel  {

@ExcelProperty(value = {"序号"} )
private int id;
        /**  名称  */
        @ExcelProperty(value = {"名称"} )
        private String  name;
        /**  拍照间隔(单位：分)  */
        @ExcelProperty(value = {"拍照间隔(单位：分)"} )
        private Long  configinterval;
        /**  创建时间  */
        @ExcelProperty(value = {"创建时间"} )
        private Date  createdate;
        /**  修改时间  */
        @ExcelProperty(value = {"修改时间"} )
        private Date  updatedate;
        /**  配置生效开始时间，时间格式为00:00:00   */
        @ExcelProperty(value = {"配置生效开始时间，时间格式为00:00:00 "} )
        private String  starttime;
        /**  配置生效结束时间,时间格式为 23:59:59  */
        @ExcelProperty(value = {"配置生效结束时间,时间格式为 23:59:59"} )
        private String  endtime;
        /**  配置有效期开始时间,都不传则是无限制，只传开始时间就是大于开始时间即可  */
        @ExcelProperty(value = {"配置有效期开始时间,都不传则是无限制，只传开始时间就是大于开始时间即可"} )
        private Date  validstarttime;
        /**  配置有效期结束时间,都不传则是无限制，只传结束时间就是小于开始时间即可  */
        @ExcelProperty(value = {"配置有效期结束时间,都不传则是无限制，只传结束时间就是小于开始时间即可"} )
        private Date  validendtime;
        /**  触发条件,0、不限制，1、根据车速，2、根据停车时长  */
        @ExcelProperty(value = {"触发条件,0、不限制，1、根据车速，2、根据停车时长"} )
        private Long  configcondition;
        /**  车速单位km/h，触发条件为1的车速  */
        @ExcelProperty(value = {"车速单位km/h，触发条件为1的车速"} )
        private String  speed;
        /**  停车时长单位分钟，触发条件为2的停车时长  */
        @ExcelProperty(value = {"停车时长单位分钟，触发条件为2的停车时长"} )
        private String  packduration;
        /**  拍照的通道,用";"号隔开  */
        @ExcelProperty(value = {"拍照的通道,用";"号隔开"} )
        private String  channel;
        /**  用户id  */
        @ExcelProperty(value = {"用户id"} )
        private Long  userid;
        /**  是否启用,0禁用,1启用  */
        @ExcelProperty(value = {"是否启用,0禁用,1启用"} )
        private Long  isuse;
        /**  删除标志,1代表删除,0代表正常  */
        @ExcelProperty(value = {"删除标志,1代表删除,0代表正常"} )
        private Long  deleted;
        /**  用户名  */
        @ExcelProperty(value = {"用户名"} )
        private String  username;


}