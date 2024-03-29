package com.hzz.hzzcloud.freemarker.Vo;

import lombok.Data;

@Data
public class TableColumn {

    private String tableschema;
    private String tablename;
    private String columnname;//字段名
    private String columnkey; //主键,外键等
    private String datatype; //字段类型
    private String columncomment;//字段注释

}
