package com.hzz.hzzcloud.test.集合反转;

/**
 * @author ：hzz
 * @description：字符串验证工具
 * @date ：2021/1/28 17:14
 */
public class StringVerifyUtil {

    /**
     * 判断是否是空格或者换行及符号
     * @return
     */
    public static  boolean isKong(char c){
        if (' ' == c || ',' == c||'\n'==c||'\t'==c) {
            return true;
        }else {
            return  false;
        }
    }
}
