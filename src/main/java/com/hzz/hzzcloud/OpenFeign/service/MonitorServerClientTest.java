package com.hzz.hzzcloud.OpenFeign.service;

import com.hzz.hzzcloud.OpenFeign.MonitorClient;
import com.hzz.hzzcloud.OpenFeign.vo.GetDepTreeVO;
import com.hzz.hzzcloud.OpenFeign.vo.JsonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/1 14:15
 */
@Service
public class MonitorServerClientTest {

    @Autowired
   private MonitorClient monitorClient;

    @PostConstruct
    public void init(){

        try {
            GetDepTreeVO getDepTreeVO=new GetDepTreeVO();
            getDepTreeVO.setPageNo(1);
            getDepTreeVO.setPageSize(100);
            getDepTreeVO.setState(0);
            JsonMessage stores = monitorClient.getStores(getDepTreeVO);
            System.out.println(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
