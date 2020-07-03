
    package com.hzz.hzzcloud.freemarker.main.gpstransferrepairlog.service;
    /**
    * 历史轨迹转发补传日志表业务层
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
    import com.hzz.hzzcloud.freemarker.main.gpstransferrepairlog.entity.*;
    import com.hzz.hzzcloud.freemarker.main.gpstransferrepairlog.exlvo.*;


    @Service
    @Slf4j
    public class GpstransferrepairlogService {

        @Autowired
        private JdbcUtil jdbcUtil;

        @Autowired
        private IQueryService queryService;

        /**
        * 分页查询历史轨迹转发补传日志表
        */
        public PaginateResult selectlist(Map param, int page, int pagesize) {
            String queryId = "gpstransferrepairlogmapper.selectlist";
            PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
            return paginateResult;
        }



        /**
        * 保存历史轨迹转发补传日志表
        */
        public void save(Gpstransferrepairlog gpstransferrepairlog) throws Exception {
            if (gpstransferrepairlog.getId() == 0) {//新增
            jdbcUtil.insert(gpstransferrepairlog).execute();
            } else {//修改
            jdbcUtil.update(gpstransferrepairlog).whereIdRefValueEQ().updateColumn(ColumnSet.all()).execute();
            }
        }


        /**
        * 删除历史轨迹转发补传日志表,更改标志位,假删除
        */
        public void fdelete(String Id) throws Exception {

            String sql = "update subiaodb.gpstransferrepairlog set deleted =id where id=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

        /**
        * 删除历史轨迹转发补传日志表,真删除
        */
        public void delete(String Id) throws Exception {
            String sql = "delete from  subiaodb.gpstransferrepairlog  where id=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

    }