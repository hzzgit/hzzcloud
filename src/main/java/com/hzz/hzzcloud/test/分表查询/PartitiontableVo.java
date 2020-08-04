package com.hzz.hzzcloud.test.分表查询;

import lombok.Data;

/**
 * @author ：hzz
 * @description：分表查询实体类
 * @date ：2020/8/4 19:08
 */
@Data
public class PartitiontableVo {

    private String tableschema;

    private String tablename;
    private String basetablename;
    private String dataenddate;
    private String databegindate;


}
