package com.hzz.hzzcloud.test.浮动车数据传输协议.vo;

import com.hzz.hzzcloud.test.浮动车数据传输协议.util.ZipUtils;

/**
 * @author ：hzz
 * @description：传输数据类
 * @date ：2020/10/15 10:58
 */
public class FloatingCarGpsData {

    /**
     * GPS 信息产生日期 
     * YYYYMMDD
     */
    private String gpsData;
    /**
     * GPS 信息产生时间 
     * HHMMSS
     */
    private String gpsTime;

    /**
     * 城市代号(或数据源名称)
     */
    private String cityCode="fxft";

    /**
     * 车辆编号 车辆唯一标识
      */
    private String simNo;

    /**
     * 经度
     */
    private Double  longitude;

    /**
     * 纬度
     */
    private Double latitude;

    /**
     * 速度 km/h
     */
    private Double speed;


    /**
     * 角度 以正北方向为 0 度
     */
    private Double direction;

    /**
     * 车辆类型，默认直接填0
     */
    private int vehicleType=0;

    /**
     * 载客状态，无此字段，
     * 出租车 填‐1
     * Ugc 填 0
     */
    private int passengerStatus=0;

    /**
     * 卫星有效性或卫星数
     * 1.基站定位
     * ‐1
     * 2.卫星定位
     * 2.1 无卫星数
     * 0=未定位
     * 1=已定位
     * 2.2 卫星定位‐有卫
     * 星数
     * 卫星数
     */
    private int satelliteStatus;

    /**
     * 接收到 GPS 信息的时间  YYYY‐MM‐DD HH:MM:SS
     *
     */
    private String createDate;

    public FloatingCarGpsData(String gpsData, String gpsTime, String simNo,
                              Double longitude, Double latitude, Double speed, Double direction,
                              int satelliteStatus, String createDate) {
        this.gpsData = gpsData;
        this.gpsTime = gpsTime;
        this.simNo = simNo;
        this.longitude = longitude;
        this.latitude = latitude;
        this.speed = speed;
        this.direction = direction;
        this.satelliteStatus = satelliteStatus;
        this.createDate = createDate;
    }


    /**
     * 原始长度，压缩数据，压缩长度
     * @return FloatingCarGpsDataVo
     */
    public FloatingCarGpsDataVo conver(){
        StringBuilder stringBuilder=new StringBuilder();
        stringBuilder.append(gpsData);stringBuilder.append(",");
        stringBuilder.append(gpsTime);stringBuilder.append(",");
        stringBuilder.append(cityCode);stringBuilder.append(",");
        stringBuilder.append(simNo);stringBuilder.append(",");
        stringBuilder.append(longitude);stringBuilder.append(",");
        stringBuilder.append(latitude);stringBuilder.append(",");
        stringBuilder.append(speed);stringBuilder.append(",");
        stringBuilder.append(direction);stringBuilder.append(",");
        stringBuilder.append(vehicleType);stringBuilder.append(",");
        stringBuilder.append(passengerStatus);stringBuilder.append(",");
        stringBuilder.append(satelliteStatus);stringBuilder.append(",");
        stringBuilder.append(createDate);stringBuilder.append(";\\r\\n");
        String orgData = stringBuilder.toString();
        int orgDatalength = orgData.length();
        String zipData = ZipUtils.zip(orgData);
        int zipDatalength = zipData.length();
        FloatingCarGpsDataVo probeGpsDataVo=new FloatingCarGpsDataVo(orgDatalength,zipDatalength,zipData);
        return  probeGpsDataVo;
    }
}
