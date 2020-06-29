package com.hzz.hzzcloud.freemarker.main.alarmgrantertopic.exlvo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

import java.util.Date;

@Data
@HeadRowHeight(35)
public class AlarmgrantertopicExlVo  extends BaseRowModel  {

    @ExcelProperty(value = {"序号"} )
    private int id;
    /**  公司名称  */
    @ExcelProperty(value = {"公司名称"} )
    private String  name;
    /**  topic名称,这边要在新增的时候,自动生成一个字符串.唯一字符串.可以使用uuid  */
    @ExcelProperty(value = {"topic名称,这边要在新增的时候,自动生成一个字符串.唯一字符串.可以使用uuid"} )
    private String  topicname;
    /**  创建时间  */
    @ExcelProperty(value = {"创建时间"} )
    private Date  createdate;
    /**  修改时间  */
    @ExcelProperty(value = {"修改时间"} )
    private Date  updatedate;
    /**  删除标志,0未删除,1已删除  */
    @ExcelProperty(value = {"删除标志,0未删除,1已删除"} )
    private String  deleted;
    /**  是否开启,0关闭,1开启  */
    @ExcelProperty(value = {"是否开启,0关闭,1开启"} )
    private String  isuse;
    /**  申请转发开通的时候选择的机构id,唯一的,只能有一条有这个机构  */
    @ExcelProperty(value = {"申请转发开通的时候选择的机构id,唯一的,只能有一条有这个机构"} )
    private Long  depid;
    /**  申请转发开通的时候选择的用户ID,用户已经申请转发开通之后,不能再进行申请  */
    @ExcelProperty(value = {"申请转发开通的时候选择的用户ID,用户已经申请转发开通之后,不能再进行申请"} )
    private Long  userid;


}