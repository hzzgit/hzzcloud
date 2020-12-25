package com.hzz.hzzcloud.公司临时代码.金融报表风控业务.快速读取exl;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.metadata.BaseRowModel;
import com.alibaba.excel.metadata.Sheet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/25 15:26
 */
public class ExlReadUtil {

    // 简单读取 (同步读取)
    public static<T> List<T> simpleRead(Class<? extends BaseRowModel> excelMode, String readPath) {
        try {
            // sheetNo --> 读取哪一个 表单
            // headLineMun --> 从哪一行开始读取( 不包括定义的这一行，比如 headLineMun为2 ，那么取出来的数据是从 第三行的数据开始读取 )
            // clazz --> 将读取的数据，转化成对应的实体，需要 extends BaseRowModel
            Sheet sheet = new Sheet(1, 1, excelMode);
            // 这里 取出来的是 ExcelModel实体 的集合
            List<T> readList = (List<T>) EasyExcelFactory.read(new FileInputStream(readPath), sheet);
            return  readList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return  null;

    }
}
