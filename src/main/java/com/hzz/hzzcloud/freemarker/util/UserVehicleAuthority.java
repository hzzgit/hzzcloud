package com.hzz.hzzcloud.freemarker.util;

import lombok.Data;

import java.util.List;

/**
 * 用户车辆机构权限的类
 */
@Data
public class UserVehicleAuthority {
    private List<Long> vehicleIdList;//车辆权限、排除之后的
    private List<Long> depIdList;//机构权限、排除之后的
}
