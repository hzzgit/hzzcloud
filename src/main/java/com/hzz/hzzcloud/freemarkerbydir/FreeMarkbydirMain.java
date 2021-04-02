package com.hzz.hzzcloud.freemarkerbydir;

import com.hzz.hzzjdbc.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzjdbc.jdbcutil.jdkjdbc.JdkDataSource;
import com.hzz.hzzjdbc.jdbcutil.util.ConverMap;

import java.util.List;

/**
 * freeMark模板自动生成文件工具,这边是生成常规的增删改查-导出模板
 */
public class FreeMarkbydirMain {



    public static void main(String[] args) {
//        JdkDataSource.jdkmysql();
//        MysqlDao mysqldb = JdkDataSource.mysqldb;
//        List<ConverMap> query = mysqldb.query("select table_schema as tableschema,table_name as tablename from INFORMATION_SCHEMA.TABLES\n" +
//                "where table_schema in('subiaodb','gps_hisdata') ");
//
//        for (ConverMap converMap : query) {
//            String table_schema = converMap.getString("tableschema");
//            String tablename = converMap.getString("tablename");
//            freeMarkbydirExcuter.readTable(table_schema, tablename,
//                    false,false);
//        }
        FreeMarkbydirExcuter freeMarkbydirExcuter=new FreeMarkbydirExcuter();


        freeMarkbydirExcuter.readTable("subiaodb", "config_table",
                false,false);
        freeMarkbydirExcuter.readTable("subiaodb", "config_table_filed",
                false,false);
    }

}
