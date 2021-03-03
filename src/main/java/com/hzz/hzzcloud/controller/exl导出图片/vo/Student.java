package com.hzz.hzzcloud.controller.exl导出图片.vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.alibaba.excel.annotation.write.style.ContentRowHeight;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import com.alibaba.excel.metadata.BaseRowModel;
import com.hzz.hzzcloud.controller.exl导出图片.MyStringImageConverter;
import lombok.Data;

import java.net.URL;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/3/3 17:01
 */
@Data
@ColumnWidth(20)
@HeadRowHeight(35)
@ContentRowHeight(300)
public class Student extends BaseRowModel {
    @ColumnWidth(30)
    @ExcelProperty(value = {"实时定位", "纬度"})
    private String latitude;

    @ColumnWidth(120)
    @ExcelProperty(value = {"实时定位", "图片"},converter = MyStringImageConverter.class)
    private String  image1;

}
