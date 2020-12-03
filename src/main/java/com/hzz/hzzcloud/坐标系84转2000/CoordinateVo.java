package com.hzz.hzzcloud.坐标系84转2000;

import lombok.Data;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/1 11:54
 */
@Data
public class CoordinateVo {
    private double lat;
    private double lng;

    public CoordinateVo(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }
}
