package com.hzz.hzzcloud.freemarker.util;


import com.hzz.hzzcloud.jdbcutil.util.ConverterUtils;
import net.fxft.ascswebcommon.vo.UserVehicleAuthority;

import java.util.Map;

public class quanxianUtil {

    //获取权限的Map
    public static Map getquanxian(UserVehicleAuthority uservehicleauthority,Map param){
        if(uservehicleauthority!=null) {
            if (ConverterUtils.isList(uservehicleauthority.getDepIdList())) {
                param.put("depIdList", uservehicleauthority.getDepIdList());
            }
            if (ConverterUtils.isList(uservehicleauthority.getVehicleIdList())) {
                param.put("vehicleIdList", uservehicleauthority.getVehicleIdList());
            }
        }
        return param;
    }
}
