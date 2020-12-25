package com.hzz.hzzcloud.公司临时代码.金融报表风控业务.将用户需要导出的报表插入到配置表中;

import com.hzz.hzzcloud.公司临时代码.ReadFileUtil;
import com.hzz.hzzcloud.公司临时代码.金融报表风控业务.将用户需要导出的报表插入到配置表中.entity.Reportpushbytime;
import com.hzz.hzzcloud.公司临时代码.金融报表风控业务.快速读取exl.ExlReadUtil;
import com.hzz.hzzcloud.公司临时代码.金融报表风控业务.快速读取exl.Vo.PlateVo;
import com.hzz.hzzjdbc.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzjdbc.jdbcutil.jdkjdbc.JdkDataSource;
import com.hzz.hzzjdbc.jdbcutil.util.ConverMap;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/25 15:17
 */
public class 主方法 {


    public void start(String loginname){


        JdkDataSource.jdkmysql();
        MysqlDao mysqldb = JdkDataSource.mysqldb;


        String selectuser="select userid,name from userinfo where loginname=?";
        ConverMap userId1 = mysqldb.queryFirst(selectuser, loginname);
        Long userId=userId1.getLong("userid");
        String userName=userId1.getString("name");
        String sql = "select vehicleId,plateNo from vehicle ";
        List<ConverMap> query = mysqldb.query(sql);
        Map<String, Long> veIdpalteNoMap = new HashMap<>();
        for (ConverMap converMap : query) {
            veIdpalteNoMap.put(converMap.getString("plateNo"), converMap.getLong("vehicleId"));
        }

        List<PlateVo> objects = ExlReadUtil.simpleRead(PlateVo.class, "C:\\Users\\fxft\\Desktop\\userplatenobytime.xlsx");
        for (PlateVo object : objects) {
            String plateNo = object.getPlateNo();
            String startTime = object.getStartTime();
            String endTime = object.getEndTime();
            Reportpushbytime reportpushbytime=new Reportpushbytime();
            reportpushbytime.setPlateno(plateNo);
            reportpushbytime.setUsername(userName);
            reportpushbytime.setLoginname(loginname);
            reportpushbytime.setUserid(userId);
            reportpushbytime.setStarttime(startTime);
            reportpushbytime.setEndtime(endTime);
        }
        System.out.println( 1);
    }

    public static void main(String[] args) {


    }
}
