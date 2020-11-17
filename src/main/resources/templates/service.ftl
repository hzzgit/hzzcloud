
    package ${packageVo.service};
    /**
    * ${tableconment}业务层
    */
    import lombok.extern.slf4j.Slf4j;
    import java.util.*;
    import net.fxft.ascswebcommon.service.IQueryService;
    import net.fxft.ascswebcommon.vo.PaginateResult;
    import net.fxft.common.jdbc.ColumnSet;
    import net.fxft.common.jdbc.JdbcUtil;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.stereotype.Service;
    import net.fxft.ascswebcommon.service.impl.UserVehicleRefCacheService;
    import net.fxft.ascswebcommon.vo.UserVehicleAuthority;


    @Service
    @Slf4j
    public class ${entityName}Service {

        @Autowired
        private JdbcUtil jdbcUtil;

        @Autowired
        private IQueryService queryService;

        /**
        * 分页查询${tableconment}
        */
        public PaginateResult selectlist(Map param, int page, int pagesize) {
            String queryId = "${tablename}mapper.selectlist";
            PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
            return paginateResult;
        }



    /**
    * 保存${tableconment}
    */
    public void save(${entityName} ${tablename}) throws Exception {
    if (${tablename}.get${pricolname?lower_case?cap_first}() == 0) {//新增
    ${entityName} ${tablename}.setCreatedate(new Date());
    jdbcUtil.insert(${tablename}).execute();
    } else {//修改
    ${entityName} ${tablename}.setUpdatedate(new Date());
    jdbcUtil.update(${tablename}).whereIdRefValueEQ().updateColumn(ColumnSet.all()).execute();
    }
    }


        /**
        * 删除${tableconment},更改标志位,假删除
        */
        public void fdelete(String Id) throws Exception {

            String sql = "update ${tableschema}.${tablename} set deleted =${pricolname} where ${pricolname}=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

        /**
        * 删除${tableconment},真删除
        */
        public void delete(String Id) throws Exception {
            String sql = "delete from  ${tableschema}.${tablename}  where ${pricolname}=? ";
            jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
        }

    }