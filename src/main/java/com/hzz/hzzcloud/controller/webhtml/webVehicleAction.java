package com.hzz.hzzcloud.controller.webhtml;

import com.hzz.hzzjdbc.submeter.util.JdbcSearchSqlUtil;
import net.fxft.common.jdbc.JdbcUtil;
import net.fxft.common.jdbc.RowDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/18 14:56
 */
@RestController
@RequestMapping("/vehicle")
public class webVehicleAction {

    @Autowired
    private JdbcUtil jdbcUtil;

    @RequestMapping("/search")
    public LayUiMessage search(int page, int limit) {
        String sql = "select * from vehicle ";
        JdbcSearchSqlUtil jdbcSearchSqlUtil = new JdbcSearchSqlUtil(sql);
        String sqlByPage = jdbcSearchSqlUtil.getSqlByPage(page, limit);
        String sqlByCount = jdbcSearchSqlUtil.getSqlByCount();
        long count = jdbcUtil.sql(sqlByCount).queryOneLong();
        List<RowDataMap> rowDataMaps = jdbcUtil.sql(sqlByPage).queryWithMap();
        LayUiMessage search = LayUiMessage.search(count, rowDataMaps);
        return search;
    }

    @RequestMapping("/delete")
    public LayUiMessage delete(int vehicleId) {
        String sql = "select * from vehicle where vehicleId=? ";
        RowDataMap rowDataMap = jdbcUtil.sql(sql).addIndexParam(vehicleId).queryFirstWithMap();

        LayUiMessage search = LayUiMessage.search(1, rowDataMap);
        return search;
    }
}
