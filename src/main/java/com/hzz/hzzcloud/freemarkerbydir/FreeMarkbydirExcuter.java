package com.hzz.hzzcloud.freemarkerbydir;


import com.hzz.hzzcloud.freemarker.Vo.TableVo;
import com.hzz.hzzcloud.freemarker.dao.FreeMarkDao;
import com.hzz.hzzcloud.freemarkerbydir.dto.PathVoByDir;
import com.hzz.hzzjdbc.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzjdbc.jdbcutil.jdkjdbc.JdkDataSource;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class FreeMarkbydirExcuter {

    private MysqlDao mysqlDao = null;

    private String TEMPLATE_PATH = "D:\\hzzmysoft\\myspace\\hzzcloud\\src\\main\\resources\\templatesdir\\template";
    private String CLASS_PATH = "D:\\hzzmysoft\\myspace\\hzzcloud\\src\\main\\resources\\templatesdir\\main";

    public Configuration configuration = new Configuration();

    //存储模板和class所在位置
    private List<PathVoByDir> pathVoList = new ArrayList<PathVoByDir>();


    public FreeMarkbydirExcuter() {
        TEMPLATE_PATH = TEMPLATE_PATH.replace("\\", File.separator);
        CLASS_PATH = CLASS_PATH.replace("\\", File.separator);
        deleteDir(CLASS_PATH);
        File file = new File(CLASS_PATH);
        if (!file.exists()) {
            file.mkdir();
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
    public void readTable(String table_schema, String table_name, boolean veanddepquanxian, boolean depquanxian) {
        table_schema = table_schema.toLowerCase();
        table_name = table_name.toLowerCase();
        FreeMarkDao freeMarkDao = new FreeMarkDao();
        TableVo tableVo = freeMarkDao.search(table_schema, table_name);
        tableVo.setVeanddepquanxian(veanddepquanxian);//如果需要权限,那么就加入这个
        tableVo.setDepquanxian(depquanxian);


        String EntityName = firstcolUp(table_name);//首字符大写的名称
        tableVo.setEntityName(EntityName);//生成的实体类的类名,首字母大写

        createDir(tableVo, TEMPLATE_PATH);

        for (PathVoByDir pathVoByDir : pathVoList) {
            String classPath = pathVoByDir.getClassPath();
            classPath = classPath.replace(this.CLASS_PATH+File.separator, "");
            classPath = classPath.replace(File.separator, ".");
            classPath=classPath.substring(0,classPath.lastIndexOf("."));
            classPath=classPath.substring(0,classPath.lastIndexOf("."));
            tableVo.setPackpath(classPath);

            try {
                configuration.setDirectoryForTemplateLoading(new File(pathVoByDir.getTempPath()));
            } catch (IOException e) {
                e.printStackTrace();
            }

            writetem(pathVoByDir.getTempName(),
                    pathVoByDir.getClassPath(),
                    tableVo);//创建实体类
        }


        System.out.println(table_name + " 文件创建成功 !");


        pathVoList.clear();
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
        String EntityName = table_name.substring(0, 1).toUpperCase() + table_name.substring(1);//首字符大写的名称
        return EntityName;
    }

    /**
     * 根据模板创建文件夹
     */
    private void createDir(TableVo tableVo, String TEMPLATE_PATH) {
        File file = new File(TEMPLATE_PATH);
        File[] files = file.listFiles();
        for (File file1 : files) {
            boolean directory = file1.isDirectory();
            String path = file1.getPath();
            path = path.replace(this.TEMPLATE_PATH, "");
            path = checkfilename(path, tableVo);

            File file2 = new File(this.CLASS_PATH + File.separator + path);
            if (!directory) {
                path = path.replace(".ftl", "");
                if (path.indexOf("_") > -1) {
                    path = path.replace("_", ".");
                } else {
                    path = path + ".java";
                }

                file2 = new File(this.CLASS_PATH + File.separator + path);
            }
            if (directory) {
                file2.mkdir();
                createDir(tableVo, file1.getPath());
            } else {
                try {
                    PathVoByDir pathVo = new PathVoByDir();
                    pathVo.setClassPath(file2.getPath());
                    pathVo.setTempName(file1.getName());
                    pathVo.setTempPath(file1.getPath().replace(File.separator + file1.getName(), ""));
                    this.pathVoList.add(pathVo);
                    file2.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    private String checkfilename(String ftppath, TableVo tableVo) {
        Class<? extends TableVo> aClass = tableVo.getClass();
        for (Field declaredField : aClass.getDeclaredFields()) {
            String fieldName = declaredField.getName();
            Class<?> type = declaredField.getType();
            if (type == String.class) {
                String replateName = "${" + fieldName + "}";
                declaredField.setAccessible(true);
                try {
                    String o = (String) declaredField.get(tableVo);
                    if (o != null) {
                        ftppath = ftppath.replace(replateName, o);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return ftppath;
    }

}
