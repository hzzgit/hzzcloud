package com.hzz.hzzcloud.OpenFeign;

import com.hzz.hzzcloud.OpenFeign.vo.GetDepTreeVO;
import com.hzz.hzzcloud.OpenFeign.vo.JsonMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/1 14:12
 */
//@FeignClient(name = "monitorwebapi",fallback =MonitorClientFallbackFactory.class )
@FeignClient("monitorwebapi" )
public interface MonitorClient {


    @PostMapping(value = "/vehicle/getDepTreexiamen.action")
    JsonMessage getStores(@RequestBody  GetDepTreeVO pageable);




}
