package com.hzz.hzzcloud.从exl中读取数据生成实体类;

import com.hzz.hzzcloud.工具.快速读取exl.ExlReadUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/10 9:23
 */
public class ReadExlMain {

    public static Configuration configuration = new Configuration();


    public static  String classPath="D:\\mycode\\hzzcode\\hzzcloud\\src\\main\\resources\\要读取实体类的exl\\main\\";

    public static void main(String[] args) {
        List<ReadExlToBean> objects = ExlReadUtil.simpleRead(ReadExlToBean.class, "D:\\mycode\\hzzcode\\hzzcloud\\src\\main\\resources\\要读取实体类的exl\\读取exl转bean.xlsx");
        for (ReadExlToBean object : objects) {
            object.setAnnotion(object.getAnnotion()!=null?object.getAnnotion().trim().replaceAll("\n"," "):"");
            object.setColname(object.getColname()!=null?object.getColname().trim():"");
            object.setName(object.getName()!=null?object.getName().trim():"");
            object.setType(object.getType()!=null?object.getType().trim():"");
            object.setRequired(object.getRequired()!=null?object.getRequired().trim():"");
        }
        ReadExlToBeanVO readExlToBeanVO=new ReadExlToBeanVO();
        readExlToBeanVO.setReadExlToBeanList(objects);
        writetem("${entityName}.ftl","QueryOrdersVO1.java",readExlToBeanVO);



    }


    /**
     * //写入模板
     *
     * @param ftlpath   模板路径
     * @param className 要写入的文件名
     * @param readExlToBeanVO   查出来的表的内容,字段名,表名注释等
     */
    public static void writetem(String ftlpath, String className, ReadExlToBeanVO readExlToBeanVO) {
        try {
            configuration.setDirectoryForTemplateLoading(new File("D:\\mycode\\hzzcode\\hzzcloud\\src\\main\\resources\\要读取实体类的exl\\ftl"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        readExlToBeanVO.setEntityName(className.replaceAll(".java",""));
        String classpath =classPath+className;
        File files=new File(classPath);
        File[] files1 = files.listFiles();
        for (File file : files1) {
            file.delete();
        }
        Writer out = null;
        // step4 加载模版文件
        try {
            Template template = configuration.getTemplate(ftlpath);
            // step5 生成数据
            System.out.println(classpath+"--创建路径");
            File docFile = new File(classpath);
            if (!docFile.exists()) {
                docFile.createNewFile();
            }
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(readExlToBeanVO, out);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != out) {
                    out.flush();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

    }

}
