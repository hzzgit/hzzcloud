package common.reportpushbytime.service;
/**
* 金融报表生成，用于控制每个用户每个月什么时间段生成指定车辆的数据业务层
*/
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import net.fxft.common.jdbc.ColumnSet;
import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ReportpushbytimeService {

@Autowired
private JdbcUtil jdbcUtil;

@Autowired
private IQueryService queryService;

/**
* 分页查询金融报表生成，用于控制每个用户每个月什么时间段生成指定车辆的数据
*/
public PaginateResult selectlist(Map param, int page, int pagesize) {
String queryId = "reportpushbytimemapper.selectlist";
PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
return paginateResult;
}



/**
* 保存金融报表生成，用于控制每个用户每个月什么时间段生成指定车辆的数据
*/
public void save(Reportpushbytime reportpushbytime) throws Exception {
if (reportpushbytime.getId() == 0) {//新增
 reportpushbytime.setCreatedate(new Date());
reportpushbytime.setUpdatedate(new Date());
jdbcUtil.insert(reportpushbytime).execute();
} else {//修改
 reportpushbytime.setUpdatedate(new Date());
jdbcUtil.update(reportpushbytime).whereIdRefValueEQ().updateColumn(ColumnSet.all().minus(Reportpushbytime.F_createdate)).execute();
}
}


/**
* 删除金融报表生成，用于控制每个用户每个月什么时间段生成指定车辆的数据,更改标志位,假删除
*/
public void fdelete(String Id) throws Exception {

String sql = "update subiaodb.reportpushbytime set deleted =id where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

/**
* 删除金融报表生成，用于控制每个用户每个月什么时间段生成指定车辆的数据,真删除
*/
public void delete(String Id) throws Exception {
String sql = "delete from  subiaodb.reportpushbytime  where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

}