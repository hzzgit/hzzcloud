package com.hzz.hzzcloud.从exl中读取数据生成实体类;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/10 9:19
 */
@Data
public class ReadExlToBean extends BaseRowModel {

    //第1列数据 ，
    @ExcelProperty(value = "字段名")
    private String colname;
    @ExcelProperty(value = "名称")
    private String name;
    @ExcelProperty(value = "类型")
    private String type;
    @ExcelProperty(value = "长度")
    private String lengths;
    @ExcelProperty(value = "约束")
    private String required;
    @ExcelProperty(value = "注释")
    private String annotion;
}
