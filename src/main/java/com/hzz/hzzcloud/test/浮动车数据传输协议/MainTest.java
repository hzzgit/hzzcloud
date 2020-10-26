package com.hzz.hzzcloud.test.浮动车数据传输协议;

import com.hzz.hzzcloud.byteUtils.ByteUtil;
import com.hzz.hzzcloud.test.浮动车数据传输协议.protocol.FloatingCarProtocol;
import com.hzz.hzzcloud.test.浮动车数据传输协议.vo.FloatingCarGpsData;
import com.hzz.hzzcloud.test.浮动车数据传输协议.vo.FloatingCarGpsDataVo;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/10/15 11:31
 */
public class MainTest {
    public static void main(String[] args) {
        FloatingCarGpsData probeGpsData=new FloatingCarGpsData
                ("2020-10-15","11:33:11",
                        "123456789101",
                        120.497067,30.094717,
                        22.0,316.0,1,
                        "2020‐10‐15 11:33:45");
        FloatingCarGpsDataVo conver = probeGpsData.conver();
        FloatingCarProtocol probeProtocol=new FloatingCarProtocol(conver.getOriDatalen(),conver.getZipDatalen(),conver.getZipData());
        byte[] bytes = probeProtocol.WriteToBytes(1);
        System.out.println(ByteUtil.byteToHexStr(bytes));
    }
}
