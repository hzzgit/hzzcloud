//package com.hzz.hzzcloud.OpenFeign;
//
//import com.hzz.hzzcloud.OpenFeign.DO.GetDepTreeVO;
//import com.hzz.hzzcloud.OpenFeign.DO.JsonMessage;
//import feign.hystrix.FallbackFactory;
//import org.springframework.stereotype.Component;
//
///**
// * @author ：hzz
// * @description：TODO
// * @date ：2020/12/1 14:18
// */
//@Component
//public class MonitorClientFallbackFactory implements FallbackFactory<MonitorClient> {
//
//
//    @Override
//    public MonitorClient create(Throwable throwable) {
//        return new MonitorClient() {
//
//            @Override
//            public JsonMessage getStores(GetDepTreeVO pageable) {
//                return null;
//            }
//        };
//    }
//}
