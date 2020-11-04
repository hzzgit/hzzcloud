package com.hzz.hzzcloud.test;


import com.hzz.hzzjdbc.jdbcutil.jdkjdbc.JdkDataSource;
import com.hzz.hzzjdbc.jdbcutil.util.ConverMap;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Lambda表达式 {

    public static void main(String[] args) {
      JdkDataSource.jdkmysql();
        Student student = new Student();
        student.setId(254);
        student.setAge(12);
        student.setSex(false);
        student.setName("修改信息21212");
        student.setCreatedate(new Date());
        student.setBirthday(new Date());
        JdkDataSource.mysqldb.getMysqlUtil().update(student);
        String name= (String) JdkDataSource.mysqldb.getMysqlUtil().queryFirstVal("select name from student where id=254");
        System.out.println(name);
        String sq1l="select * from student";
        List<Student> query = JdkDataSource.mysqldb.getMysqlUtil().query(sq1l, Student.class);
        List<ConverMap> query1= JdkDataSource.mysqldb.getMysqlUtil().query(sq1l);
        query1.sort((p,v)-> (int) (p.getLong("age")-v.getLong("age")));
        query1.sort((p,v)-> (int) (-p.getLong("age")-v.getLong("age")));
        Map<String, List<Student>> collect = query.stream().
                sorted(Comparator.comparing(Student::getAge)).
                collect(Collectors.groupingBy(Student::getName));
        Map<Long,List<Student>> ageMapStudent=query.stream().sorted(Comparator.comparing(Student::getAge))
                .collect(Collectors.groupingBy(Student::getAge));
        Double collect1 = query.stream().collect(Collectors.averagingLong(Student::getAge));
        List<Double> doubles= Arrays.asList(1.2,4.5,212.33,131.44,2131.22,1.2);
        Double collect2 = doubles.stream().collect(Collectors.averagingDouble(Double::doubleValue));
        DoubleSummaryStatistics collect3 = doubles.stream().collect(Collectors.summarizingDouble(Double::doubleValue));
        doubles.stream().collect(Collectors.groupingByConcurrent(Double::doubleValue));
        Set<String> collect4 = query.stream().map(p -> p.getName()).collect(Collectors.toSet());
        List<Student> 测试0 = query.stream().filter(p -> p.getName().indexOf("测试")>-1==false).collect(Collectors.toList());
        Long collect5 = query.stream().collect(Collectors.summingLong(Student::getAge));
        long[] longs = query.stream().mapToLong(Student::getAge).toArray();
        List<Double> collect6 = doubles.stream().distinct().collect(Collectors.toList());
        List<String> collect7 = query.stream().map(Student::getName).collect(Collectors.toList());

        Date date = query.stream().filter(p->p.getCreatedate()!=null).map(TenCla::getCreatedate).max(Date::compareTo).get();
        Map<String, Boolean> collect8 = query.stream().map(Student::getName).distinct().collect
                (Collectors.toList()).stream().collect(Collectors.toMap(p -> p, p -> true));

         query.stream().collect(Collectors.groupingBy(student1 -> student1.getName()));
         String names="2121,3131,414,11,11,11";
        Map<String, Integer> collect9 = Stream.of(names.split(",")).distinct().collect(Collectors.toMap(Function.identity(), v -> 1));

       List<String> names1= query.stream().map(Student::getName).collect(Collectors.toList());
        Map<String, Integer> collect10 = names1.stream().distinct().collect(Collectors.toMap(p->p, p -> 1));


        List<Long> collect11 = query.stream().map(Student::getAge).filter(p->p>0).collect(Collectors.toList());
        Map<Long, Boolean> collect12 = collect11.stream().distinct().collect(Collectors.toMap(Function.identity(), p -> true));

        List<Student> collect13 = query.stream().filter(p -> p.getAge() > 1).collect(Collectors.toList());
        double asDouble = query.stream().mapToLong(Student::getAge).average().getAsDouble();
        Long aLong = query.stream().map(Student::getAge).max(Long::compareTo).get();
        Date date1 = query.stream().map(Student::getCreatedate).min(Date::compareTo).get();
        Date createdate = query1.stream().map(p -> p.getDate("createdate")).max(Date::compareTo).get();
        System.out.println(1);





    }



}
