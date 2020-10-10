package com.hzz.hzzcloud.test.反射注入一个类;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/9/30 17:11
 */
public class TESTmAIN {

    public static void main(String[] args) {
        String sql = "\tselect v.vehicleId,\n" +
                "\t\tv.depId,\n" +
                "\t\tv.createDate,\n" +
                "\t\tv.plateColor,\n" +
                "\t\tv.plateNo,\n" +
                "\t\tr.fullname region,\n" +
                "\t\tv.runStatus,\n" +
                "\t\tv.simNo,\n" +
                "\t\tv.vehicleType,\n" +
                "\t\tv.videoDeviceId,\n" +
                "\t\tv.videoChannelNum,\n" +
                "\t\tv.videoDisk,\n" +
                "\t\tv.videoChannelNames,\n" +
                "\t\tv.flowRateNo,\n" +
                "\t\tv.mileageAdjustment,\n" +
                "\t\tv.serviceEndDate,\n" +
                "\t\tmen.name memname,\n" +
                "\t\tifnull(d.name,'') depName,\n" +
                "\t\tifnull(t.termNo,0) termNo\n" +
                "\t\tfrom\n" +
                "\t\tvehicle v left join terminal  t on v.termId=t.termId\n" +
                "\t\tleft join department  d on v.depId=d.depId\n" +
                "\t\tleft join region r on v.region=r.code\n" +
                "\t\tleft join memberinfo men on v.memberId=men.id\n" +
                "\t\twhere  1=1  and  v.deleted = 0 and d.deleted=0 " +
                "\t\torder by v.plateNo asc ";
        System.out.println(sql);
    }
}
