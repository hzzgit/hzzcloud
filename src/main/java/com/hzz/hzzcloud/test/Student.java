package com.hzz.hzzcloud.test;


import com.hzz.hzzjdbc.jdbcutil.annotation.DbTableId;
import com.hzz.hzzjdbc.jdbcutil.annotation.DbTableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@DbTableName(value = "Student")
@Data
public class Student  extends TenCla implements Serializable,StudentInter {

    @DbTableId
    private Integer id;

    private String name;

    private long age;
    private Boolean sex;

    private Date birthday=new Date();

    //@DbColNUll
    private String test="scs";

    private Date testtime=new Date();


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
