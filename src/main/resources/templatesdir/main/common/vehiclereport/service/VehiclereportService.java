package common.vehiclereport.service;
/**
* 企业车辆信息表维护表业务层
*/
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import net.fxft.common.jdbc.ColumnSet;
import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VehiclereportService {

@Autowired
private JdbcUtil jdbcUtil;

@Autowired
private IQueryService queryService;

/**
* 分页查询企业车辆信息表维护表
*/
public PaginateResult selectlist(Map param, int page, int pagesize) {
String queryId = "vehiclereportmapper.selectlist";
PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
return paginateResult;
}



/**
* 保存企业车辆信息表维护表
*/
public void save(Vehiclereport vehiclereport) throws Exception {
if (vehiclereport.getId() == 0) {//新增
 vehiclereport.setCreatedate(new Date());
vehiclereport.setUpdatedate(new Date());
jdbcUtil.insert(vehiclereport).execute();
} else {//修改
 vehiclereport.setUpdatedate(new Date());
jdbcUtil.update(vehiclereport).whereIdRefValueEQ().updateColumn(ColumnSet.all().minus(Vehiclereport.F_createdate)).execute();
}
}


/**
* 删除企业车辆信息表维护表,更改标志位,假删除
*/
public void fdelete(String Id) throws Exception {

String sql = "update subiaodb.vehiclereport set deleted =id where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

/**
* 删除企业车辆信息表维护表,真删除
*/
public void delete(String Id) throws Exception {
String sql = "delete from  subiaodb.vehiclereport  where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

}