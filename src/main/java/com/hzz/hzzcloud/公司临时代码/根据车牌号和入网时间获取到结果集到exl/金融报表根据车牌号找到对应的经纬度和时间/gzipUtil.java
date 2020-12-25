package com.hzz.hzzcloud.公司临时代码.根据车牌号和入网时间获取到结果集到exl.金融报表根据车牌号找到对应的经纬度和时间;

import cn.hutool.core.util.ZipUtil;

//gzip工具类
public class gzipUtil {

    //进行数据压缩
    public static   byte[] gzipcompress(String sb){
        byte[] gz = ZipUtil.gzip(sb.toString(),"utf-8");
        return  gz;
    }
}
