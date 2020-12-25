package com.hzz.hzzcloud.公司临时代码.金融报表风控业务.快速读取exl.Vo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.Data;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/25 15:25
 */
@Data
public class PlateVo extends BaseRowModel {
    //第1列数据 ，车牌号
    @ExcelProperty(index = 0)
    private String plateNo;


    //第2列数据 ，服务开始时间
    @ExcelProperty(index = 1)
    private String startTime;

    //第3列数据 ，服务结束时间
    @ExcelProperty(index = 2)
    private String endTime;
}
