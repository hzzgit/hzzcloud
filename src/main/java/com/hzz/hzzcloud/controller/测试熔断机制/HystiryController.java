package com.hzz.hzzcloud.controller.测试熔断机制;

import com.hzz.hzzcloud.OpenFeign.MonitorClient;
import com.hzz.hzzcloud.OpenFeign.vo.GetDepTreeVO;
import com.hzz.hzzcloud.OpenFeign.vo.JsonMessage;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/1/5 15:24
 */
@RestController
@RequestMapping("hystiry")
public class HystiryController extends RestTemplateApi {

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

    /**
     * 获取车辆列表
     * @return
     */
    @RequestMapping("/getonlinevehicle.action")
    public Object getonlinevehicle(){
        Map<String,Object> param=new HashMap<>();
        param.put("rows",10);
        param.put("page",2);
        GetDepTreeVO pageable=new GetDepTreeVO();
        pageable.setPageSize(30);
       // String post = Post("", 10, param);
       // String post2 = PostJson("", 10, pageable);
        String get = get("http://monitorwebapice1/realData/getonlinevehicle.action", 10, param);

        return get;
    }

    public static void main(String[] args) {
        String s = DigestUtils.md5Hex("123456abc");
        System.out.println(s);
    }
}
