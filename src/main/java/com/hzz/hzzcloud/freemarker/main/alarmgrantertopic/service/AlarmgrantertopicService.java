
    package com.hzz.hzzcloud.freemarker.main.alarmgrantertopic.service;
    /**
    * 报警转发申请开通表,用于存放公司名和topic业务层
    */
    import lombok.extern.slf4j.Slf4j;
    import java.util.*;
    import net.fxft.ascswebcommon.service.IQueryService;
    import net.fxft.ascswebcommon.vo.PaginateResult;
    import net.fxft.common.jdbc.ColumnSet;
    import net.fxft.common.jdbc.JdbcUtil;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import com.hzz.hzzcloud.freemarker.util.MaptoBeanUtil;
    import net.fxft.ascswebcommon.service.impl.UserVehicleRefCacheService;
    import net.fxft.ascswebcommon.vo.UserVehicleAuthority;
    import com.hzz.hzzcloud.freemarker.main.alarmgrantertopic.entity.*;
    import com.hzz.hzzcloud.freemarker.main.alarmgrantertopic.exlvo.*;


    @Service
    @Slf4j
    public class AlarmgrantertopicService {

        @Autowired
        private JdbcUtil jdbcUtil;

        @Autowired
        private IQueryService queryService;

        /**
        * 分页查询报警转发申请开通表,用于存放公司名和topic
        */
        public PaginateResult selectlist(Map param, int page, int pagesize) {
            String queryId = "alarmgrantertopicmapper.selectlist";
            PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
            return paginateResult;
        }



        /**
        * 保存报警转发申请开通表,用于存放公司名和topic
        */
        public void save(Alarmgrantertopic alarmgrantertopic) throws Exception {
            if (alarmgrantertopic.getId() == 0) {//新增
            jdbcUtil.insert(alarmgrantertopic).execute();
            } else {//修改
            jdbcUtil.update(alarmgrantertopic).whereIdRefValueEQ().updateColumn(ColumnSet.all()).execute();
            }
        }


        /**
        * 删除报警转发申请开通表,用于存放公司名和topic,更改标志位,假删除
        */
        public void fdelete(String Id) throws Exception {

            String sql = "update subiaodb.alarmgrantertopic set deleted =id where id=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

        /**
        * 删除报警转发申请开通表,用于存放公司名和topic,真删除
        */
        public void delete(String Id) throws Exception {
            String sql = "delete from  subiaodb.alarmgrantertopic  where id=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

    }