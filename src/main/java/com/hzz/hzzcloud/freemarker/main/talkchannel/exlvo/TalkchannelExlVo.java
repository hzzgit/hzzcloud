package com.hzz.hzzcloud.freemarker.main.talkchannel.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class TalkchannelExlVo  extends BaseRowModel  {

    @ExcelProperty(value = {"序号"} )
    private int id;
    /**  频道Id  */
    @ExcelProperty(value = {"频道Id"} )
    private String  channelid;
    /**  频道名称  */
    @ExcelProperty(value = {"频道名称"} )
    private String  channelname;
    /**  创建时间  */
    @ExcelProperty(value = {"创建时间"} )
    private Date  createdate;
    /**  成员数量  */
    @ExcelProperty(value = {"成员数量"} )
    private Long  membernum;
    /**  频道备注  */
    @ExcelProperty(value = {"频道备注"} )
    private String  remark;
    /**  创建用户id  */
    @ExcelProperty(value = {"创建用户id"} )
    private Long  owner;
    /**  状态,0,正常,4禁用  */
    @ExcelProperty(value = {"状态,0,正常,4禁用"} )
    private Long  status;
    /**  类型,0频道(该字段暂时无用,预留)  */
    @ExcelProperty(value = {"类型,0频道(该字段暂时无用,预留)"} )
    private Long  type;
    /**  0代表未删除,1以上代表删除  */
    @ExcelProperty(value = {"0代表未删除,1以上代表删除"} )
    private Long  deleted;
    /**  更新时间  */
    @ExcelProperty(value = {"更新时间"} )
    private Date  updatedate;


}