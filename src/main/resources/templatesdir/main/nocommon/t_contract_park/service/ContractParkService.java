
package nocommon.t_contract_park.service;
/**
* 车场合同业务层
*/
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import net.fxft.common.jdbc.ColumnSet;
import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ContractParkService {

@Autowired
private JdbcUtil jdbcUtil;

@Autowired
private IQueryService queryService;

/**
* 分页查询车场合同
*/
public PaginateResult selectlist(Map param, int page, int pagesize) {
String queryId = "t_contract_parkmapper.selectlist";
PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
return paginateResult;
}



/**
* 保存车场合同
*/
public void save(ContractPark t_contract_park) throws Exception {
if (t_contract_park.getContractid() == 0) {//新增
t_contract_park.setCreatedate(new Date());
t_contract_park.setUpdatedate(new Date());
jdbcUtil.insert(t_contract_park).insertColumn(ColumnSet.all()).execute();
} else {//修改
t_contract_park.setUpdatedate(new Date());
jdbcUtil.update(t_contract_park).whereIdRefValueEQ().updateColumn(ColumnSet.all().minus(ContractPark.F_createdate)).execute();
}
}


/**
* 删除车场合同,更改标志位,假删除
*/
public void fdelete(String Id) throws Exception {

String sql = "update sync.t_contract_park set deleted =id where contractId=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

/**
* 删除车场合同,真删除
*/
public void delete(String Id) throws Exception {
String sql = "delete from  sync.t_contract_park  where contractId=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

}