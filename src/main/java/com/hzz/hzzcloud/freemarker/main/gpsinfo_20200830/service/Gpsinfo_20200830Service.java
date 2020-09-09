
    package com.hzz.hzzcloud.freemarker.main.gpsinfo_20200830.service;
    /**
    * 业务层
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
    import com.hzz.hzzcloud.freemarker.main.gpsinfo_20200830.entity.*;
    import com.hzz.hzzcloud.freemarker.main.gpsinfo_20200830.exlvo.*;


    @Service
    @Slf4j
    public class Gpsinfo_20200830Service {

        @Autowired
        private JdbcUtil jdbcUtil;

        @Autowired
        private IQueryService queryService;

        /**
        * 分页查询
        */
        public PaginateResult selectlist(Map param, int page, int pagesize) {
            String queryId = "gpsinfo_20200830mapper.selectlist";
            PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
            return paginateResult;
        }



        /**
        * 保存
        */
        public void save(Gpsinfo_20200830 gpsinfo_20200830) throws Exception {
            if (gpsinfo_20200830.getGpsid() == 0) {//新增
            jdbcUtil.insert(gpsinfo_20200830).execute();
            } else {//修改
            jdbcUtil.update(gpsinfo_20200830).whereIdRefValueEQ().updateColumn(ColumnSet.all()).execute();
            }
        }


        /**
        * 删除,更改标志位,假删除
        */
        public void fdelete(String Id) throws Exception {

            String sql = "update gps_hisdata.gpsinfo_20200830 set deleted =id where gpsid=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

        /**
        * 删除,真删除
        */
        public void delete(String Id) throws Exception {
            String sql = "delete from  gps_hisdata.gpsinfo_20200830  where gpsid=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

    }