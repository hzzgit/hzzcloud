
    package com.hzz.hzzcloud.freemarker.main.talkchannel.service;
    /**
    * 对讲频道管理业务层
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
    import com.hzz.hzzcloud.freemarker.main.talkchannel.entity.*;
    import com.hzz.hzzcloud.freemarker.main.talkchannel.exlvo.*;


    @Service
    @Slf4j
    public class TalkchannelService {

        @Autowired
        private JdbcUtil jdbcUtil;

        @Autowired
        private IQueryService queryService;

        /**
        * 分页查询对讲频道管理
        */
        public PaginateResult selectlist(Map param, int page, int pagesize) {
            String queryId = "talkchannelmapper.selectlist";
            PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
            return paginateResult;
        }



        /**
        * 保存对讲频道管理
        */
        public void save(Talkchannel talkchannel) throws Exception {
            if (talkchannel.getId() == 0) {//新增
            jdbcUtil.insert(talkchannel).execute();
            } else {//修改
            jdbcUtil.update(talkchannel).whereIdRefValueEQ().updateColumn(ColumnSet.all()).execute();
            }
        }


        /**
        * 删除对讲频道管理,更改标志位,假删除
        */
        public void fdelete(String Id) throws Exception {

            String sql = "update subiaodb.talkchannel set deleted =id where id=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

        /**
        * 删除对讲频道管理,真删除
        */
        public void delete(String Id) throws Exception {
            String sql = "delete from  subiaodb.talkchannel  where id=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

    }