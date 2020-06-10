package com.hzz.hzzcloud.test;

import com.hzz.hzzcloud.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzcloud.jdbcutil.jdkjdbc.JdkDataSource;
import com.hzz.hzzcloud.jdbcutil.util.ConverMap;
import com.hzz.hzzcloud.jdbcutil.vo.PaginateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class testdb {

    @Autowired
    private MysqlDao hzzdao;

    //@PostConstruct
    private void init(){

        List<Student> students=new ArrayList<>();
        for (int i = 0; i < 10; i++) {

            Student student=new Student();
            student.setId(0);
            student.setAge(i);
           // student.setSex(i);
            student.setName("测试"+i);
            student.setCreatedate(new Date());
            student.setBirthday(new Date());
            students.add(student);
        }
        try {
            hzzdao.insertList(students);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String s = UUID.randomUUID().toString().replaceAll("-","");
        System.out.println(s);
    }

    public static void main(String[] args) {
      JdkDataSource.jdkmysql();
        MysqlDao mysqlDao=  JdkDataSource.mysqldb;
      Student student2121=new Student();
        student2121.setId(0);
        student2121.setName("ce");
        JdkDataSource.mysqldb.insert(student2121);
        String SQL="select name from student";
        List<String> objects = JdkDataSource.mysqldb.queryFirstOne(SQL);
        SQL="select age from student";
        List<Integer> createdates = JdkDataSource.mysqldb.queryFirstOne(SQL);

        Integer date = createdates.get(0);
        ConverMap converMap1 = JdkDataSource.mysqldb.queryFirst("select * from student where id=255");
        SQL="select createdate from student";
        Date o = mysqlDao.queryFirstVal(SQL);

        List<Student> students=new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            Student student=new Student();
            student.setId(0);
            student.setAge(i);
            student.setSex(true);
            student.setName("测试"+i);
            student.setCreatedate(new Date());
            student.setBirthday(new Date());
            students.add(student);
        }
        try {
            JdkDataSource.mysqldb.insertList(students);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Student student=new Student();
        student.setId(0);
        student.setAge(2);
        student.setSex(true);
        student.setName("单一插入,返回主键");
        student.setCreatedate(new Date());
        student.setBirthday(new Date());
        JdkDataSource.mysqldb.insert(student,true);
        System.out.println(student);

         student=new Student();
        student.setId(0);
        student.setAge(2);
        //student.setSex(1);
        student.setName("单一插入,不返回主键");
        student.setCreatedate(new Date());
        student.setBirthday(new Date());
        JdkDataSource.mysqldb.insert(student);
        student=new Student();
        student.setId(118);
        student.setAge(2);
       // student.setSex(1);
        student.setName("单一插入,不返回主键107");
        student.setCreatedate(new Date());
        student.setBirthday(new Date());
        JdkDataSource.mysqldb.update(student);


      String sql="select * from student";
        JdkDataSource.mysqldb.ConsumeQuery(sql,Student.class,student1 -> {
            System.out.println(student1);
        });


        List<ConverMap> query = JdkDataSource.mysqldb.query(sql);
        for (ConverMap converMap : query) {
            System.out.println(converMap);
        }
        List<Student> query2 = JdkDataSource.mysqldb.query(sql,Student.class);
        for (Student student1 : query2) {
            System.out.println(student1);
        }

        PaginateResult query3 = JdkDataSource.mysqldb.queryPage(sql,Student.class,1,10);
        System.out.println(query3);

        PaginateResult query4 = JdkDataSource.mysqldb.queryPage(sql,1,10);
        System.out.println(query4);



        List names= Arrays.asList("测试1");
        String sql2="select * from student where name in("+JdkDataSource.mysqldb.arrtoStr(names)+")  or name like ? ";
        List<ConverMap> query1 = JdkDataSource.mysqldb.query(sql2,"测试0");
        for (ConverMap converMap : query1) {
            System.out.println(converMap);
        }

//        String sql="select * from student where sex=? and name like ? ";
//        JdkDataSource.mysqldb.ConsumeQuery(sql,ConverMap.class,converMap -> {
//            System.out.println(converMap);
//            List<String> name1 = Stream.of(converMap).map(p -> p.getString("name")).collect(Collectors.toList());
//            System.out.println(1);
//        },0,"%测试%");

    }

}
