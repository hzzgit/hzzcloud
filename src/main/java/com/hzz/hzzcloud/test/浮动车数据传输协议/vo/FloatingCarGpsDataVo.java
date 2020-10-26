package com.hzz.hzzcloud.test.浮动车数据传输协议.vo;

/**
 * @author ：hzz
 * @description：用于返回压缩后的字符串，压缩前字符串长度，压缩后字符串长度
 * @date ：2020/10/15 11:25
 */
public class FloatingCarGpsDataVo {
    private int OriDatalen;
    private int ZipDatalen;
    private String ZipData;

    public FloatingCarGpsDataVo(int oriDatalen, int zipDatalen, String zipData) {
        OriDatalen = oriDatalen;
        ZipDatalen = zipDatalen;
        ZipData = zipData;
    }

    public int getOriDatalen() {
        return OriDatalen;
    }

    public void setOriDatalen(int oriDatalen) {
        OriDatalen = oriDatalen;
    }

    public int getZipDatalen() {
        return ZipDatalen;
    }

    public void setZipDatalen(int zipDatalen) {
        ZipDatalen = zipDatalen;
    }

    public String getZipData() {
        return ZipData;
    }

    public void setZipData(String zipData) {
        ZipData = zipData;
    }
}
