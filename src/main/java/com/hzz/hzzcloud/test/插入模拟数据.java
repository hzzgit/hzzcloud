package com.hzz.hzzcloud.test;

import com.hzz.hzzjdbc.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzjdbc.jdbcutil.jdkjdbc.JdkDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/2 15:59
 */
public class 插入模拟数据 {

    public static void main(String[] args) {
        JdkDataSource.jdkmysql();
        MysqlDao mysqlDao = JdkDataSource.mysqldb;

        String sql1="select * from videofileitem limit 1";
        Videofileitem videofileitem1 = mysqlDao.getMysqlUtil().queryFirst(sql1, Videofileitem.class);


        for (int j = 0; j < 10000; j++) {
              long s = System.currentTimeMillis();   //获取开始时间
            List<Videofileitem> videofileitems=new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                videofileitem1.setBaseid(new Long(0));
                videofileitems.add(videofileitem1);
            }
            try {
                mysqlDao.getMysqlUtil().insertList(videofileitems);
            } catch (Exception e) {
                e.printStackTrace();
            }

            long e = System.currentTimeMillis(); //获取结束时间
            System.out.println( "用时：" + (e - s) + "ms");
        }
    }
}
