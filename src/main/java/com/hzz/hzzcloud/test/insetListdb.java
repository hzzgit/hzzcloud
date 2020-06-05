package com.hzz.hzzcloud.test;

import com.hzz.hzzcloud.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzcloud.jdbcutil.jdkjdbc.JdkDataSource;
import com.hzz.hzzcloud.jdbcutil.util.ConverMap;
import com.hzz.hzzcloud.jdbcutil.vo.FieldVo;
import com.hzz.hzzcloud.jdbcutil.vo.PaginateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;

public class insetListdb {




    public static void main(String[] args) {
      JdkDataSource.jdkmysql();
        MysqlDao mysqlDao= JdkDataSource.mysqldb;

      Student student2121=new Student();
        student2121.setId(0);
        student2121.setName("ce");
        FieldVo getinsertsql = mysqlDao.getinsertsql(student2121);
        student2121.setAge(new Long(1121212112));
        FieldVo getinsertsql2 = mysqlDao.getinsertsql(student2121);
        List<FieldVo> fieldVos = Arrays.asList(getinsertsql, getinsertsql2);

        mysqlDao.excutesqlList(fieldVos);
    }

}
