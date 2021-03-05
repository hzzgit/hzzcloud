package common.config_table.service;
/**
* 用于管理平台配置表的信息的表结构存储表业务层
*/
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import net.fxft.common.jdbc.ColumnSet;
import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Config_tableService {

@Autowired
private JdbcUtil jdbcUtil;

@Autowired
private IQueryService queryService;

/**
* 分页查询用于管理平台配置表的信息的表结构存储表
*/
public PaginateResult selectlist(Map param, int page, int pagesize) {
String queryId = "config_tablemapper.selectlist";
PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
return paginateResult;
}



/**
* 保存用于管理平台配置表的信息的表结构存储表
*/
public void save(Config_table config_table) throws Exception {
if (config_table.getId() == 0) {//新增
 config_table.setCreatedate(new Date());
config_table.setUpdatedate(new Date());
jdbcUtil.insert(config_table).insertColumn(ColumnSet.all()).execute();
} else {//修改
 config_table.setUpdatedate(new Date());
jdbcUtil.update(config_table).whereIdRefValueEQ().updateColumn(ColumnSet.all().minus(Config_table.F_createdate)).execute();
}
}


/**
* 删除用于管理平台配置表的信息的表结构存储表,更改标志位,假删除
*/
public void fdelete(String Id) throws Exception {

String sql = "update subiaodb.config_table set deleted =id where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

/**
* 删除用于管理平台配置表的信息的表结构存储表,真删除
*/
public void delete(String Id) throws Exception {
String sql = "delete from  subiaodb.config_table  where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

}