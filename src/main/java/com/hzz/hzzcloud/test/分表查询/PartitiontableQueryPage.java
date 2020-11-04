package com.hzz.hzzcloud.test.分表查询;

import com.hzz.hzzjdbc.jdbcutil.util.TimeUtils;
import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：hzz
 * @description：分表sql工具类
 * @date ：2020/8/4 16:36
 */
@Service
public class PartitiontableQueryPage {

    @Autowired
    private PartitiontablerangeCache partitiontablerangeCache;


    @Autowired
    private JdbcUtil jdbcUtil;

    /**
     * 查看分表，带分页的
     *
     * @return
     */
    public PaginateResult queryPage(String sql, Date startTime, Date endTime, String database, String tablename, int page, int pagesize) {
        int limitend=page*pagesize;//查询到要查询的页数所需要的分表总和条数
        int limitstart=(page*pagesize)-(pagesize-1);//开始的条数


        List<String> searchsubmeter = partitiontablerangeCache.searchtablenames(database, tablename, startTime, endTime);
        Integer totalco=0;

        List<MustSearchTableVo> mustsearchtable=new ArrayList<>();//必须查询的表;

        int firstpage=0;
        int firtpagesize=0;//记录第一张表要查询的分页

        boolean arg=true;
        if (searchsubmeter != null && searchsubmeter.size() > 0) {
            for (int i = 0; i < searchsubmeter.size(); i++) {
                String searchtable=searchsubmeter.get(i);//这边是包含进去要查询的表
                String replacetablenamesql = replacetablename(sql, tablename, searchtable);
                Integer gettotalbysql = gettotalbysql(replacetablenamesql);
                totalco+=gettotalbysql;//全部的总数
                MustSearchTableVo mustSearchTableVo=new MustSearchTableVo();
                mustSearchTableVo.setCo(gettotalbysql);
                mustSearchTableVo.setSql(searchtable);
                if(arg&&gettotalbysql>0) {
                    if (totalco >= limitend) {//如果总数大于了查询数量，那么说明表够了，结束添加查询表
                        mustsearchtable.add(mustSearchTableVo);
                        arg = false;
                    }else {//否则，如果小于，那么说明不够，添加查询表
                        if(totalco>=limitstart) {//且总数要大于等于查询的其实数量
                            mustsearchtable.add(mustSearchTableVo);
                        }
                    }
                }
            }
        }

        List<Map> allresult=new ArrayList<>();

        int size = mustsearchtable.size();
        if(size==1){//如果就一张表，那么就直接进行分页查询
            allresult=searchpage(mustsearchtable.get(0).getSql(),page,pagesize);
        }else{
            for (int i = 0; i < mustsearchtable.size(); i++) {
                MustSearchTableVo mustSearchTableVo = mustsearchtable.get(i);
                List<Map> searchpage=new ArrayList<>();
                if(i==0){//如果是第一页，那么就直接按照分页去查
                searchpage = searchpage(mustSearchTableVo.getSql(), page, pagesize);
                }else if(i==mustsearchtable.size()-1){//如果是最后一页

                }else{//如果在中间，那么就全部都查了，不考虑分页
                    searchpage = searchpage(mustSearchTableVo.getSql(), 0, 0);
                }
                allresult.addAll(searchpage);

            }
        }

        return null;
    }

    private  List<Map> searchpage(String sql,Integer page,Integer pagesize){
        page = (page - 1) * pagesize;// 获取到其实的显示数量
        String sql1 = "select * from ( ";
        sql1 += sql;
        sql1 += " )  as sdasgasgasdsa " ;
        if(page==0&&pagesize==0) {
        }else{
            sql1 += "limit " + page + "," + pagesize;
        }
        List<Map> query = jdbcUtil.sql(sql1).query(Map.class);
        return  query;
    }

    /**
     * 获取到查询总数的sql
     *
     * @return
     */
    private Integer gettotalbysql(String sql) {
        sql = "select count(1) cn from ( " + sql + "  ) as PartitiontableVo";
        int i = jdbcUtil.sql(sql).queryOneInt();
        return i;
    }


    // @PostConstruct
    private void init() {
        Date startTime = TimeUtils.todatetime("2020-08-02");
        Date endTime = TimeUtils.todatetime("2020-08-06");

        String sql = "SELECT\n" +
                "  a.id,\n" +
                "  a.speed,\n" +
                "  a.adasAlarmNo,\n" +
                "  a.alarmSource,\n" +
                "  a.alarmType,\n" +
                "  a.firstTime,\n" +
                "  a.lastTime,\n" +
                "  a.firstLocation,\n" +
                "  a.lastLocation,\n" +
                "  a.firstLatitude,\n" +
                "  a.firstLongitude,\n" +
                "  a.lastLatitude,\n" +
                "  a.lastLongitude,\n" +
                "  a.duration,\n" +
                "  a.alarmCount,\n" +
                "  a.endFlag,\n" +
                "  a.descr,\n" +
                "  v.plateNo,\n" +
                "  v.depId,\n" +
                "  a.processed,\n" +
                "  a.processedTime,\n" +
                "  a.processedUserId,\n" +
                "  a.processedUserName,\n" +
                "  a.driverName,\n" +
                "  v.plateColor,\n" +
                "  v.simNo AS vsimNo,\n" +
                "  v.vehicleId,\n" +
                "CASE\n" +
                "    a.alarmType \n" +
                "    WHEN '1' THEN\n" +
                "    1 \n" +
                "    WHEN '2' THEN\n" +
                "    1 \n" +
                "    WHEN 'OverSpeedOnRoute' THEN\n" +
                "    1 \n" +
                "    WHEN 'driver_1_1' THEN\n" +
                "    1 \n" +
                "    WHEN 'driver_1_2' THEN\n" +
                "    1 \n" +
                "    WHEN 'overspeed_1' THEN\n" +
                "    1 \n" +
                "    WHEN 'overspeed_2' THEN\n" +
                "    1 \n" +
                "    WHEN 'overspeed_3' THEN\n" +
                "    1 \n" +
                "    WHEN 'tired_1' THEN\n" +
                "    1 \n" +
                "    WHEN 'tired_2' THEN\n" +
                "    1 \n" +
                "    WHEN '13' THEN\n" +
                "    1 \n" +
                "    WHEN '14' THEN\n" +
                "    1 ELSE 2 \n" +
                "  END AS sort \n" +
                "FROM\n" +
                "  gps_hisdata.alarm_summary a,\n" +
                "  vehicle v \n" +
                "WHERE\n" +
                "  a.vehicleId = v.vehicleId \n" +
                "  AND v.deleted = FALSE \n" +
                "  AND a.firstTime >= DATE_ADD( '2020-08-03', INTERVAL - 10 DAY ) \n" +
                "  AND a.lastTime >= '2020-08-03' \n" +
                "  AND a.firstTime < DATE_ADD( '2020-08-04', INTERVAL 1 DAY ) \n" +
                "  AND a.lastTime < DATE_ADD( '2020-08-04', INTERVAL 1 DAY ) \n" +
                "  AND ( a.alarmSource = 'terminal_alarm' ) \n" +
                "  AND ( a.processed = 0 ) \n" +
                "  AND a.alarmType IN ( '1', '2' ) \n" +
                "ORDER BY\n" +
                "  sort ASC,\n" +
                "  a.lastTime DESC ";


    }


    /**
     * 字符串替换，忽略大小写
     *
     * @param aa
     * @param pattern
     * @param replacement
     * @return
     */
    private String replacetablename(String aa, String pattern, String replacement) {
        aa = aa.replaceAll(pattern, replacement);
        return aa;
    }


}
