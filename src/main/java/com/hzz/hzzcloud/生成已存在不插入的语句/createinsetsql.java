package com.hzz.hzzcloud.生成已存在不插入的语句;

import com.hzz.hzzcloud.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzcloud.jdbcutil.jdkjdbc.JdkDataSource;
import com.hzz.hzzcloud.jdbcutil.util.ConverMap;

import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/7/30 11:45
 */
public class createinsetsql {


    public static MysqlDao mysqldb = null;

    public createinsetsql() {
        mysqldb = JdkDataSource.jdkmysql();
    }


    public String createinsert(String table_schema, String table_name, String wheredata) {
        String sql = "select column_name  from\n" +
                "information_schema.COLUMNS where table_schema not in ('mysql','information_schema','performance_schema','sys') \n" +
                "and table_schema =? and table_name=?";
        List<String> query = mysqldb.queryFirstOne(sql, table_schema, table_name);

        String sql2 = "select * from  " + table_schema + "." + table_name + "  " + wheredata;
        ConverMap converMap = mysqldb.queryFirst(sql2);
        String[] filenamessplit = query.toArray(new String[query.size()]);
        String[] valuessplit = new String[converMap.size()];
        for (int i = 1; i < filenamessplit.length; i++) {
            String string = converMap.getString(filenamessplit[i])  ;
            if("true".equalsIgnoreCase(string)||"false".equalsIgnoreCase(string)){
                valuessplit[i] = string;
            }
            else if (!string.equalsIgnoreCase("null")) {
                valuessplit[i] = "'" + string + "'";

            } else {
                valuessplit[i] = string;

            }
        }

        String filenamesql = " INSERT INTO " + table_schema + "." + table_name + "(";
        String valsql = "select ";

        String selecttablesql = " ( select 1 from  " + table_schema + "." + table_name + " where 1=1  ";

        boolean isfirst=false;
        for (int i = 1; i < filenamessplit.length; i++) {

            String filename = filenamessplit[i].replaceAll("`", "").trim();
            String fileval = valuessplit[i];


            boolean arg = true;
            if (filename.equalsIgnoreCase("createdate") ||
                    filename.equalsIgnoreCase("updateDate") ||
                    filename.equalsIgnoreCase("create_date") ||
                    filename.equalsIgnoreCase("update_date")
            ) {
                fileval = "  SYSDATE() ";
                arg = false;
            }

            if (i > 0&&isfirst) {
                filenamesql += ",";
                valsql += ",";
            }


            filenamesql += filename;
            valsql += fileval;

            if (arg&&i>0&&!"null".equalsIgnoreCase(fileval)) {
                selecttablesql += " and ";
                selecttablesql += " " + filename + "=" + fileval;
            }
            isfirst=true;
        }

        selecttablesql += ");";
        filenamesql += " ) ";
        valsql += "   from dual where NOT EXISTS ";

        String allsql = filenamesql + valsql + selecttablesql;

        return allsql;
    }

    public static void main(String[] args) {
        String allsql="";
        String createinsert = new createinsetsql().createinsert("subiaodb", "partitiontableconfig",
                "where baseTableName='drivercardrecord' and  tableSchema='subiaodb' ");
        allsql+=createinsert+"\n";
        System.out.println(allsql);

    }

}
