package com.hzz.hzzcloud.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/9 16:36
 */
public class LineToHumpUtil {
    private static Pattern linePattern = Pattern.compile("_(\\w)");
    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     * 驼峰转下划线,最后转为大写
     * @param str
     * @return
     */
    public static String humpToLine(String str) {
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString().toUpperCase();
    }

    /**
     * 下划线转驼峰,正常输出
     * @param str
     * @return
     */
    public static String lineToHump1(String str) {
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }


    public static String lineToHump(String table_name){
        String EntityName = table_name.substring(0, 1).toUpperCase() + table_name.substring(1);//首字符大写的名称
        EntityName=    LineToHumpUtil.lineToHump1(EntityName);
        if(EntityName.substring(0,1).equalsIgnoreCase("T")){
            EntityName=EntityName.substring(1);
        }
        return EntityName;
    }

    /**
     * 字段下划线转驼峰
     * @param colName
     * @return
     */
    public static String lineToHumpbyCol(String colName){
     return    LineToHumpUtil.lineToHump1(colName);
    }

}
