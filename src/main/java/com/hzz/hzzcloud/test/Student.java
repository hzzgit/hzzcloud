package com.hzz.hzzcloud.test;

import com.hzz.hzzcloud.jdbcutil.annotation.DbTableId;
import com.hzz.hzzcloud.jdbcutil.annotation.DbTableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@DbTableName(value = "Student")
@Data
public class Student  extends TenCla implements Serializable {

    @DbTableId
    private Integer id;

    private String name;

    private long age;
    private Boolean sex;

    private Date birthday;

    //@DbColNUll
    private String test;

    private Date testtime;


    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", sex=" + sex +
                ", createdate=" + createdate +
                ", birthday=" + birthday +
                ", test='" + test + '\'' +
                '}';
    }
}
