package com.hzz.hzzcloud.从exl中读取数据生成实体类;

import lombok.Data;

import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/6/10 9:30
 */
@Data
public class ReadExlToBeanVO {

    private List<ReadExlToBean> readExlToBeanList;

    private String entityName;
}
