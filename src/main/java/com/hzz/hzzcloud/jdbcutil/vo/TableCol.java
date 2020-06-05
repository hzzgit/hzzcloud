package com.hzz.hzzcloud.jdbcutil.vo;

import lombok.Data;

@Data
public class TableCol {
    private String table_schema;
    private String table_name;
    private String column_name;
}
