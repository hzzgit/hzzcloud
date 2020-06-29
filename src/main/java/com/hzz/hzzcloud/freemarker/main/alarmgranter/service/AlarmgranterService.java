
    package com.hzz.hzzcloud.freemarker.main.alarmgranter.service;
    /**
    * 报警转发规则配置业务层
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
    import com.hzz.hzzcloud.freemarker.main.alarmgranter.entity.*;
    import com.hzz.hzzcloud.freemarker.main.alarmgranter.exlvo.*;


    @Service
    @Slf4j
    public class AlarmgranterService {

        @Autowired
        private JdbcUtil jdbcUtil;

        @Autowired
        private IQueryService queryService;

        /**
        * 分页查询报警转发规则配置
        */
        public PaginateResult selectlist(Map param, int page, int pagesize) {
            String queryId = "alarmgrantermapper.selectlist";
            PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
            return paginateResult;
        }



        /**
        * 保存报警转发规则配置
        */
        public void save(Alarmgranter alarmgranter) throws Exception {
            if (alarmgranter.getId() == 0) {//新增
            jdbcUtil.insert(alarmgranter).execute();
            } else {//修改
            jdbcUtil.update(alarmgranter).whereIdRefValueEQ().updateColumn(ColumnSet.all()).execute();
            }
        }


        /**
        * 删除报警转发规则配置,更改标志位,假删除
        */
        public void fdelete(String Id) throws Exception {

            String sql = "update subiaodb.alarmgranter set deleted =id where id=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

        /**
        * 删除报警转发规则配置,真删除
        */
        public void delete(String Id) throws Exception {
            String sql = "delete from  subiaodb.alarmgranter  where id=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

    }