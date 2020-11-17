package com.hzz.hzzcloud.工具.快速读取resources中的文件;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/11 10:26
 */
@Slf4j
public class WordUtil {



    /**
     * 导出word
     * <p>第一步生成替换后的word文件，只支持docx</p>
     * <p>第二步下载生成的文件</p>
     * <p>第三步删除生成的临时文件</p>
     * 模版变量中变量格式：{{foo}}
     *
     * @param templatePath word模板地址
     * @param temDir       生成临时文件存放地址
     * @param fileName     文件名
     * @param params       替换的参数
     */
    public static void exportWord(String templatePath, String temDir, String fileName,
                                  Map<String, Object> params) throws IOException {
        InputStream initialStream = FileLoaderUtil.getFile(templatePath);
        String filePath = FileLoaderUtil.getFilePath(templatePath);
        byte[] buffer = new byte[initialStream.available()];
        initialStream.read(buffer);
        File targetFile = new File(temDir+"/"+fileName);
        OutputStream outStream = new FileOutputStream(targetFile);
        outStream.write(buffer);
    }

    public static void main(String[] args) {
        Map params = new HashMap();
        params.put("name", "傻子");
        try {
            String filePath = FileLoaderUtil.getFilePath("doctemplate");
            System.out.println(filePath);
            // WordUtil.exportWord("doctemplate/exportword.docx", "D:/test", "aaa.docx", params);
//            WordUtil.exportWord("application.properties", "D:/test", "application2.properties", params);
//            WordUtil.exportWord("com\\hzz\\hzzcloud\\util\\RunCmdUtil.java", "D:/test", "RunCmdUtil.java", params);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
