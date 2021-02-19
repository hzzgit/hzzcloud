package com.hzz.hzzcloud.test;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/9/28 10:12
 */
public class DoubleToLong {
    public static void main(String[] args) {

        String sql = "SELECT " +
                " vehicleId, " +
                " count( * ) cn  " +
                "FROM " +
                " ( " +
                " SELECT " +
                "  vehicleId, " +
                "  onlineDate  " +
                " FROM " +
                "  gps_hisdata.car_online  " +
                " WHERE 1=1 " +
                "  AND onlineDate >= ?  " +
                "  AND onlineDate <= ?  " +
                " GROUP BY " +
                "  vehicleId, " +
                "  onlineDate  " +
                " ) a1  " +
                "GROUP BY " +
                " vehicleId";
        System.out.println(sql);
    }
}
