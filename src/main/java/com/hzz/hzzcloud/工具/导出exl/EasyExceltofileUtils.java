package com.hzz.hzzcloud.工具.导出exl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import lombok.extern.slf4j.Slf4j;
import net.fxft.ascswebcommon.util.easyexcel.EasyExlRead.Custemhandler;
import net.fxft.ascswebcommon.web.util.EasyExcelUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class EasyExceltofileUtils {

    /**
     * 根据表头，数据，文件名称，进行exl的导出，xls格式
     *
     * @param fileName 文件名
     * @param heads    标题
     * @param alldata  实际数据
     */
    public static void exlport(String fileName, String filepath, List<List<String>> heads, List<List<Object>> alldata) {
//        if(alldata.size()<50000){
//            exportfile(fileName,request,response,heads,alldata,".xls",ExcelTypeEnum.XLS);
//        }else{
        exportfile(fileName, filepath, heads, alldata, ".xlsx", ExcelTypeEnum.XLSX);
        //  }
    }


    private static void exportfile(String fileName, String filepath, List<List<String>> heads, List<List<Object>> alldata, String filetype, ExcelTypeEnum exltype) {
        FileOutputStream fos = null;
        try {

            File file = new File(filepath + "//"+fileName+filetype);
            if (!file.exists()) {
                file.createNewFile();
            }
            fos = new FileOutputStream(file);

            List<List<String>> heads2=new ArrayList<>();
            for (List<String> head : heads) {
                List<String> head1=new ArrayList<>();
                for (String s : head) {
                    head1.add(s);
                    head1.add(s);
                    head1.add(s);
                    head1.add(s);
                }
                heads2.add(head1);
            }


            // 头的策略
            WriteCellStyle headWriteCellStyle = new WriteCellStyle();
            WriteFont headWriteFont = new WriteFont();
            headWriteFont.setFontHeightInPoints((short)12);
            headWriteFont.setBold(true);
            headWriteCellStyle.setWriteFont(headWriteFont);
            //设置 自动换行
            headWriteCellStyle.setWrapped(true);


            // 内容的策略
            WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
            WriteFont contentWriteFont = new WriteFont();
            // 字体大小
            contentWriteFont.setFontHeightInPoints((short)12);
            contentWriteCellStyle.setWriteFont(contentWriteFont);
            // 这个策略是 头是头的样式 内容是内容的样式 其他的策略可以自己实现
            HorizontalCellStyleStrategy horizontalCellStyleStrategy =
                    new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle);


            EasyExcel.write(fos).needHead(true).excelType(exltype).
                    registerWriteHandler(new Custemhandler()).registerWriteHandler(horizontalCellStyleStrategy).
                    sheet("sheet").head(heads2).doWrite(alldata);

        } catch (Exception e) {
            log.error("导出exl失败" + fileName, e);
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //获取表头
    public  static  List<List<String>> gethead() {
        List headdata = new ArrayList();
        String[] row2 = new String[]{"车牌号"};
        headdata.add(row2);
        return EasyExcelUtil.gethead(headdata, 1);
    }

    public static void main(String[] args) {
        List<List<String>> gethead = EasyExceltofileUtils.gethead();
        List<List<Object>> exldatas = new ArrayList<>();
        List<Object> data=new ArrayList<>();
        data.add("性能");
        exldatas.add(data);
        EasyExceltofileUtils.exlport("exl", "D://test", gethead, exldatas);//调用easyexl模版进行导出exl特定标题的数据

    }
}
