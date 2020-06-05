package com.hzz.hzzcloud.jdbcutil.jdkjdbc;


import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.hzz.hzzcloud.jdbcutil.config.SpringConnectionSource;
import com.hzz.hzzcloud.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzcloud.jdbcutil.dbmain.Mysqldb;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class JdkDataSource {

     public static MysqlDao mysqldb;

    private static final String ORACLE = "oracleDataSource.properties";
    private static final String MYSQL = "DataSource.properties";


    public static  void jdkmysql(){
        new JdkDataSource().createdateSource();
    }

    // 创建连接池
    private  void createdateSource() {

        Properties properties = new Properties();
        try {
            // properties.load(config.class.getResourceAsStream("/oracleDataSource.properties"));//这个是读取linux的class路径
            // properties.load(config.class.getResourceAsStream("/oracleDataSource.properties"));//这是读取src路径下
            try {
                String realPath = this.getClass().getClassLoader().getResource(MYSQL).getFile();
                properties.load(new FileInputStream(realPath));// 这个是web项目用的
            } catch (IOException e) {
                properties.load(new FileInputStream(MYSQL));// 这是直接读取项目下面的

            }
            DataSource dataSource = DruidDataSourceFactory.createDataSource(properties);
            String rawJdbcUrl = ((DruidDataSource) dataSource).getRawJdbcUrl();
            mysqldb= new Mysqldb(dataSource,new SpringConnectionSource(dataSource,false),rawJdbcUrl);

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
