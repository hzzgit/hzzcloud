package com.hzz.hzzcloud.freemarker.FreeMarkConfig;


import com.hzz.hzzcloud.freemarker.Vo.PackageVo;
import com.hzz.hzzcloud.freemarker.Vo.PathVo;
import com.hzz.hzzcloud.freemarker.Vo.TableNameVo;
import com.hzz.hzzcloud.freemarker.Vo.TableVo;
import com.hzz.hzzcloud.freemarker.dao.FreeMarkDao;
import com.hzz.hzzcloud.freemarker.emun.WebEnum;
import com.hzz.hzzcloud.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzcloud.jdbcutil.jdkjdbc.JdkDataSource;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

public class FreeMarkExcuter {

    private MysqlDao mysqlDao = null;
    private static  String TEMPLATE_PATH = "D:\\hzzmysoft\\myspace\\hzzcloud\\src\\main\\java\\com\\hzz\\hzzcloud\\freemarker\\templates";

    private static final String TEMPLATE_PATH1 = "D:\\hzzmysoft\\myspace\\hzzcloud\\src\\main\\java\\com\\hzz\\hzzcloud\\freemarker\\templates";
    private static final String TEMPLATE_PATHNOCOMMON = "D:\\hzzmysoft\\myspace\\hzzcloud\\src\\main\\java\\com\\hzz\\hzzcloud\\freemarker\\templatesnocommon";


    private static final String CLASS_PATH = "D:\\hzzmysoft\\myspace\\hzzcloud\\src\\main\\java\\com\\hzz\\hzzcloud\\freemarker\\main";
    private static final String packpath = "com.hzz.hzzcloud.freemarker.main";
    public Configuration configuration = new Configuration();

    public FreeMarkExcuter() {
        // step2 获取模版路径
            JdkDataSource.jdkmysql();
            mysqlDao = JdkDataSource.mysqldb;
    }

    //读取表进行模板生成

    /**
     *
     * @param table_schema 表所在数据库
     * @param table_name 表名
     * @param veanddepquanxian 车辆机构权限
     * @param depquanxian 机构权限,车辆机构权限为true的时候,这个不生效
     * @param iscommon 使用继承模板还是原始模板,一个是继承common,一个是没有
     */
    public void readTable(String table_schema, String table_name, boolean veanddepquanxian, boolean depquanxian,boolean iscommon) {
        if(iscommon){//这边是依赖common的版本
            TEMPLATE_PATH=TEMPLATE_PATH1;
        }else{//这个是非依赖common的版本
            TEMPLATE_PATH=TEMPLATE_PATHNOCOMMON;
        }
        try {
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
        } catch (IOException e) {
            e.printStackTrace();
        }
        table_schema = table_schema.toLowerCase();
        table_name = table_name.toLowerCase();
        FreeMarkDao freeMarkDao = new FreeMarkDao();
        TableVo tableVo = freeMarkDao.search(table_schema, table_name);
        TableNameVo tableNameVo = freeMarkDao.searchTableName(table_schema, table_name);
        tableVo.setTableconment(tableNameVo.getTableconment());

        tableVo.setVeanddepquanxian(veanddepquanxian);//如果需要权限,那么就加入这个
        tableVo.setDepquanxian(depquanxian);
        PathVo pathVo = CreateDir(table_name);//各文件所在路径
        PackageVo packageVo = new PackageVo(packpath + "." + table_name + "." + WebEnum.entity.getValue(),
                packpath + "." + table_name + "." + WebEnum.controller,
                packpath + "." + table_name + "." + WebEnum.service.getValue(),
                packpath + "." + table_name + "." + WebEnum.vo.getValue(),
                packpath + "." + table_name + "." + WebEnum.exlvo.getValue());
        String EntityName = firstcolUp(table_name);//首字符大写的名称

        tableVo.setEntityName(EntityName);//生成的实体类的类名,首字母大写
        tableVo.setPackageVo(packageVo);
        tableVo.setPackpath(packpath + "." + table_name);

        writetem(WebEnum.entity.getValue() + ".ftl",
                pathVo.getEntitypath() + File.separator + EntityName + ".java",
                tableVo);//创建实体类

        writetem(WebEnum.exlvo.getValue() + ".ftl",
                pathVo.getExlVopath() + File.separator + EntityName + "ExlVo.java",
                tableVo);//创建exl导出Vo类

        writetem(WebEnum.controller.getValue() + ".ftl",
                pathVo.getControllerpath() + File.separator + firstcolUp(tableVo.getTablename()) + firstcolUp(WebEnum.controller.getValue()) + ".java",
                tableVo);//创建controller

        writetem(WebEnum.service.getValue() + ".ftl",
                pathVo.getServicepath() + File.separator + firstcolUp(tableVo.getTablename()) + firstcolUp(WebEnum.service.getValue()) + ".java",
                tableVo);//创建service

        writetem(WebEnum.mapper.getValue() + ".ftl",
                pathVo.getAllpath() + File.separator + tableVo.getTablename() + WebEnum.mapper.getValue() + ".xml",
                tableVo);//创建mapper

        System.out.println(table_name + " 文件创建成功 !");

    }

    //写入模板
    private void writetem(String ftlpath, String classpath, TableVo tableVo) {
        Writer out = null;
        // step4 加载模版文件
        try {
            Template template = configuration.getTemplate(ftlpath);
            // step5 生成数据
            File docFile = new File(classpath);
            out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
            // step6 输出文件
            template.process(tableVo, out);
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


    private PathVo CreateDir(String table_name) {
        String allpath = CLASS_PATH + File.separator + table_name;
        String entitypath = CLASS_PATH + File.separator + table_name + File.separator + "entity";
        String Controllerpath = CLASS_PATH + File.separator + table_name + File.separator + "controller";
        String Servicepath = CLASS_PATH + File.separator + table_name + File.separator + "service";
        String Vopath = CLASS_PATH + File.separator + table_name + File.separator + "vo";
        String exlVopath = CLASS_PATH + File.separator + table_name + File.separator + "exlvo";

        String[] paths = new String[]{allpath, entitypath, Controllerpath, Servicepath, Vopath, exlVopath};
        for (String path : paths) {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        return new PathVo(allpath, entitypath, Controllerpath, Servicepath, Vopath, exlVopath);
    }

    /**
     * 首字符大写
     *
     * @return
     */
    private String firstcolUp(String table_name) {
        String EntityName = table_name.substring(0, 1).toUpperCase() + table_name.substring(1);//首字符大写的名称
        return EntityName;
    }

}
