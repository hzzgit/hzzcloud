
package nocommon.flowlimitconfig.service;
/**
* 流量阈值配置表业务层
*/
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import net.fxft.common.jdbc.ColumnSet;
import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FlowlimitconfigService {

@Autowired
private JdbcUtil jdbcUtil;

@Autowired
private IQueryService queryService;

/**
* 分页查询流量阈值配置表
*/
public PaginateResult selectlist(Map param, int page, int pagesize) {
String queryId = "flowlimitconfigmapper.selectlist";
PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
return paginateResult;
}



/**
* 保存流量阈值配置表
*/
public void save(Flowlimitconfig flowlimitconfig) throws Exception {
if (flowlimitconfig.getId() == 0) {//新增
flowlimitconfig.setCreatedate(new Date());
flowlimitconfig.setUpdatedate(new Date());
jdbcUtil.insert(flowlimitconfig).insertColumn(ColumnSet.all()).execute();
} else {//修改
flowlimitconfig.setUpdatedate(new Date());
jdbcUtil.update(flowlimitconfig).whereIdRefValueEQ().updateColumn(ColumnSet.all().minus(Flowlimitconfig.F_createdate)).execute();
}
}


/**
* 删除流量阈值配置表,更改标志位,假删除
*/
public void fdelete(String Id) throws Exception {

String sql = "update subiaodb.flowlimitconfig set deleted =id where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

/**
* 删除流量阈值配置表,真删除
*/
public void delete(String Id) throws Exception {
String sql = "delete from  subiaodb.flowlimitconfig  where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

}