package com.hzz.hzzcloud.OpenFeign;

import com.hzz.hzzcloud.OpenFeign.vo.GetDepTreeVO;
import com.hzz.hzzcloud.OpenFeign.vo.JsonMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/1 14:12
 */
@FeignClient(name = "subiaoweb")
//@FeignClient("monitorwebapi" )
public interface SubiaodbClient {


    /**
     * 查询报警管理
     * @param pageable
     * @return
     */
    @PostMapping(value = "/vehicle/getDepTreexiamen.action")
    JsonMessage alarmSummaryList(@RequestParam GetDepTreeVO pageable);




}
