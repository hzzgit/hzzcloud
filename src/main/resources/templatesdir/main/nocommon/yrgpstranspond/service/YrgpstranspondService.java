
package nocommon.yrgpstranspond.service;
/**
* 奕人gps转发基于protobuffer协议配置表业务层
*/
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import net.fxft.common.jdbc.ColumnSet;
import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class YrgpstranspondService {

@Autowired
private JdbcUtil jdbcUtil;

@Autowired
private IQueryService queryService;

/**
* 分页查询奕人gps转发基于protobuffer协议配置表
*/
public PaginateResult selectlist(Map param, int page, int pagesize) {
String queryId = "yrgpstranspondmapper.selectlist";
PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
return paginateResult;
}



/**
* 保存奕人gps转发基于protobuffer协议配置表
*/
public void save(Yrgpstranspond yrgpstranspond) throws Exception {
if (yrgpstranspond.getId() == 0) {//新增
yrgpstranspond.setCreatedate(new Date());
yrgpstranspond.setUpdatedate(new Date());
jdbcUtil.insert(yrgpstranspond).execute();
} else {//修改
yrgpstranspond.setUpdatedate(new Date());
jdbcUtil.update(yrgpstranspond).whereIdRefValueEQ().updateColumn(ColumnSet.all().minus(Yrgpstranspond.F_createdate)).execute();
}
}


/**
* 删除奕人gps转发基于protobuffer协议配置表,更改标志位,假删除
*/
public void fdelete(String Id) throws Exception {

String sql = "update subiaodb.yrgpstranspond set deleted =id where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

/**
* 删除奕人gps转发基于protobuffer协议配置表,真删除
*/
public void delete(String Id) throws Exception {
String sql = "delete from  subiaodb.yrgpstranspond  where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

}