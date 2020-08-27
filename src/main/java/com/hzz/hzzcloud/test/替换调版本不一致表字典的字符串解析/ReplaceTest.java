package com.hzz.hzzcloud.test.替换调版本不一致表字典的字符串解析;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/21 13:51
 */
public class ReplaceTest {

    public static ConcurrentHashMap<String, List<PartVersionFieldVo>> PartVersionFieldVoCache = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String, Double> NowVersionCache = new ConcurrentHashMap<>();


    public static void main(String[] args) {
        String basedata = "gps_hisdata";
        String tablename = "gpsinfo";
        String key = basedata + "." + tablename;
        List<PartVersionFieldVo> partVersionFieldVos = new ArrayList<>();
        PartVersionFieldVo partVersionFieldVos1 = new PartVersionFieldVo();
        List<String> allFiles = new ArrayList<>();
        allFiles.add("abnormalType");
        allFiles.add("extendVersion");
        partVersionFieldVos1.setAddField(allFiles);
        partVersionFieldVos1.setVersionId(2.1);
        partVersionFieldVos.add(partVersionFieldVos1);

        PartVersionFieldVo partVersionFieldVos2 = new PartVersionFieldVo();
        List<String> allFiles2 = new ArrayList<>();
        allFiles2.add("extendJson");
        partVersionFieldVos2.setAddField(allFiles2);
        partVersionFieldVos2.setVersionId(2.2);
        partVersionFieldVos.add(partVersionFieldVos2);

        PartVersionFieldVoCache.put(key, partVersionFieldVos);
        NowVersionCache.put(tablename, 1.0);

        String sql = "select a.recordVelocity,a.status,direction,DATE_FORMAT(a.sendTime ,'%Y-%m-%d %H:%i:%s')  sendTime," +
                "DATE_FORMAT(a.createDate ,'%Y-%m-%d %H:%i:%s')  createDate,a.mileage,a.latitude,a.longitude,a.velocity," +
                "a.alarmState,a.extendVersion,a.extendJson  from  " + basedata + "." + tablename + " a where  a.simNo=? and a.abnormalType in(1,2, 3 )";
        ReplaceTest.replaceTableName(sql, basedata, tablename, tablename);
    }


    public static String replaceTableName(String sql, String baseData, String pattern, String replaceName) {
        List<String> allReplaceFiledVo = new ArrayList<>();
        Double aDouble = NowVersionCache.get(replaceName);
        String key = baseData + "." + pattern;
        List<PartVersionFieldVo> partVersionFieldVos = PartVersionFieldVoCache.get(key);
        for (PartVersionFieldVo partVersionFieldVo : partVersionFieldVos) {
            Double versionId = partVersionFieldVo.getVersionId();
            if (versionId > aDouble) {
                allReplaceFiledVo.addAll(partVersionFieldVo.getAddField());
            }
        }


        String oldName = baseData + "." + pattern;
        String replacename = baseData + "." + replaceName;
        StringBuilder colName = new StringBuilder();

        boolean isWhereArg = false;//是否是where条件后面的参数

        StringBuilder finalSql = new StringBuilder();
        for (int i = 0; i < sql.length(); i++) {
            char c = sql.charAt(i);
            if (' ' == c || ',' == c) {
                if (oldName.equalsIgnoreCase(colName.toString())) {
                    finalSql.append(replacename);
                } else {
                    String ChangeStr = null;
                    for (String s : allReplaceFiledVo) {
                        if (colName.toString().toLowerCase().indexOf(s.toLowerCase()) > -1) {
                            ChangeStr = s;
                            break;
                        }
                    }
                    if (ChangeStr != null) {//如果有改变
                        if (!isWhereArg) {
                            finalSql.append(" null as " + ChangeStr);
                        }else{
                            finalSql.append(colName);
                        }
                    } else {
                            finalSql.append(colName);
                    }
                }

                finalSql.append(c);


                if ("where".equalsIgnoreCase(colName.toString().toLowerCase())) {
                    isWhereArg = true;
                }
                colName = new StringBuilder();
            } else {
                colName.append(c);
            }
        }
        finalSql.append(colName);
        return finalSql.toString();
    }
}
