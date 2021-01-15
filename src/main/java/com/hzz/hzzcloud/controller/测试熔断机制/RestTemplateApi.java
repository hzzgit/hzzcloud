package com.hzz.hzzcloud.controller.测试熔断机制;

import com.alibaba.fastjson.JSON;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/1/14 10:00
 */
public class RestTemplateApi {
    @Autowired
    private RestTemplate rt;


    /**
     * get请求
     *
     * @param url
     * @param userId
     * @param param
     * @return
     * @throws RuntimeException
     */
    public String get(String url, int userId, Map param) throws RuntimeException {
        if (userId == 0) {
            throw new RuntimeException("用户id不能为空");
        }
        List<Object> paramVals=new ArrayList<>();

        StringBuilder urlBuilder=new StringBuilder(url);
        if(param!=null&&param.size()>0){
            urlBuilder.append("?");
            param.forEach((p,v)->{
                urlBuilder.append(p);
                urlBuilder.append("="+v+"&&");
            });
        }
        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("ssoUserId", String.valueOf(userId));
        ResponseEntity<String> response = rt.exchange(
                urlBuilder.toString(),
                HttpMethod.GET,
                new HttpEntity<String>(requestHeaders),
                String.class);
        return response.getBody();
    }

    /**
     * post请求
     *
     * @param url
     * @param userId
     * @param param
     * @return
     * @throws RuntimeException
     */
    public String Post(String url, int userId, Map param) throws RuntimeException {
        if (userId == 0) {
            throw new RuntimeException("用户id不能为空");
        }
        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("ssoUserId", String.valueOf(userId));
        //body
        MultiValueMap<Object, Object> requestBody = new LinkedMultiValueMap<>();
        if (param != null && param.size() > 0) {
            param.forEach((p, v) -> {
                requestBody.add(p, v);
            });
        }
        //HttpEntity
        HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(requestBody, requestHeaders);
        ResponseEntity<String> stringResponseEntity = rt.postForEntity(url, requestEntity, String.class);
        return stringResponseEntity.getBody();
    }

    /**
     * postjson请求
     *
     * @param url
     * @param userId
     * @param param
     * @return
     * @throws RuntimeException
     */
    public <T> String PostJson(String url, int userId, T param) {
        if (userId == 0) {
            throw new RuntimeException("用户id不能为空");
        }
        //headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("ssoUserId", String.valueOf(userId));
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
        //HttpEntity
        HttpEntity requestEntity = new HttpEntity(param, requestHeaders);
        ResponseEntity<String> stringResponseEntity = rt.postForEntity(url, requestEntity, String.class);
        return stringResponseEntity.getBody();
    }


}
