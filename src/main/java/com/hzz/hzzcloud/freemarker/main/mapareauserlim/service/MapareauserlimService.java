
package com.hzz.hzzcloud.freemarker.main.mapareauserlim.service;
/**
* 区域围栏的授权用户可见性配置表业务层
*/
import lombok.extern.slf4j.Slf4j;
import java.util.*;
import net.fxft.ascswebcommon.service.IQueryService;
import net.fxft.ascswebcommon.vo.PaginateResult;
import net.fxft.common.jdbc.ColumnSet;
import net.fxft.common.jdbc.JdbcUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hzz.hzzcloud.freemarker.util.MaptoBeanUtil;
import com.hzz.hzzcloud.freemarker.main.mapareauserlim.entity.*;
import com.hzz.hzzcloud.freemarker.main.mapareauserlim.exlvo.*;


@Service
@Slf4j
public class MapareauserlimService {

@Autowired
private JdbcUtil jdbcUtil;

@Autowired
private IQueryService queryService;

/**
* 分页查询区域围栏的授权用户可见性配置表
*/
public PaginateResult selectlist(Map param, int page, int pagesize) {
String queryId = "mapareauserlimmapper.selectlist";
PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
return paginateResult;
}



/**
* 保存区域围栏的授权用户可见性配置表
*/
public void save(Mapareauserlim mapareauserlim) throws Exception {
if (mapareauserlim.getId() == 0) {//新增
jdbcUtil.insert(mapareauserlim).execute();
} else {//修改
jdbcUtil.update(mapareauserlim).whereIdRefValueEQ().updateColumn(ColumnSet.all()).execute();
}
}


/**
* 删除区域围栏的授权用户可见性配置表,更改标志位,假删除
*/
public void fdelete(String Id) throws Exception {

String sql = "update subiaodb.mapareauserlim set deleted =id where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

/**
* 删除区域围栏的授权用户可见性配置表,真删除
*/
public void delete(String Id) throws Exception {
String sql = "delete from  subiaodb.mapareauserlim  where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

}