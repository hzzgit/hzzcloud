package com.hzz.hzzcloud.公司临时代码.根据车牌号和入网时间获取到结果集到exl;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.HeadRowHeight;
import lombok.Data;

@Data
@HeadRowHeight(35)
public class reportExlMobanVo {

    @ExcelProperty(value = {"月度上线天数"})
    private int type1cn;
    @ExcelProperty(value = {"月度运营状态天数"})
    private int type2cn;

    @ExcelProperty(value = {"月度有效运营天数"})
    private int type3cn;
    @ExcelProperty(value = {"总里程(米)"})
    private double type4cn;


    public static void main(String[] args) {
        String tempStr="测试黄绿";
        String replace = tempStr.trim().replace("黄", "").replace("绿", "").replace("蓝", "").replace("白", "").replace("红", "");
        System.out.println(replace);

    }
}