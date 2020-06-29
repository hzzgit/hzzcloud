package com.hzz.hzzcloud.byteUtils;


import com.ltmonitor.jt808.tool.Tools;
import org.apache.mina.core.buffer.IoBuffer;

import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;

public class MyBuffer {
    IoBuffer buff;

    public MyBuffer() {
        this.buff = IoBuffer.allocate(1536);
        this.buff.mark();
    }

    public MyBuffer(int len) {
        this.buff = IoBuffer.allocate(len);
        this.buff.mark();
    }

    public MyBuffer(byte[] bytes) {
        if (bytes.length > 1024) {
            this.buff = IoBuffer.allocate(bytes.length + 100);
        } else {
            this.buff = IoBuffer.allocate(1024);
        }

        this.buff.mark();
        this.buff.put(bytes);
        this.buff.limit(bytes.length);
        this.buff.reset();
    }

    public MyBuffer(byte[] bytes, int start, int length) {
        this.buff = IoBuffer.allocate(length);
        this.buff.mark();
        this.buff.put(bytes, start, length);
        this.buff.limit(length);
        this.buff.reset();
    }

    public void clear() {
        this.buff.clear();
        this.buff.mark();
    }

    public void put(byte a) {
        this.buff.put(a);
    }

    public void put(long a) {
        this.buff.putLong(a);
    }

    public void put(short a) {
        this.buff.putShort(a);
    }

    public void put(byte[] a) {
        this.buff.put(a);
    }

    public void put(byte[] a, int start, int maxLen) {
        if (a.length > start) {
            int left = a.length - start;
            int putlen = Math.min(maxLen, left);
            this.buff.put(a, start, putlen);
        }

    }

    public int remain() {
        return this.buff.remaining();
    }

    public boolean hasRemain() {
        return this.buff.remaining() > 0;
    }

    public int position() {
        return this.buff.position();
    }

    public void put(int a) {
        this.buff.putInt(a);
    }

    public void putShort(int a) {
        this.buff.putShort((short)a);
    }

    public void put(String str) {
        try {
            byte[] b = str.getBytes("gbk");
            this.buff.put(b);
        } catch (Exception var3) {
            throw new RuntimeException("", var3);
        }
    }

    public void putStringWithLen(String str) {
        try {
            byte[] b = str.getBytes("ASCII");
            this.buff.put((byte)b.length);
            this.buff.put(b);
        } catch (Exception var3) {
            throw new RuntimeException("", var3);
        }
    }

    public void putBcd(String str, int length) {
        byte[] b = BcDToBytes(str, length);
        this.buff.put(b);
    }

    public static String BytesToBcd(byte[] bytes, int start, int len) {
        StringBuilder bcd = new StringBuilder();

        for(int m = 0; m < len; ++m) {
            bcd.append(String.format("%02X", bytes[start + m]));
        }

        return bcd.toString();
    }

    public static byte[] BcDToBytes(String bcd, int len) {
        for(bcd = bcd == null ? "" : bcd; bcd.length() < len; bcd = "0" + bcd) {
        }

        return Tools.HexString2Bytes(bcd);
    }

    public void put(String str, int len) {
        byte[] result = new byte[len];

        try {
            byte[] b = str.getBytes("gbk");
            System.arraycopy(b, 0, result, 0, b.length);

            for(int m = b.length; m < len; ++m) {
                result[m] = 0;
            }

            this.buff.put(result);
        } catch (Exception var6) {
            throw new RuntimeException("", var6);
        }
    }

    public byte get() {
        return this.buff.get();
    }

    public byte[] gets(int len) {
        byte[] data = new byte[len];
        this.buff.get(data);
        return data;
    }

    public int getInt() {
        return this.buff.getInt();
    }

    public short getShort() {
        return this.buff.getShort();
    }

    public long getLong() {
        return this.buff.getLong();
    }

    public int getUnsignedShort() {
        short t = this.buff.getShort();
        return t & '\uffff';
    }

    public int getUnsignedByte() {
        return this.buff.get() & 255;
    }

    public long getUnsignedInt() {
        long t = this.buff.getUnsignedInt();
        return t;
    }

    public String getString() {
        try {
            String strTemp = this.buff.getString(Charset.forName("GBK").newDecoder());
            return strTemp;
        } catch (CharacterCodingException var2) {
            throw new RuntimeException("", var2);
        }
    }

    public String getString(int len) {
        try {
            String strTemp = this.buff.getString(len, Charset.forName("GBK").newDecoder());
            return strTemp;
        } catch (CharacterCodingException var3) {
            throw new RuntimeException("", var3);
        }
    }

    public String getBcdString(int len) {
        byte[] bytes = this.gets(len);
        StringBuilder bcd = new StringBuilder();

        for(int m = 0; m < len; ++m) {
            bcd.append(String.format("%02X", bytes[m]));
        }

        return bcd.toString();
    }

    public String getBcdDateString() {
        byte[] timeBytes = this.gets(6);
        String time = String.format("20%02X-%02X-%02X %02X:%02X:%02X", timeBytes[0], timeBytes[1], timeBytes[2], timeBytes[3], timeBytes[4], timeBytes[5]);
        return time;
    }

    public byte[] array() {
        int pos = this.buff.position();
        byte[] data = new byte[pos];
        this.buff.reset();
        this.buff.get(data);
        return data;
    }


    public static void main(String[] args) {
        MyBuffer myBuffer=new MyBuffer();
        myBuffer.put(1);
        myBuffer.put(12);
        myBuffer.put(5);
        myBuffer.put(4);
        myBuffer.put(3);
        myBuffer.put(2);
        byte[] array = myBuffer.array();


        MyBuffer myBuffer2=new MyBuffer(array);

        while (myBuffer2.hasRemain()){
            int anInt = myBuffer2.getInt();
            int remain = myBuffer2.remain();
        }



    }
}