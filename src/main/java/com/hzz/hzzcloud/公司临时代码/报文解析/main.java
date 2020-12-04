package com.hzz.hzzcloud.公司临时代码.报文解析;

import com.hzz.hzzcloud.byteUtils.ByteUtil;
import com.ltmonitor.jt808.protocol.*;
import net.fxft.gateway.protocol.IMsgBody;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/3 16:33
 */
public class main {
    public static void main(String[] args) {


        byte a=-125;
        String s = ByteUtil.byteToHexStr(a);

        byte[] testbytes = ByteUtil.hexStrToBytes(s);
        MyBuffer myBuffer=new MyBuffer(testbytes);


        String data="7E0200404E0100000000040620020586004300000000000C0003023DD3CB067CB9A3052B00000055201203160406010400011BE9030200002504000000002B040000000030011F310114140400000000150400000000160400000000170200000E7E";

        byte[] bytes = ByteUtil.hexStrToBytes(data);

        T808Message t808Message=new T808Message();
        try {
            t808Message.ReadFromBytes(bytes);
            IMsgBody messageContents = t808Message.getMessageContents();
            JT_0200 JT_0200= (JT_0200) messageContents;
            System.out.println(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
