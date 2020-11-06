
package com.hzz.hzzcloud.freemarker.main.datafillingvehicle.service;
/**
* 填充的数据的车辆主键业务层
*/
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import net.fxft.common.jdbc.ColumnSet;
import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DatafillingvehicleService {

@Autowired
private JdbcUtil jdbcUtil;

@Autowired
private IQueryService queryService;

/**
* 分页查询填充的数据的车辆主键
*/
public PaginateResult selectlist(Map param, int page, int pagesize) {
String queryId = "datafillingvehiclemapper.selectlist";
PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
return paginateResult;
}



/**
* 保存填充的数据的车辆主键
*/
public void save(Datafillingvehicle datafillingvehicle) throws Exception {
if (datafillingvehicle.getId() == 0) {//新增
Datafillingvehicle datafillingvehicle.setCreatedate(new Date());
jdbcUtil.insert(datafillingvehicle).execute();
} else {//修改
Datafillingvehicle datafillingvehicle.setUpdatedate(new Date());
jdbcUtil.update(datafillingvehicle).whereIdRefValueEQ().updateColumn(ColumnSet.all()).execute();
}
}


/**
* 删除填充的数据的车辆主键,更改标志位,假删除
*/
public void fdelete(String Id) throws Exception {

String sql = "update subiaodb.datafillingvehicle set deleted =id where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

/**
* 删除填充的数据的车辆主键,真删除
*/
public void delete(String Id) throws Exception {
String sql = "delete from  subiaodb.datafillingvehicle  where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

}