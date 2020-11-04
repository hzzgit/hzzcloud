package com.hzz.hzzcloud.freemarker.FreeMarkConfig;


import com.hzz.hzzcloud.freemarker.Vo.PackageVo;
import com.hzz.hzzcloud.freemarker.Vo.PathVo;
import com.hzz.hzzcloud.freemarker.Vo.TableVo;
import com.hzz.hzzcloud.freemarker.dao.FreeMarkDao;
import com.hzz.hzzcloud.freemarker.emun.TemplateEnum;
import com.hzz.hzzcloud.freemarker.emun.WebEnum;
import com.hzz.hzzjdbc.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzjdbc.jdbcutil.jdkjdbc.JdkDataSource;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.util.List;

public class FreeMarkExcuter {

    private MysqlDao mysqlDao = null;
    private static String TEMPLATE_PATH = "D:\\hzzmysoft\\myspace\\hzzcloud\\src\\main\\java\\com\\hzz\\hzzcloud\\freemarker\\templates";

    private static final String TEMPLATE_PATH1 = "D:\\hzzmysoft\\myspace\\hzzcloud\\src\\main\\java\\com\\hzz\\hzzcloud\\freemarker\\templates";
    private static final String TEMPLATE_PATHNOCOMMON = "D:\\hzzmysoft\\myspace\\hzzcloud\\src\\main\\java\\com\\hzz\\hzzcloud\\freemarker\\templatesnocommon";
    private static final String TEMPLATE_TREE = "D:\\hzzmysoft\\myspace\\hzzcloud\\src\\main\\java\\com\\hzz\\hzzcloud\\freemarker\\templatestree";


    private static final String TEMPLATE_OTHER = "D:\\hzzmysoft\\myspace\\hzzcloud\\src\\main\\java\\com\\hzz\\hzzcloud\\freemarker\\templatesother";


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
     * @param table_schema     表所在数据库
     * @param table_name       表名
     * @param veanddepquanxian 车辆机构权限
     * @param depquanxian      机构权限,车辆机构权限为true的时候,这个不生效
     * @param templateEnum         使用继承模板还是原始模板,枚举类是TemplateEnum
     * @param isother          是否读取其他的模板文件夹
     */
    public void readTable(String table_schema, String table_name, boolean veanddepquanxian, boolean depquanxian, TemplateEnum templateEnum, boolean isother) {
        if (templateEnum == TemplateEnum.TEMPLATE_PATH1) {//这边是依赖common的版本
            TEMPLATE_PATH = TEMPLATE_PATH1;
        } else if (templateEnum == TemplateEnum.TEMPLATE_PATHNOCOMMON) {//这个是非依赖common的版本
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

        tableVo.setVeanddepquanxian(veanddepquanxian);//如果需要权限,那么就加入这个
        tableVo.setDepquanxian(depquanxian);
        PathVo pathVo = CreateDir(table_name);//各文件所在路径
        String packagetablename = packpath + "." + table_name;//这个是生成的文件夹的package名称
        PackageVo packageVo = new PackageVo(packagetablename + "." + WebEnum.entity.getValue(),
                packagetablename + "." + WebEnum.controller,
                packagetablename + "." + WebEnum.service.getValue(),
                packagetablename + "." + WebEnum.vo.getValue(),
                packagetablename + "." + WebEnum.exlvo.getValue());
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

        if (isother) {
            System.out.println("开始读取自定义模板");
            try {
                configuration.setDirectoryForTemplateLoading(new File(TEMPLATE_OTHER));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //自定义模板引擎,命名规则,文件名-文件类型,例如test-java.ftl
            List<String> otherfilenames = FileUtil.scanFilesWithRecursion(TEMPLATE_OTHER);
            if (otherfilenames != null && otherfilenames.size() > 0) {
                for (String otherfilename : otherfilenames) {
                    try {
                        String realpath = otherfilename.substring(0, otherfilename.indexOf("."));
                        String[] split = realpath.split("-");
                        String filetype = split[1];
                        String filename = split[0];
                        String classname = firstcolUp(tableVo.getTablename()) + filename;
                        tableVo.setClassname(classname);
                        tableVo.getPackageVo().setOtherpackname(packagetablename);
                        writetem(otherfilename,
                                pathVo.getAllpath() + File.separator + classname + "." + filetype,
                                tableVo);
                        System.out.println("生成自定义模板" + otherfilename + ",生成文件:" + pathVo.getAllpath() + File.separator + tableVo.getTablename() + filename + "." + filetype);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                }
            }
        }

        System.out.println(table_name + " 文件创建成功 !");

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
     * @param dirPath 文件夹路径
     */
    private  void deleteDir(String dirPath)
    {
        File file = new File(dirPath);
        if(file.isFile())
        {
            file.delete();
        }else
        {
            File[] files = file.listFiles();
            if(files == null)
            {
                file.delete();
            }else
            {
                for (int i = 0; i < files.length; i++)
                {
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
        String EntityName = table_name.substring(0, 1).toUpperCase() + table_name.substring(1);//首字符大写的名称
        return EntityName;
    }

}
