
package ${packpath};
/**
* ${tableconment}业务层
*/
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import net.fxft.common.jdbc.ColumnSet;
import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
${tablename}.setCreatedate(new Date());
${tablename}.setUpdatedate(new Date());
jdbcUtil.insert(${tablename}).insertColumn(ColumnSet.all()).execute();
} else {//修改
${tablename}.setUpdatedate(new Date());
jdbcUtil.update(${tablename}).whereIdRefValueEQ().updateColumn(ColumnSet.all().minus(${entityName}.F_createdate)).execute();
}
}


/**
* 删除${tableconment},更改标志位,假删除
*/
public void fdelete(String Id) throws Exception {

String sql = "update ${tableschema}.${tablename} set deleted =id where ${pricolname}=? ";
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