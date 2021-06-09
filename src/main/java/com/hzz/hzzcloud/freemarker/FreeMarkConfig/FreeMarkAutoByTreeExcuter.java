package com.hzz.hzzcloud.freemarker.FreeMarkConfig;


import com.hzz.hzzcloud.freemarker.Vo.PackageVo;
import com.hzz.hzzcloud.freemarker.Vo.PathVo;
import com.hzz.hzzcloud.freemarker.Vo.TableVo;
import com.hzz.hzzcloud.freemarker.dao.FreeMarkDao;
import com.hzz.hzzcloud.freemarker.emun.WebEnum;
import com.hzz.hzzcloud.util.LineToHumpUtil;
import com.hzz.hzzcloud.工具.快速读取resources中的文件.FileLoaderUtil;
import com.hzz.hzzjdbc.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzjdbc.jdbcutil.jdkjdbc.JdkDataSource;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;

/**
 * 用来生成规则，以及机构授权，车辆授权，用户授权
 */
public class FreeMarkAutoByTreeExcuter {

    private MysqlDao mysqlDao = null;

    private  String TEMPLATE_TREE = "templatestree";

    private static final String CLASS_PATH = "D:\\hzzmysoft\\myspace\\hzzcloud\\src\\main\\java\\com\\hzz\\hzzcloud\\freemarker\\maintree";

    private static final String packpath = "com.hzz.hzzcloud.freemarker.maintree";

    public Configuration configuration = new Configuration();

    public FreeMarkAutoByTreeExcuter() {
        try {
            TEMPLATE_TREE = FileLoaderUtil.getFilePath(TEMPLATE_TREE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // step2 获取模版路径
        JdkDataSource.jdkmysql();
        mysqlDao = JdkDataSource.mysqldb;
    }

    //读取表进行模板生成

    /**
     * @param table_schema     表所在数据库
     * @param table_name       表名
     * @param veanddepquanxian 车辆机构权限
     * @param depquanxian      机构权限,车辆机构权限为true的时候,这个不生效
     */
    public void readTable(String table_schema, String table_name, boolean veanddepquanxian,
                          boolean depquanxian) {
        try {
            configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_TREE));
        } catch (IOException e) {
            e.printStackTrace();
        }
        table_schema = table_schema.toLowerCase();//所在数据库
        table_name = table_name.toLowerCase();//所在表
        FreeMarkDao freeMarkDao = new FreeMarkDao();//查询到所需要的表名表结构等
        TableVo tableVo = freeMarkDao.search(table_schema, table_name);//查询到规则的主表
        TableVo tableVobyDep = freeMarkDao.search(table_schema, table_name + "bydep");//查询到规则的主表对应的机构表
        TableVo tableVobyVehicle = freeMarkDao.search(table_schema, table_name + "byvehicle");//查询到规则的主表对应的车辆表
        TableVo tableVobyUserLim = freeMarkDao.search(table_schema, table_name+"userlim");//查询到规则的主表对应的用户授权表
        tableVo.setTableVobyDep(tableVobyDep);
        tableVo.setTableVobyVehicle(tableVobyVehicle);
        tableVo.setTableVobyUserLim(tableVobyUserLim);


        tableVo.setVeanddepquanxian(veanddepquanxian);//如果需要权限,那么就加入这个
        tableVo.setDepquanxian(depquanxian);

        PathVo pathVo = CreateDir(table_name);//创建文件夹，并返回各文件所在路径

        String packagetablename = packpath + "." + table_name;//这个是生成的文件夹的package名称

        PackageVo packageVo = new PackageVo(packagetablename + "." + WebEnum.entity.getValue(),
                packagetablename + "." + WebEnum.controller,
                packagetablename + "." + WebEnum.service.getValue(),
                packagetablename + "." + WebEnum.vo.getValue(),
                packagetablename + "." + WebEnum.exlvo.getValue());
        String EntityName = firstcolUp(table_name);//首字符大写的名称
        tableVo.setEntityName(EntityName);//生成的实体类的类名,首字母大写
        tableVobyDep.setEntityName(firstcolUp(table_name + "bydep"));//生成的实体类的类名,首字母大写
        tableVobyVehicle.setEntityName(firstcolUp(table_name + "byvehicle"));//生成的实体类的类名,首字母大写
        tableVobyUserLim.setEntityName(firstcolUp(table_name+"userlim"));//生成的实体类的类名,首字母大写



        tableVo.setPackageVo(packageVo);
        tableVo.setPackpath(packpath + "." + table_name);

        tableVobyDep.setPackageVo(packageVo);
        tableVobyDep.setPackpath(packpath + "." + table_name);

        tableVobyVehicle.setPackageVo(packageVo);
        tableVobyVehicle.setPackpath(packpath + "." + table_name);

        tableVobyUserLim.setPackageVo(packageVo);
        tableVobyUserLim.setPackpath(packpath + "." + table_name);



        writetemList(pathVo,EntityName,tableVo);//进行模板刻印


        System.out.println(table_name + " 文件创建成功 !");

    }

    /**
     * 根据文件模板进行批量创建
     */
    private void writetemList(PathVo pathVo,String EntityName,TableVo tableVo){

        writetem(WebEnum.entity.getValue() + ".ftl",
                pathVo.getEntitypath() + File.separator + EntityName + ".java",
                tableVo);//创建实体类

        writetem(WebEnum.entity.getValue() + ".ftl",
                pathVo.getEntitypath() + File.separator + EntityName + "bydep.java",
                tableVo.getTableVobyDep());//创建实体机构授权类

        writetem(WebEnum.entity.getValue() + ".ftl",
                pathVo.getEntitypath() + File.separator + EntityName + "byvehicle.java",
                tableVo.getTableVobyVehicle());//创建实体车辆授权类

        writetem(WebEnum.entity.getValue() + ".ftl",
                pathVo.getEntitypath() + File.separator + EntityName + "userlim.java",
                tableVo.getTableVobyUserLim());//创建实体用户授权类

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

    }


    /**
     * //写入模板
     *
     * @param ftlpath   模板路径
     * @param classpath 要写入的文件路径
     * @param tableVo   查出来的表的内容,字段名,表名注释等
     */
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


    /**
     * 创建文件夹
     * @param table_name
     * @return
     */
    private PathVo CreateDir(String table_name) {
        String allpath = CLASS_PATH + File.separator + table_name;
        String entitypath = CLASS_PATH + File.separator + table_name + File.separator + "entity";
        String Controllerpath = CLASS_PATH + File.separator + table_name + File.separator + "controller";
        String Servicepath = CLASS_PATH + File.separator + table_name + File.separator + "service";
        String Vopath = CLASS_PATH + File.separator + table_name + File.separator + "protocol";
        String exlVopath = CLASS_PATH + File.separator + table_name + File.separator + "exlvo";

        String[] paths = new String[]{allpath, entitypath, Controllerpath, Servicepath, Vopath, exlVopath};
        for (String path : paths) {
            deleteDir(path);
        }
        for (String path : paths) {
            File file = new File(path);
            if (!file.exists()) {
                file.mkdir();
            }
        }
        return new PathVo(allpath, entitypath, Controllerpath, Servicepath, Vopath, exlVopath);
    }

    /**
     * 迭代删除文件夹
     *
     * @param dirPath 文件夹路径
     */
    private void deleteDir(String dirPath) {
        File file = new File(dirPath);
        if (file.isFile()) {
            file.delete();
        } else {
            File[] files = file.listFiles();
            if (files == null) {
                file.delete();
            } else {
                for (int i = 0; i < files.length; i++) {
                    deleteDir(files[i].getAbsolutePath());
                }
                file.delete();
            }
        }
    }


    /**
     * 首字符大写
     *
     * @return
     */
    private String firstcolUp(String table_name) {
        return  LineToHumpUtil.lineToHump(table_name);
    }

    public static void main(String[] args) {
        String table_name="t_contract_ext_park";
        String EntityName = table_name.substring(0, 1).toUpperCase() + table_name.substring(1);//首字符大写的名称
        EntityName=    LineToHumpUtil.lineToHump(EntityName);
        if(EntityName.substring(0,1).equalsIgnoreCase("T")){
            EntityName=EntityName.substring(1);
        }
        System.out.println(EntityName);

    }

}
