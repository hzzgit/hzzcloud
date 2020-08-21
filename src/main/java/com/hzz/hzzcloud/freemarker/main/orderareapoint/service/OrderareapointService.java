
    package com.hzz.hzzcloud.freemarker.main.orderareapoint.service;
    /**
    * 物流订单围栏点位信息业务层
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
    import com.hzz.hzzcloud.freemarker.main.orderareapoint.entity.*;
    import com.hzz.hzzcloud.freemarker.main.orderareapoint.exlvo.*;


    @Service
    @Slf4j
    public class OrderareapointService {

        @Autowired
        private JdbcUtil jdbcUtil;

        @Autowired
        private IQueryService queryService;

        /**
        * 分页查询物流订单围栏点位信息
        */
        public PaginateResult selectlist(Map param, int page, int pagesize) {
            String queryId = "orderareapointmapper.selectlist";
            PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
            return paginateResult;
        }



        /**
        * 保存物流订单围栏点位信息
        */
        public void save(Orderareapoint orderareapoint) throws Exception {
            if (orderareapoint.getId() == 0) {//新增
            jdbcUtil.insert(orderareapoint).execute();
            } else {//修改
            jdbcUtil.update(orderareapoint).whereIdRefValueEQ().updateColumn(ColumnSet.all()).execute();
            }
        }


        /**
        * 删除物流订单围栏点位信息,更改标志位,假删除
        */
        public void fdelete(String Id) throws Exception {

            String sql = "update subiaodb.orderareapoint set deleted =id where id=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

        /**
        * 删除物流订单围栏点位信息,真删除
        */
        public void delete(String Id) throws Exception {
            String sql = "delete from  subiaodb.orderareapoint  where id=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

    }