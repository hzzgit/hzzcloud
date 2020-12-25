package com.hzz.hzzcloud.公司临时代码.根据车牌号和入网时间获取到结果集到exl.金融报表根据车牌号找到对应的经纬度和时间;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@Data
@HeadRowHeight(35)
public class LocationVo {

    @ExcelProperty(value = {"纬度"})
    private double lat;
    @ExcelProperty(value = {"经度"})
    private double lon;

    @ExcelProperty(value = {"时间"})
    private String time;

    public static void main(String[] args) {
        String tempStr="测试黄绿";
        String replace = tempStr.trim().replace("黄", "").replace("绿", "").replace("蓝", "").replace("白", "").replace("红", "");
        System.out.println(replace);

    }
}