package com.hzz.hzzcloud.test;


import com.hzz.hzzjdbc.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzjdbc.jdbcutil.jdkjdbc.JdkDataSource;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class insetListdb {




    public static void main(String[] args) throws  Exception {
      JdkDataSource.jdkmysql();
        MysqlDao mysqlDao= JdkDataSource.mysqldb;

      System.out.println(1);
      Double a=new Double(1.2);
      Double a1=new Double(2.2);
      long address = Addresser.addressOf(a);
      System.out.println("Addess: " + address);

      long address1 = Addresser.addressOf(a1);
      System.out.println("Addess: " + address1);
      Console console = System.console();
      Properties properties = System.getProperties();
      List<String> stringList=new ArrayList<>();

      System.out.println(1);
      byte sa=1;

    }

}
