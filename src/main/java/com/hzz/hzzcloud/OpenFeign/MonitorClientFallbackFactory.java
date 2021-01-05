package com.hzz.hzzcloud.OpenFeign;

import com.hzz.hzzcloud.OpenFeign.vo.GetDepTreeVO;
import com.hzz.hzzcloud.OpenFeign.vo.JsonMessage;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/1 14:18
 */
@Component
public class MonitorClientFallbackFactory implements MonitorClient {


    @Override
    public JsonMessage getStores(GetDepTreeVO pageable) {
        return new JsonMessage(false,"服务降级");
    }
}
