package com.hzz.hzzcloud.公司临时代码.查询到主键用于测试;

import com.hzz.hzzjdbc.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzjdbc.jdbcutil.jdkjdbc.JdkDataSource;

import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/15 15:01
 */
public class maintest {

    public static void main(String[] args) {
        JdkDataSource.jdkmysql();
        MysqlDao mysqlDao=  JdkDataSource.mysqldb;
        List<Object> objects = mysqlDao.queryFirstOne("select id from gps_hisdata.car_duration   limit 10000 ");
        String ids="";
        for (Object object : objects) {
            ids+=object+",";
        }
        System.out.println(ids);
    }
}
