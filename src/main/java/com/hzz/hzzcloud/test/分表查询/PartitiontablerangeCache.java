package com.hzz.hzzcloud.test.分表查询;

import com.hzz.hzzjdbc.jdbcutil.util.TimeUtils;
import lombok.extern.slf4j.Slf4j;
import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/4 19:02
 */
@Service
@Slf4j
public class PartitiontablerangeCache {

    private ConcurrentHashMap<String, List<PartitiontableVo>> partitiontablecache = new ConcurrentHashMap<>();

    @Autowired
    private JdbcUtil jdbcUtil;


   // @PostConstruct
    private void init() {
        try {
            String sql = "select  tableSchema,tableName,baseTableName,dataEndDate,dataBeginDate" +
                    " from partitiontablerange_copy1  order by dataBeginDate";
            List<PartitiontableVo> query = jdbcUtil.sql(sql).query(PartitiontableVo.class);
            if (query != null && query.size() > 0) {
                for (PartitiontableVo partitiontableVo : query) {
                    List<PartitiontableVo> data = new ArrayList<>();
                    String baseTableName = partitiontableVo.getBasetablename().toLowerCase();
                    String tableschema = partitiontableVo.getTableschema().toLowerCase();
                    String key = getkey(tableschema, baseTableName);
                    if (partitiontablecache.containsKey(baseTableName)) {
                        data = partitiontablecache.get(key);
                    }
                    data.add(partitiontableVo);
                    partitiontablecache.put(key, data);
                }
            }
        } catch (Exception e) {
            log.error("缓存分表配置表异常", e);
        }
    }


    /**
     * 查询到所在分表在哪一张
     */
    public List<String> searchtablenames(String tableschema, String tablename, Date startTime, Date endTime) {
        String key = getkey(tableschema, tablename);
        List<String> tablenames = new ArrayList<>();
        if (partitiontablecache.containsKey(key)) {
            List<PartitiontableVo> partitiontableVos = partitiontablecache.get(key);
            if (partitiontableVos != null && partitiontableVos.size() > 0) {
                for (PartitiontableVo partitiontableVo : partitiontableVos) {
                    Date dataBeginDate = TimeUtils.date(partitiontableVo.getDatabegindate());
                    Date dataEndDate = TimeUtils.date(partitiontableVo.getDataenddate());
                    String tableName = partitiontableVo.getTablename();
                    if (dataEndDate.getTime() > startTime.getTime() && dataBeginDate.getTime() < endTime.getTime()
                            && dataEndDate.getTime() < endTime.getTime()) {
                        tablenames.add(tableName);
                    }
                }
            }
        }
        return tablenames;
    }

    /**
     * 获取key
     * @param tableschema
     * @param baseTableName
     * @return
     */
    private String getkey(String tableschema, String baseTableName) {
        String key = tableschema.toLowerCase() + "." + baseTableName.toLowerCase();
        return key;
    }


}
