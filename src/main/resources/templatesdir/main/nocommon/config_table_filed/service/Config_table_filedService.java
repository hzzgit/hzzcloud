
package nocommon.config_table_filed.service;
/**
* 用于管理平台配置表的信息的表字段配置表业务层
*/
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import net.fxft.common.jdbc.ColumnSet;
import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Config_table_filedService {

@Autowired
private JdbcUtil jdbcUtil;

@Autowired
private IQueryService queryService;

/**
* 分页查询用于管理平台配置表的信息的表字段配置表
*/
public PaginateResult selectlist(Map param, int page, int pagesize) {
String queryId = "config_table_filedmapper.selectlist";
PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
return paginateResult;
}



/**
* 保存用于管理平台配置表的信息的表字段配置表
*/
public void save(Config_table_filed config_table_filed) throws Exception {
if (config_table_filed.getId() == 0) {//新增
config_table_filed.setCreatedate(new Date());
config_table_filed.setUpdatedate(new Date());
jdbcUtil.insert(config_table_filed).insertColumn(ColumnSet.all()).execute();
} else {//修改
config_table_filed.setUpdatedate(new Date());
jdbcUtil.update(config_table_filed).whereIdRefValueEQ().updateColumn(ColumnSet.all().minus(Config_table_filed.F_createdate)).execute();
}
}


/**
* 删除用于管理平台配置表的信息的表字段配置表,更改标志位,假删除
*/
public void fdelete(String Id) throws Exception {

String sql = "update subiaodb.config_table_filed set deleted =id where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

/**
* 删除用于管理平台配置表的信息的表字段配置表,真删除
*/
public void delete(String Id) throws Exception {
String sql = "delete from  subiaodb.config_table_filed  where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

}