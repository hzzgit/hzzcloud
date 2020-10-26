package com.hzz.hzzcloud.test.浮动车数据传输协议.protocol;

import com.hzz.hzzcloud.byteUtils.MyBuffer;
import com.hzz.hzzcloud.test.浮动车数据传输协议.util.CRC32;

/**
 * @author ：hzz
 * @description：浮动车传输协议
 * @date ：2020/10/15 10:22
 */
public class FloatingCarProtocol {
    private  int  SymbolHead;//字节数1，类型byte，取值范围0x23 ,注释:记录起始符#
    private  int  OriDatalen;//字节数4，类型  Unsigned int，,注释:原始数据长度#
    private  int  ZipDatalen;//字节数4，类型  Unsigned int，,注释:压缩后数据度(zip 压缩)
    private  String  ZipData;//变长(ZipDatalen)，类型string，注释:压缩后数据
    private  int  Crc32;//字节数4，类型Unsigned int,注释:Crc32 校验码
    private  int  SymbolTail;//字节数1，类型byte，取值范围0x24   ,注释:记录结束符$

    public FloatingCarProtocol(int oriDatalen, int zipDatalen, String zipData) {
        SymbolHead = 35;
        SymbolTail = 36;
        OriDatalen = oriDatalen;
        ZipDatalen = zipDatalen;
        ZipData = zipData;
        MyBuffer myBuffer=new MyBuffer();
        myBuffer.put((byte) SymbolHead);
        myBuffer.put( OriDatalen);
        myBuffer.put( ZipDatalen);
        myBuffer.put(ZipData);
        byte[] array = myBuffer.array();
        Crc32 = CRC32.getCRC32(array);//计算crc32校验码
    }

    /**
     * 转成成可以传输的字节数组
     * @param version
     * @return
     */
    public final byte[] WriteToBytes(int version) {
        MyBuffer myBuffer=new MyBuffer();
        myBuffer.put((byte) SymbolHead);
        myBuffer.put( OriDatalen);
        myBuffer.put( ZipDatalen);
        myBuffer.put(ZipData);
        myBuffer.put(Crc32);
        myBuffer.put((byte)SymbolTail);
        return myBuffer.array();
    }
}
