package com.hzz.hzzcloud.test;

import com.hzz.hzzcloud.jdbcutil.annotation.DbColNUll;
import lombok.Data;

import java.util.Date;

@Data
public class TenCla {
    protected Date createdate;

    @DbColNUll
    public String na;
}
