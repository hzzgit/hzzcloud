package com.hzz.hzzcloud.公司临时代码.用来插入对应厂家型号和设备编码的通道号;

import com.hzz.hzzjdbc.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzjdbc.jdbcutil.jdkjdbc.JdkDataSource;
import com.hzz.hzzjdbc.jdbcutil.util.ConverMap;

import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/6 11:20
 */
public class test {

    public static void main(String[] args) {
        JdkDataSource.jdkmysql();
        MysqlDao mysqldb = JdkDataSource.mysqldb;


        List<ConverMap> query = mysqldb.query("select factory_code,model from terminal_model where deleted=false ");
        for (ConverMap converMap : query) {
            String factory_code = converMap.getString("factory_code");
            String model = converMap.getString("model");
            String insertsql="INSERT INTO `subiaodb`.`imggathermodelchannel`(`id`, `model`, `factoryCode`, `channelId`) VALUES (0, ?, ?, 1)\n";
            mysqldb.excutesql(insertsql,model,factory_code);
        }


    }
}
