
    package com.hzz.hzzcloud.freemarker.main.alarmgranterbydep.service;
    /**
    * 报警转发规则和机构权限表业务层
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
    import com.hzz.hzzcloud.freemarker.main.alarmgranterbydep.entity.*;
    import com.hzz.hzzcloud.freemarker.main.alarmgranterbydep.exlvo.*;


    @Service
    @Slf4j
    public class AlarmgranterbydepService {

        @Autowired
        private JdbcUtil jdbcUtil;

        @Autowired
        private IQueryService queryService;

        /**
        * 分页查询报警转发规则和机构权限表
        */
        public PaginateResult selectlist(Map param, int page, int pagesize) {
            String queryId = "alarmgranterbydepmapper.selectlist";
            PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
            return paginateResult;
        }



        /**
        * 保存报警转发规则和机构权限表
        */
        public void save(Alarmgranterbydep alarmgranterbydep) throws Exception {
            if (alarmgranterbydep.getId() == 0) {//新增
            jdbcUtil.insert(alarmgranterbydep).execute();
            } else {//修改
            jdbcUtil.update(alarmgranterbydep).whereIdRefValueEQ().updateColumn(ColumnSet.all()).execute();
            }
        }


        /**
        * 删除报警转发规则和机构权限表,更改标志位,假删除
        */
        public void fdelete(String Id) throws Exception {

            String sql = "update subiaodb.alarmgranterbydep set deleted =id where id=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

        /**
        * 删除报警转发规则和机构权限表,真删除
        */
        public void delete(String Id) throws Exception {
            String sql = "delete from  subiaodb.alarmgranterbydep  where id=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

    }