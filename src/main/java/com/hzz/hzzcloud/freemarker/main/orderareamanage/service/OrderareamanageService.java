
    package com.hzz.hzzcloud.freemarker.main.orderareamanage.service;
    /**
    * 物流订单围栏管理业务层
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
    import com.hzz.hzzcloud.freemarker.main.orderareamanage.entity.*;
    import com.hzz.hzzcloud.freemarker.main.orderareamanage.exlvo.*;


    @Service
    @Slf4j
    public class OrderareamanageService {

        @Autowired
        private JdbcUtil jdbcUtil;

        @Autowired
        private IQueryService queryService;

        /**
        * 分页查询物流订单围栏管理
        */
        public PaginateResult selectlist(Map param, int page, int pagesize) {
            String queryId = "orderareamanagemapper.selectlist";
            PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
            return paginateResult;
        }



        /**
        * 保存物流订单围栏管理
        */
        public void save(Orderareamanage orderareamanage) throws Exception {
            if (orderareamanage.getId() == 0) {//新增
            jdbcUtil.insert(orderareamanage).execute();
            } else {//修改
            jdbcUtil.update(orderareamanage).whereIdRefValueEQ().updateColumn(ColumnSet.all()).execute();
            }
        }


        /**
        * 删除物流订单围栏管理,更改标志位,假删除
        */
        public void fdelete(String Id) throws Exception {

            String sql = "update subiaodb.orderareamanage set deleted =id where id=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

        /**
        * 删除物流订单围栏管理,真删除
        */
        public void delete(String Id) throws Exception {
            String sql = "delete from  subiaodb.orderareamanage  where id=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

    }