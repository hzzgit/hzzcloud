package com.hzz.hzzcloud.公司临时代码.报文解析;

import com.hzz.hzzcloud.byteUtils.ByteUtil;
import com.ltmonitor.jt808.protocol.JT_0702;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/11 15:28
 */
public class J0702T {
    public static void main(String[] args) {

        JT_0702My jt_0702=new JT_0702My();
        jt_0702.setDriverName("测试");
        jt_0702.setAgencyName("机构");
        jt_0702.setCertificationCode("1234567819");
        jt_0702.setCardState((byte) 1);
        jt_0702.setReadResult((byte) 0);
        jt_0702.setOperTime("2020-12-11 01:11:11");
        jt_0702.setValidateDate("20201211");
        jt_0702.setDriverIdCard("123444");

        byte[] bytes = jt_0702.WriteToBytes(1);
        System.out.println(ByteUtil.byteToHexStr(bytes));
        JT_0702My jt_0702temp=new JT_0702My();
        jt_0702temp.ReadFromBytes(1,bytes);
        System.out.println(1);

        JT_0702 jt_07021=new JT_0702();
        jt_07021.ReadFromBytes(1,bytes);
        System.out.println(jt_07021.getDriverIdCard());
        System.out.println(jt_07021.getDriverName());
    }
}
