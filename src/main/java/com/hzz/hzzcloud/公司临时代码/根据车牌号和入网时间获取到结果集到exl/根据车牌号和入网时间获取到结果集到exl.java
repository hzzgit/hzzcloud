package com.hzz.hzzcloud.公司临时代码.根据车牌号和入网时间获取到结果集到exl;

import com.hzz.hzzcloud.公司临时代码.EasyExceltofileUtil;
import com.hzz.hzzjdbc.jdbcutil.dbmain.MysqlDao;
import com.hzz.hzzjdbc.jdbcutil.jdkjdbc.JdkDataSource;
import com.hzz.hzzjdbc.jdbcutil.util.ConverMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/24 17:43
 */
public class 根据车牌号和入网时间获取到结果集到exl {

    public static void main(String[] args) {
        JdkDataSource.jdkmysql();
        MysqlDao mysqldb = JdkDataSource.mysqldb;

        String sql="select vehicleId,plateNo from vehicle ";
        List<ConverMap> query = mysqldb.query(sql);
        Map<String,Long> veIdpalteNoMap=new HashMap<>();
        for (ConverMap converMap : query) {
            veIdpalteNoMap.put(converMap.getString("plateNo"),converMap.getLong("vehicleId"));
        }

        String filepath="C:\\Users\\fxft\\Desktop\\platenototime.txt";
        File file=new File(filepath);
        BufferedReader reader = null;
        List<String> plateNos=new ArrayList<>();
        List<String> times=new ArrayList<>();

        boolean arg=true;
        StringBuffer sbf = new StringBuffer();
        try {
            reader = new BufferedReader(new FileReader(file));
            String tempStr;
            while ((tempStr = reader.readLine()) != null) {
                if("入网时间".equalsIgnoreCase(tempStr.trim())){
                    arg=false;
                }else if("车牌号".equalsIgnoreCase(tempStr.trim())){
                    arg=true;
                }else{
                    if(arg){
                        plateNos.add(tempStr.trim().replace("黄","").replace("绿","").replace("蓝","").replace("白","").replace("红",""));
                    }else{
                        try {
                            times.add(tempStr.trim().substring(0,tempStr.trim().indexOf(".")));
                        } catch (Exception e) {
                           times.add("1");
                        }
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        String seasql = "select type1cn ,type2cn,type3cn,type4cn,vehicleId from gps_hisdata.logisticsstatisticsresult where time =?   ";
        List<ConverMap> query09 = mysqldb.query(seasql, "2020-09-01");
        seasql = "select type1cn ,type2cn,type3cn,type4cn,vehicleId from gps_hisdata.logisticsstatisticsresult where time =?   ";
        List<ConverMap> query11 = mysqldb.query(seasql, "2020-11-01");

        Map<Long,ConverMap> Map09=new HashMap<>();
        Map<Long,ConverMap> Map11=new HashMap<>();

        for (ConverMap converMap : query09) {
            Long vehicleId = converMap.getLong("vehicleId");
            Map09.put(vehicleId,converMap);
        }
        for (ConverMap converMap : query11) {
            Long vehicleId = converMap.getLong("vehicleId");
            Map11.put(vehicleId,converMap);
        }

        List<reportExlMobanVo> data=new ArrayList<>();
        for (int i = 0; i < plateNos.size(); i++) {
            String plateNo=plateNos.get(i);
            String time = times.get(i);
            String searchtime="2020-09-01";
            if(time.indexOf("2020-10")>-1||
                    time.indexOf("2020-11")>-1||
                    time.indexOf("2020/10")>-1||
                    time.indexOf("2020/11")>-1){
                searchtime="2020-11-01";
            }
            if(veIdpalteNoMap.containsKey(plateNo)) {
                Long aLong = veIdpalteNoMap.get(plateNo);
                ConverMap converMap=null;
                if("2020-09-01".equalsIgnoreCase(searchtime)){
                    if(Map09.containsKey(aLong)){
                        converMap=Map09.get(aLong);
                    }
                }else{
                    if(Map11.containsKey(aLong)){
                        converMap=Map11.get(aLong);
                    }
                }
                if(converMap==null){
                    data.add(new reportExlMobanVo());
                }else{
                    reportExlMobanVo reportExlMobanVo = new reportExlMobanVo();
                    reportExlMobanVo.setType1cn(converMap.getInt("type1cn"));
                    reportExlMobanVo.setType2cn(converMap.getInt("type2cn"));
                    reportExlMobanVo.setType3cn(converMap.getInt("type3cn"));
                    reportExlMobanVo.setType4cn(converMap.getDouble("type4cn"));
                    data.add(reportExlMobanVo);

                }
            }else{
                data.add(new reportExlMobanVo());
            }
        }
        EasyExceltofileUtil easyExceltofileUtil=new EasyExceltofileUtil();
        easyExceltofileUtil.exportfile("te","C:\\Users\\fxft\\Desktop",reportExlMobanVo.class);
        easyExceltofileUtil.write(data);
        easyExceltofileUtil.finishwrite();
        System.out.println( 1);
    }
}
