
    package com.hzz.hzzcloud.freemarker.main.personmanage.service;
    /**
    * 人员表（唯一从业资格证和姓名，保存司机信息和押运员信息）业务层
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
    import com.hzz.hzzcloud.freemarker.main.personmanage.entity.*;
    import com.hzz.hzzcloud.freemarker.main.personmanage.exlvo.*;


    @Service
    @Slf4j
    public class PersonmanageService {

        @Autowired
        private JdbcUtil jdbcUtil;

        @Autowired
        private IQueryService queryService;

        /**
        * 分页查询人员表（唯一从业资格证和姓名，保存司机信息和押运员信息）
        */
        public PaginateResult selectlist(Map param, int page, int pagesize) {
            String queryId = "personmanagemapper.selectlist";
            PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
            return paginateResult;
        }



        /**
        * 保存人员表（唯一从业资格证和姓名，保存司机信息和押运员信息）
        */
        public void save(Personmanage personmanage) throws Exception {
            if (personmanage.getId() == 0) {//新增
            jdbcUtil.insert(personmanage).execute();
            } else {//修改
            jdbcUtil.update(personmanage).whereIdRefValueEQ().updateColumn(ColumnSet.all()).execute();
            }
        }


        /**
        * 删除人员表（唯一从业资格证和姓名，保存司机信息和押运员信息）,更改标志位,假删除
        */
        public void fdelete(String Id) throws Exception {

            String sql = "update subiaodb.personmanage set deleted =id where id=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

        /**
        * 删除人员表（唯一从业资格证和姓名，保存司机信息和押运员信息）,真删除
        */
        public void delete(String Id) throws Exception {
            String sql = "delete from  subiaodb.personmanage  where id=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

    }