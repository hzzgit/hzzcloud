package com.hzz.hzzcloud.freemarker.dao;


import com.hzz.hzzcloud.freemarker.Vo.TableColumn;
import com.hzz.hzzcloud.freemarker.Vo.TableNameVo;
import com.hzz.hzzcloud.freemarker.Vo.TableVo;
import com.hzz.hzzjdbc.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzjdbc.jdbcutil.jdkjdbc.JdkDataSource;

import java.util.List;

public class FreeMarkDao {
    private MysqlDao mysqlDao = null;

    public FreeMarkDao() {
        mysqlDao = JdkDataSource.mysqldb;
    }

    //查询到这张表的每个字段
    public TableVo search(String table_schema, String table_name) {
        TableVo tableVo = new TableVo();
        tableVo.setTableschema(table_schema);
        tableVo.setTablename(table_name);
        String sql = "select table_schema tableschema,table_name tablename,\n" +
                "column_name columnname,column_key columnkey,data_type datatype,column_comment columncomment  from\n" +
                "information_schema.COLUMNS where table_schema =? and table_name=? ";
        List<TableColumn> query = mysqlDao.getMysqlUtil().query(sql, TableColumn.class, table_schema, table_name);
        String  pricolname =null;//主键名称
        String ordervbyname=null;
        for (TableColumn tableColumn : query) {
            tableColumn.setColumnname(tableColumn.getColumnname().toLowerCase());
        }
        for (TableColumn tableColumn : query) {
            if("PRI".equalsIgnoreCase(tableColumn.getColumnkey())){
                pricolname=tableColumn.getColumnname();
                break;
            }
        }

        for (TableColumn tableColumn : query) {
            if("createdate".equalsIgnoreCase(tableColumn.getColumnname().toLowerCase())){
                ordervbyname=tableColumn.getColumnname();
            }
            if("updatedate".equalsIgnoreCase(tableColumn.getColumnname().toLowerCase())){
                ordervbyname=tableColumn.getColumnname();
                break;
            }
        }

        tableVo.setOrdercol(ordervbyname);//排序字段
        tableVo.setPricolname(pricolname);
        tableVo.setTableColumnList(query);

        TableNameVo tableNameVo = searchTableName(table_schema, table_name);
        tableVo.setTableconment(tableNameVo.getTableconment());

        return tableVo;
    }

    //获取表注释
    private TableNameVo searchTableName(String table_schema, String table_name) {
        String sql = " select table_name tablename,table_comment tableconment from INFORMATION_SCHEMA.TABLES  where table_schema = ?\n" +
                "AND table_name =?";
        TableNameVo tableNameVo = mysqlDao.getMysqlUtil().queryFirst(sql, TableNameVo.class, table_schema, table_name);
        return tableNameVo;
    }

    public static void main(String[] args) {
        String sql = "select table_schema tableschema,table_name tablename,\n" +
                "column_name columnname,column_key columnkey,data_type datatype,column_comment columncomment  from\n" +
                "information_schema.COLUMNS where table_schema =? and table_name=? ";
        System.out.println(sql);
    }

}
