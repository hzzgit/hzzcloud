package templatestree.vehicle.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class VehicleExlVo  extends BaseRowModel  {

@ExcelProperty(value = {"序号"} )
private int id;
        /**    */
        @ExcelProperty(value = {""} )
        private Date  createdate;
        /**    */
        @ExcelProperty(value = {""} )
        private Long  deleted;
        /**    */
        @ExcelProperty(value = {""} )
        private String  owner;
        /**    */
        @ExcelProperty(value = {""} )
        private String  remark;
        /**    */
        @ExcelProperty(value = {""} )
        private Date  buytime;
        /**  机构id  */
        @ExcelProperty(value = {"机构id"} )
        private Long  depid;
        /**    */
        @ExcelProperty(value = {""} )
        private String  depname;
        /**    */
        @ExcelProperty(value = {""} )
        private String  driver;
        /**    */
        @ExcelProperty(value = {""} )
        private String  drivermobile;
        /**    */
        @ExcelProperty(value = {""} )
        private String  gpsterminaltype;
        /**  运输行业编码  */
        @ExcelProperty(value = {"运输行业编码"} )
        private String  industry;
        /**  安装时间  */
        @ExcelProperty(value = {"安装时间"} )
        private Date  installdate;
        /**    */
        @ExcelProperty(value = {""} )
        private Date  lastchecktime;
        /**  业户id  */
        @ExcelProperty(value = {"业户id"} )
        private Long  memberid;
        /**    */
        @ExcelProperty(value = {""} )
        private String  monitor;
        /**    */
        @ExcelProperty(value = {""} )
        private String  monitormobile;
        /**    */
        @ExcelProperty(value = {""} )
        private String  motorid;
        /**    */
        @ExcelProperty(value = {""} )
        private Date  offlinetime;
        /**    */
        @ExcelProperty(value = {""} )
        private Date  onlinetime;
        /**    */
        @ExcelProperty(value = {""} )
        private String  operpermit;
        /**  车辆颜色  */
        @ExcelProperty(value = {"车辆颜色"} )
        private Long  platecolor;
        /**  车牌号  */
        @ExcelProperty(value = {"车牌号"} )
        private String  plateno;
        /**  车籍地  */
        @ExcelProperty(value = {"车籍地"} )
        private String  region;
        /**    */
        @ExcelProperty(value = {""} )
        private String  routes;
        /**  运行状态  */
        @ExcelProperty(value = {"运行状态"} )
        private String  runstatus;
        /**    */
        @ExcelProperty(value = {""} )
        private String  simno;
        /**    */
        @ExcelProperty(value = {""} )
        private String  status;
        /**    */
        @ExcelProperty(value = {""} )
        private Long  termid;
        /**    */
        @ExcelProperty(value = {""} )
        private String  usetype;
        /**  车辆类型  */
        @ExcelProperty(value = {"车辆类型"} )
        private String  vehicletype;
        /**    */
        @ExcelProperty(value = {""} )
        private String  vendor;
        /**    */
        @ExcelProperty(value = {""} )
        private String  videodeviceid;
        /**    */
        @ExcelProperty(value = {""} )
        private Date  enddate;
        /**    */
        @ExcelProperty(value = {""} )
        private Date  startdate;
        /**    */
        @ExcelProperty(value = {""} )
        private Double  workhour;
        /**    */
        @ExcelProperty(value = {""} )
        private Date  buydate;
        /**    */
        @ExcelProperty(value = {""} )
        private String  engineno;
        /**    */
        @ExcelProperty(value = {""} )
        private String  frameno;
        /**    */
        @ExcelProperty(value = {""} )
        private String  manufacture;
        /**    */
        @ExcelProperty(value = {""} )
        private String  modelno;
        /**    */
        @ExcelProperty(value = {""} )
        private String  photo;
        /**    */
        @ExcelProperty(value = {""} )
        private Long  companyid;
        /**  视频通道数  */
        @ExcelProperty(value = {"视频通道数"} )
        private Long  videochannelnum;
        /**    */
        @ExcelProperty(value = {""} )
        private String  username;
        /**    */
        @ExcelProperty(value = {""} )
        private Long  videodatatype;
        /**    */
        @ExcelProperty(value = {""} )
        private Long  channelid;
        /**    */
        @ExcelProperty(value = {""} )
        private String  videodisk;
        /**  视频通道名称  */
        @ExcelProperty(value = {"视频通道名称"} )
        private String  videochannelnames;
        /**  物联网卡号  */
        @ExcelProperty(value = {"物联网卡号"} )
        private String  flowrateno;
        /**  车辆登录密码  */
        @ExcelProperty(value = {"车辆登录密码"} )
        private String  vehiclepassword;
        /**  上级平台的营运类型代码    (必填)	  字典维护  */
        @ExcelProperty(value = {"上级平台的营运类型代码    (必填)	  字典维护"} )
        private String  supertranstype;
        /**  吨（座）位  */
        @ExcelProperty(value = {"吨（座）位"} )
        private String  seatingcapacity;
        /**  道路运输证号  */
        @ExcelProperty(value = {"道路运输证号"} )
        private String  transserialno;
        /**  营运线路  */
        @ExcelProperty(value = {"营运线路"} )
        private String  routename;
        /**  服务开始时间  */
        @ExcelProperty(value = {"服务开始时间"} )
        private Date  servicestartdate;
        /**  服务结束时间  */
        @ExcelProperty(value = {"服务结束时间"} )
        private Date  serviceenddate;
        /**  里程调整  */
        @ExcelProperty(value = {"里程调整"} )
        private String  mileageadjustment;
        /**  百公里油耗  */
        @ExcelProperty(value = {"百公里油耗"} )
        private String  fuelconsumption;
        /**  更新时间  */
        @ExcelProperty(value = {"更新时间"} )
        private Date  updatedate;
        /**  更新人  */
        @ExcelProperty(value = {"更新人"} )
        private Long  updatestaff;
        /**  创建人  */
        @ExcelProperty(value = {"创建人"} )
        private Long  createstaff;
        /**  车辆厂商  */
        @ExcelProperty(value = {"车辆厂商"} )
        private String  vehiclemanufacturer;
        /**    */
        @ExcelProperty(value = {""} )
        private Date  inlinedate;
        /**  同步时间  */
        @ExcelProperty(value = {"同步时间"} )
        private Date  synchronizeddate;
        /**  运营商id  */
        @ExcelProperty(value = {"运营商id"} )
        private Long  operatorid;


}