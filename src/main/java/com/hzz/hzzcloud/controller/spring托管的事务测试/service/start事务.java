package com.hzz.hzzcloud.controller.spring托管的事务测试.service;

import com.hzz.hzzcloud.jdbcutil.dbmain.MysqlDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/10/29 17:11
 */
@Service
public class start事务 {


    @Autowired
    private MysqlDao mysqlDao;

    @PostConstruct
    public void starttran(){
             new Thread(()->{
                             while (true){
                                 search();
                                 try {
                                     Thread.sleep(1000);
                                 } catch (InterruptedException e) {
                                     e.printStackTrace();
                                 }
                             }
                     }).start();
                  new Thread(()->{
                      for (int i = 0; i < 50; i++) {
                        Student Student=new Student();
                        Student.setId(i);
                          Student.setName("测试"+i);
                          Student.setAge(i);
                          mysqlDao.insert(Student);
                          try {
                              Thread.sleep(1000);
                          } catch (InterruptedException e) {
                              e.printStackTrace();
                          }
                      }
                      
                          }).start();
    }

    @Transactional
    public void search(){
        Integer o = mysqlDao.queryFirstVal("select count(1) from student");
        System.out.println(o);
    }
}
