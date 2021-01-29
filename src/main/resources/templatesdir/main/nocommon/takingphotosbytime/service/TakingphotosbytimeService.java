
package nocommon.takingphotosbytime.service;
/**
* 定时拍照配置表业务层
*/
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import net.fxft.common.jdbc.ColumnSet;
import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TakingphotosbytimeService {

@Autowired
private JdbcUtil jdbcUtil;

@Autowired
private IQueryService queryService;

/**
* 分页查询定时拍照配置表
*/
public PaginateResult selectlist(Map param, int page, int pagesize) {
String queryId = "takingphotosbytimemapper.selectlist";
PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
return paginateResult;
}



/**
* 保存定时拍照配置表
*/
public void save(Takingphotosbytime takingphotosbytime) throws Exception {
if (takingphotosbytime.getId() == 0) {//新增
takingphotosbytime.setCreatedate(new Date());
takingphotosbytime.setUpdatedate(new Date());
jdbcUtil.insert(takingphotosbytime).insertColumn(ColumnSet.all()).execute();
} else {//修改
takingphotosbytime.setUpdatedate(new Date());
jdbcUtil.update(takingphotosbytime).whereIdRefValueEQ().updateColumn(ColumnSet.all().minus(Takingphotosbytime.F_createdate)).execute();
}
}


/**
* 删除定时拍照配置表,更改标志位,假删除
*/
public void fdelete(String Id) throws Exception {

String sql = "update subiaodb.takingphotosbytime set deleted =id where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

/**
* 删除定时拍照配置表,真删除
*/
public void delete(String Id) throws Exception {
String sql = "delete from  subiaodb.takingphotosbytime  where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

}