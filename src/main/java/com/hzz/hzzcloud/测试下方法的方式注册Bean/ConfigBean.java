package com.hzz.hzzcloud.测试下方法的方式注册Bean;

import com.hzz.hzzcloud.test.Student;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/7 15:13
 */
@Configuration
public class ConfigBean {


    @Primary
    @Qualifier("stu1")
    @Bean("stu1")
    Student getstudent() {
        Student student = new Student();
        student.setName("测");
        return student;
    }

    @Qualifier("stu2")
    @Bean("stu2")
    Student getstu2(){
        Student student = new Student();
        student.setName("测2");
        return student;
    }

    @Bean
    School geschool() {
        Student getstudent = getstudent();
        Student student = getstu2();
        return  new School();
    }
}
