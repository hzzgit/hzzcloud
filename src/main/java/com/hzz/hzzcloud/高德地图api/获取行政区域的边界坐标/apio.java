package com.hzz.hzzcloud.高德地图api.获取行政区域的边界坐标;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.client.RestTemplate;

public class apio {
    static double pi = 3.14159265358979324;

    //
    // Krasovsky 1940
    //
    // a = 6378245.0, 1/f = 298.3
    // b = a * (1 - f)
    // ee = (a^2 - b^2) / a^2;
    static double a = 6378245.0;
    static double ee = 0.00669342162296594323;

    public static void main(String[] args) {

        String key="a7c538c85b1d65c958a20a46761a1db1";
        RestTemplate restTemplate=new RestTemplate();
        String forObject = restTemplate.getForObject("https://restapi.amap.com/v3/config/district?keywords=福建&subdistrict=0&extensions=all&key=" + key, String.class);
        JSONObject jsonObject = JSON.parseObject(forObject);
        JSONArray districts = jsonObject.getJSONArray("districts");
        JSONObject o = districts.getJSONObject(0);
        String polyline = o.getString("polyline");
        System.out.println(polyline);

    }

}
