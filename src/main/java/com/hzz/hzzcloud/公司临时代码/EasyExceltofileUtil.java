package com.hzz.hzzcloud.公司临时代码;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.WriteSheet;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * easyexl导出到压缩包的的工具类
 */
@Slf4j
public class EasyExceltofileUtil {
    private static final Logger logger = LoggerFactory.getLogger(EasyExceltofileUtil.class);


    private ExcelWriter writer;
    private FileOutputStream fos = null;
    WriteSheet sheet1;
    private String filespathresult = "";
    private String filenamenew="";
    //批量写入的时候的初始值
    private int startco=0;
    //初始化导入
    public void exportfile(String fileName, String filepath, Class cls) {
        if(startco==0) {
            try {
                filespathresult = filepath + File.separator + fileName + getnowtime() + ExcelTypeEnum.XLSX.getValue();
                filenamenew=fileName + getnowtime() + ExcelTypeEnum.XLSX.getValue();
                //如果文件夹不存在就创建文件夹
                File folder = new File(filepath);
                if (!folder.exists() && !folder.isDirectory()) {
                    folder.mkdirs();
                }
                File file = new File(filespathresult);
                if (!file.exists()) {
                    file.createNewFile();
                }
                fos = new FileOutputStream(file);
                writer= EasyExcel.write(fos, cls).excelType(ExcelTypeEnum.XLSX).registerWriteHandler(new Custemhandler()).needHead(true).build();
                // 动态添加表头，适用一些表头动态变化的场景
                sheet1 = EasyExcel.writerSheet("sheet").build();

                // 将上下文中的最终 outputStream 写入到指定文件中
            } catch (Exception e) {
                logger.error("导出exl失败" + fileName, e);
            }
        }
    }

    //批量写入
    public void write(List alldata){
        writer.write(alldata,sheet1);
        startco=startco+alldata.size();
    }



    //写入完最后做的事情
    public String finishwrite() {
        try {
            writer.finish();

        } catch (Exception e) {
            log.error("exl导出失败",e);
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                log.error("exl导出失败",e);
            }
        }
        return filenamenew;
    }

    private String getnowtime() {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(new Date());
        return dateString;
    }

}
