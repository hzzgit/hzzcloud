package com.hzz.hzzcloud.test;

import com.hzz.hzzcloud.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzcloud.jdbcutil.jdkjdbc.JdkDataSource;
import com.hzz.hzzcloud.jdbcutil.util.ConverMap;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class 生成插入网关语句 {
    public static void main(String[] args) {
        JdkDataSource.jdkmysql();
        MysqlDao mysqlDao=  JdkDataSource.mysqldb;

        String sql="SELECT * from web_route_config";
        List<ConverMap> query = mysqlDao.query(sql);
        for (ConverMap converMap : query) {
            String path_name = converMap.getString("path_name");
            String orders = converMap.getString("orders");
            String flag = converMap.getString("flag");
            String fliter_name = converMap.getString("fliter_name");
            String url = converMap.getString("url");
            String remark = converMap.getString("remark");
            String name="INSERT INTO `subiaodb`.`web_route_config`(`id`, `url`, `orders`, `path_name`, `fliter_name`, `flag`, `remark`) " +
                    "select 0, '"+url+"', '"+orders+"', '"+path_name+"', '"+fliter_name+"', '"+flag+"', '"+remark+"' FROM\n" +
                    "  DUAL  where NOT EXISTS (\n" +
                    "    SELECT\n" +
                    "      1\n" +
                    "    FROM\n" +
                    "      subiaodb.web_route_config\n" +
                    "    WHERE 1=1 \n" +
                    "    and  url = '"+url+"'\n" +
                    "    and  fliter_name = '"+fliter_name+"'\n" +
                    "    and  path_name = '"+path_name+"'\n" +
                    "    AND remark = '"+remark+"' \n" +
                    "  );";
            System.out.println(name);

        }
    

    }
}
