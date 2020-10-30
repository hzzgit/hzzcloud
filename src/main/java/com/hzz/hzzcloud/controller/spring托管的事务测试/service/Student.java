package com.hzz.hzzcloud.controller.spring托管的事务测试.service;

import com.hzz.hzzcloud.jdbcutil.annotation.DbTableId;
import com.hzz.hzzcloud.jdbcutil.annotation.DbTableName;
import lombok.Data;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/10/29 17:19
 */
@DbTableName("student")
@Data
public class Student {

    @DbTableId
    private Integer id;

    private String name;
    private int age;
}
