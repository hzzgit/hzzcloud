
package nocommon.vehicle.service;
/**
* 车辆表业务层
*/
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import net.fxft.common.jdbc.ColumnSet;
import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VehicleService {

@Autowired
private JdbcUtil jdbcUtil;

@Autowired
private IQueryService queryService;

/**
* 分页查询车辆表
*/
public PaginateResult selectlist(Map param, int page, int pagesize) {
String queryId = "vehiclemapper.selectlist";
PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
return paginateResult;
}



/**
* 保存车辆表
*/
public void save(Vehicle vehicle) throws Exception {
if (vehicle.getVehicleid() == 0) {//新增
vehicle.setCreatedate(new Date());
vehicle.setUpdatedate(new Date());
jdbcUtil.insert(vehicle).insertColumn(ColumnSet.all()).execute();
} else {//修改
vehicle.setUpdatedate(new Date());
jdbcUtil.update(vehicle).whereIdRefValueEQ().updateColumn(ColumnSet.all().minus(Vehicle.F_createdate)).execute();
}
}


/**
* 删除车辆表,更改标志位,假删除
*/
public void fdelete(String Id) throws Exception {

String sql = "update subiaodb.vehicle set deleted =id where vehicleid=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

/**
* 删除车辆表,真删除
*/
public void delete(String Id) throws Exception {
String sql = "delete from  subiaodb.vehicle  where vehicleid=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

}