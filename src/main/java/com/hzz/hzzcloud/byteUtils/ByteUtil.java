package com.hzz.hzzcloud.byteUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayOutputStream;

public class ByteUtil {
    private static Logger log = LoggerFactory.getLogger(net.fxft.common.util.ByteUtil.class);
    protected static char[] HEX_CHAR = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    public ByteUtil() {
    }

    public static byte[] intTo3Byte(int value) {
        byte[] buf = new byte[]{(byte)(value >> 16 & 255), (byte)(value >> 8 & 255), (byte)(value & 255)};
        return buf;
    }

    public static byte[] intToByte(int value) {
        byte[] buf = new byte[]{(byte)(value >> 24), (byte)(value >> 16), (byte)(value >> 8), (byte)value};
        return buf;
    }

    public static byte[] shortToByte(int value) {
        byte[] buf = new byte[]{(byte)(value >> 8), (byte)value};
        return buf;
    }

    public static int intToByte(int value, byte[] buf, int pos) {
        try {
            buf[pos] = (byte)(value >> 24);
            buf[pos + 1] = (byte)(value >> 16);
            buf[pos + 2] = (byte)(value >> 8);
            buf[pos + 3] = (byte)value;
            return 4;
        } catch (Exception var4) {
            log.error("intToByte出错![" + value + "]", var4);
            return 0;
        }
    }

    public static int byteToShort(byte b1, byte b2) {
        try {
            return (255 & b1) << 8 | 255 & b2;
        } catch (Exception var3) {
            log.error("byteToShort出错!", var3);
            return 0;
        }
    }

    public static int byteToInt(byte[] buf) {
        try {
            return (255 & buf[0]) << 24 | (255 & buf[1]) << 16 | (255 & buf[2]) << 8 | 255 & buf[3];
        } catch (Exception var2) {
            log.error("byteToInt出错!", var2);
            return 0;
        }
    }

    public static int byteToInt(byte[] buf, int pos) {
        try {
            return (255 & buf[pos]) << 24 | (255 & buf[pos + 1]) << 16 | (255 & buf[pos + 2]) << 8 | 255 & buf[pos + 3];
        } catch (Exception var3) {
            log.error("byteToInt出错!", var3);
            return 0;
        }
    }

    public static byte[] longToByte(long value) {
        byte[] buf = new byte[]{(byte)((int)(value >> 56)), (byte)((int)(value >> 48)), (byte)((int)(value >> 40)), (byte)((int)(value >> 32)), (byte)((int)(value >> 24)), (byte)((int)(value >> 16)), (byte)((int)(value >> 8)), (byte)((int)value)};
        return buf;
    }

    public static int longToByte(long value, byte[] buf, int pos) {
        try {
            buf[pos] = (byte)((int)(value >> 56));
            buf[pos + 1] = (byte)((int)(value >> 48));
            buf[pos + 2] = (byte)((int)(value >> 40));
            buf[pos + 3] = (byte)((int)(value >> 32));
            buf[pos + 4] = (byte)((int)(value >> 24));
            buf[pos + 5] = (byte)((int)(value >> 16));
            buf[pos + 6] = (byte)((int)(value >> 8));
            buf[pos + 7] = (byte)((int)value);
            return 8;
        } catch (Exception var5) {
            log.error("longToByte出错![" + value + "]", var5);
            return 0;
        }
    }

    public static long byteToLong(byte[] buf) {
        try {
            return ((long)buf[0] & 255L) << 56 | ((long)buf[1] & 255L) << 48 | ((long)buf[2] & 255L) << 40 | ((long)buf[3] & 255L) << 32 | ((long)buf[4] & 255L) << 24 | ((long)buf[5] & 255L) << 16 | ((long)buf[6] & 255L) << 8 | (long)buf[7] & 255L;
        } catch (Exception var2) {
            log.error("byteToLong出错!", var2);
            return 0L;
        }
    }

    public static long byteToLong(byte[] buf, int pos) {
        try {
            return ((long)buf[pos] & 255L) << 56 | ((long)buf[pos + 1] & 255L) << 48 | ((long)buf[pos + 2] & 255L) << 40 | ((long)buf[pos + 3] & 255L) << 32 | ((long)buf[pos + 4] & 255L) << 24 | ((long)buf[pos + 5] & 255L) << 16 | ((long)buf[pos + 6] & 255L) << 8 | (long)buf[pos + 7] & 255L;
        } catch (Exception var3) {
            log.error("byteToLong出错!", var3);
            return 0L;
        }
    }

    public static void byteToHexStr(byte value, StringBuilder sb) {
        sb.append(HEX_CHAR[(255 & value) >> 4]).append(HEX_CHAR[15 & value]);
    }

    public static String byteToHexStr(byte value) {
        StringBuilder sb = new StringBuilder();
        byteToHexStr(value, sb);
        return sb.toString();
    }

    public static String intToHexStr(int value, int hexLength) {
        String hexstr = Integer.toHexString(value);
        if (hexstr.length() >= hexLength) {
            return hexstr;
        } else {
            StringBuilder sb = new StringBuilder();

            for(int i = 0; i < hexLength - hexstr.length(); ++i) {
                sb.append("0");
            }

            sb.append(hexstr);
            return sb.toString();
        }
    }

    public static String byteToHexStr(byte[] buffer) {
        return buffer == null ? "" : byteToHexStr(buffer, 0, buffer.length);
    }

    public static String byteToHexStr(byte[] buffer, int startPos, int length) {
        StringBuilder result = new StringBuilder();
        if (buffer != null) {
            for(int i = startPos; i < startPos + length; ++i) {
                byteToHexStr(buffer[i], result);
            }
        }

        return result.toString();
    }

    public static byte[] hexStrToBytes(String str) {
        if (str != null && str.length() != 0) {
            str = str.trim();
            str = str.replaceAll("[ :\\r\\n]", "");
            ByteArrayOutputStream bos = new ByteArrayOutputStream(str.length() / 2 + 1);

            for(int i = 0; i < str.length(); i += 2) {
                String str1 = str.substring(i, i + 2);
                bos.write(Integer.parseInt(str1, 16));
            }

            return bos.toByteArray();
        } else {
            return new byte[0];
        }
    }

    public static String byteToHexViewStr(byte[] buffer) {
        if (buffer == null) {
            return "";
        } else {
            StringBuffer result = new StringBuffer();

            for(int i = 0; i < buffer.length; ++i) {
                result.append(byteToHexStr(buffer[i]));
                result.append(" ");
            }

            return result.toString();
        }
    }

    public static byte[] BCDStringToBytes(String hexstr) {
        byte[] b = new byte[hexstr.length() / 2];
        int j = 0;

        for(int i = 0; i < b.length; ++i) {
            char c0 = hexstr.charAt(j++);
            char c1 = hexstr.charAt(j++);
            b[i] = (byte)(parse(c0) << 4 | parse(c1));
        }

        return b;
    }

    //字节转2进制
    public static String byteto2hex(byte tByte){
        String tString = Integer.toBinaryString((tByte & 0xFF) + 0x100).substring(1);
        return  tString;
    }

    private static int parse(char c) {
        if (c >= 'a') {
            return c - 97 + 10 & 15;
        } else {
            return c >= 'A' ? c - 65 + 10 & 15 : c - 48 & 15;
        }
    }

    public static String bytesToBCDString(byte[] barr) {
        return byteToHexStr(barr);
    }

    public static String bytesToBCDString(byte[] barr, int startPos, int length) {
        return byteToHexStr(barr, startPos, length);
    }

    public static boolean isEqual(byte[] buf1, int pos1, byte[] buf2, int pos2, int len) {
        try {
            for(int i = 0; i < len; ++i) {
                if (buf1[pos1 + i] != buf2[pos2 + i]) {
                    return false;
                }
            }

            return true;
        } catch (Exception var6) {
            log.error("theSameArr出错!", var6);
            return false;
        }
    }
    public static String intTobinary2sys(long n){
        String str = "";
        while(n!=0){
            str = n%2+str;
            n = n/2;
        }
        return  str;
    }

    public static String tobinary2sys(Object n){
        String name="";
        if(n.getClass()==(Integer.class)){
            name=Integer.toBinaryString((Integer) n);
        }
       else if(n.getClass()==(Short.class)){
            name=Short.toString((Short) n);
        }
        else  if(n.getClass()==(Long.class)){
            name=Long.toBinaryString((Long) n);
        }
        return name;
    }

    public static void main(String[] args) {
        byte[] bytes = ByteUtil.intTo3Byte(789632);

        String e16="EEFFEEFFEEFF";
        Long i = Long.parseLong(e16,16);
         String s1 = ByteUtil.tobinary2sys(i);
        String string = Long.toBinaryString(i);
        String s = ByteUtil.intTobinary2sys(i);
        System.out.println(1);


        System.out.println(byteto2hex((byte) 22));
    }
}
