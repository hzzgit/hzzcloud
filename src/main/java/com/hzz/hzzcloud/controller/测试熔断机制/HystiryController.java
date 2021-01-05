package com.hzz.hzzcloud.controller.测试熔断机制;

import com.hzz.hzzcloud.OpenFeign.MonitorClient;
import com.hzz.hzzcloud.OpenFeign.vo.GetDepTreeVO;
import com.hzz.hzzcloud.OpenFeign.vo.JsonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/1/5 15:24
 */
@RestController
@RequestMapping("hystiry")
public class HystiryController {

    @Autowired
    private MonitorClient monitorClient;

    @RequestMapping("searchmonitor")
    public JsonMessage searchmonitor() {
        GetDepTreeVO getDepTreeVO = new GetDepTreeVO();
        getDepTreeVO.setPageNo(1);
        getDepTreeVO.setPageSize(100);
        getDepTreeVO.setState(0);
        return monitorClient.getStores(getDepTreeVO);
    }
}
