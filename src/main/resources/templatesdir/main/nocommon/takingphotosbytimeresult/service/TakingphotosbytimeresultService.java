
package nocommon.takingphotosbytimeresult.service;
/**
* 定时拍照结果表(总表)业务层
*/
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import net.fxft.common.jdbc.ColumnSet;
import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TakingphotosbytimeresultService {

@Autowired
private JdbcUtil jdbcUtil;

@Autowired
private IQueryService queryService;

/**
* 分页查询定时拍照结果表(总表)
*/
public PaginateResult selectlist(Map param, int page, int pagesize) {
String queryId = "takingphotosbytimeresultmapper.selectlist";
PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
return paginateResult;
}



/**
* 保存定时拍照结果表(总表)
*/
public void save(Takingphotosbytimeresult takingphotosbytimeresult) throws Exception {
if (takingphotosbytimeresult.getId() == 0) {//新增
takingphotosbytimeresult.setCreatedate(new Date());
takingphotosbytimeresult.setUpdatedate(new Date());
jdbcUtil.insert(takingphotosbytimeresult).execute();
} else {//修改
takingphotosbytimeresult.setUpdatedate(new Date());
jdbcUtil.update(takingphotosbytimeresult).whereIdRefValueEQ().updateColumn(ColumnSet.all().minus(Takingphotosbytimeresult.F_createdate)).execute();
}
}


/**
* 删除定时拍照结果表(总表),更改标志位,假删除
*/
public void fdelete(String Id) throws Exception {

String sql = "update subiaodb.takingphotosbytimeresult set deleted =id where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

/**
* 删除定时拍照结果表(总表),真删除
*/
public void delete(String Id) throws Exception {
String sql = "delete from  subiaodb.takingphotosbytimeresult  where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

}