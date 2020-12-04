package com.hzz.hzzcloud.公司临时代码.读取配置文件文档根据日期自动查找;

import com.hzz.hzzjdbc.jdbcutil.util.TimeUtils;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：hzz
 * @description：查找对应的配置文件，升级用
 * @date ：2020/12/2 10:58
 */
public class SearchSqlFile {

    private SearchSqlFile() {

    }

    public static SearchSqlFile getInstance() {
        return searchfiletemp.getSearchsqlfile();
    }

    private static class searchfiletemp {

        private static final SearchSqlFile searchsqlfile = new SearchSqlFile();

        private searchfiletemp() {
        }


        public static SearchSqlFile getSearchsqlfile() {
            return searchsqlfile;
        }
    }

    private List<String> fileList = new ArrayList<>();

    private String getfilenames(){
        String names="";
        for (String s : fileList) {
            names+=s+"\n";
        }
        return names;
    }

    public static void searchFile(String path, List<String> keyname, String starttime) {
        File file = new File(path);
        SearchSqlFile instance = SearchSqlFile.getInstance();
        Date date = instance.date(starttime);
        instance.searchsql(file, keyname, date);
        String getfilenames = instance.getfilenames();
        System.out.println(getfilenames);
    }

    private void searchsql(File file, List<String> keyname, Date starttime) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    searchsql(files[i], keyname, starttime);
                }
            }
        } else {
            boolean arg = false;
            String name = file.getName();
            String time = getNumber(name);
            Date date = date(time);
            if (date!=null&&starttime.getTime() < date.getTime()) {
                for (String s : keyname) {
                    if (name.indexOf(s) > -1) {
                        arg = true;
                    }
                }
            }
            if (arg) {
                fileList.add(name);
            }
        }
    }

    private Date date(String date_str) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            Date date = formatter.parse(date_str);
            return date;
        } catch (NullPointerException var3) {
            return null;
        } catch (ParseException var4) {
            return null;
        }
    }

    private String getNumber(String name) {
        String time = "";
        for (int i = 0; i < name.length(); i++) {
            String substring = name.substring(i, i + 1);
            boolean arg = true;
            try {
                Integer.parseInt(substring.trim());
            } catch (NumberFormatException e) {
                arg = false;
            }
            if (arg) {
                time += substring;
            }

        }
        return  time;
    }

    public static void main(String[] args) {
        String path = "D:\\hzzmysoft\\workspace\\ascs-deploy\\release\\2020";
        List<String > keyname=new ArrayList<>();
        keyname.add("预发");keyname.add("pre");
        SearchSqlFile.searchFile(path,keyname,"20200807");
    }
}
