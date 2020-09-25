
package com.hzz.hzzcloud.freemarker.main.mapareabydep.service;
/**
* 区域围栏和机构权限表业务层
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
import com.hzz.hzzcloud.freemarker.main.mapareabydep.entity.*;
import com.hzz.hzzcloud.freemarker.main.mapareabydep.exlvo.*;


@Service
@Slf4j
public class MapareabydepService {

@Autowired
private JdbcUtil jdbcUtil;

@Autowired
private IQueryService queryService;

/**
* 分页查询区域围栏和机构权限表
*/
public PaginateResult selectlist(Map param, int page, int pagesize) {
String queryId = "mapareabydepmapper.selectlist";
PaginateResult paginateResult = queryService.queryByPagination(queryId, param, page, pagesize);
return paginateResult;
}



/**
* 保存区域围栏和机构权限表
*/
public void save(Mapareabydep mapareabydep) throws Exception {
if (mapareabydep.getId() == 0) {//新增
jdbcUtil.insert(mapareabydep).execute();
} else {//修改
jdbcUtil.update(mapareabydep).whereIdRefValueEQ().updateColumn(ColumnSet.all()).execute();
}
}


/**
* 删除区域围栏和机构权限表,更改标志位,假删除
*/
public void fdelete(String Id) throws Exception {

String sql = "update subiaodb.mapareabydep set deleted =id where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

/**
* 删除区域围栏和机构权限表,真删除
*/
public void delete(String Id) throws Exception {
String sql = "delete from  subiaodb.mapareabydep  where id=? ";
jdbcUtil.sql(sql).addIndexParam(Id).executeUpdate();
}

}