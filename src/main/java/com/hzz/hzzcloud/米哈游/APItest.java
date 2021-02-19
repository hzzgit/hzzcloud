package com.hzz.hzzcloud.米哈游;

import net.fxft.common.util.JacksonUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2021/2/19 9:37
 */
public class APItest {

    RestTemplate rt =new RestTemplate();
    HttpHeaders requestHeaders = new HttpHeaders();
    public static  String Cookie="_MHYUUID=02cf79c9-6717-4c2f-a4e2-bb39d534c026; UM_distinctid=175b0c598b544d-0cdd5771a4b6c5-376b4502-1fa400-175b0c598b6851; _ga=GA1.2.1356378899.1612505360; _gid=GA1.2.937261832.1613697273; login_uid=190601568; login_ticket=Wo8Lh5ESWxrTvU1g8MLhUsbtby5vxb72XkmAjjJN; account_id=190601568; cookie_token=MZWtgkAVp1TYLqa3pHo2Fq5Z0MvkphFvjWFk1ebI; ltoken=1pG5e7TJUWwnlrvZ5ufYHBwv185kJv7LYsOeujBx; ltuid=190601568";
    public static  String DS="1613701552,6nQTsE,c8bb77fb2c2e9ba27b9b5cdbd3a1bc66";
    public static  String DS2="1613705852,ktQMaF,08fd58a1e56cadf48fa730a6aaa01081";

    public static  String Origin="https://bbs.mihoyo.com";
    public static  String Referer="https://bbs.mihoyo.com/ys/article/4523686";
    public static  String xRpcAppVersion="2.5.0";
    public static  String xRpcClientType="4";
    public static  String xRpcDeviceId="442b16a69adf0edf17f738f05239acdc";



    public APItest() {
        requestHeaders.add("Cookie", Cookie);
        requestHeaders.add("DS", DS2);
        requestHeaders.add("Origin", Origin);
        requestHeaders.add("Referer", Referer);
        requestHeaders.add("x-rpc-app_version", xRpcAppVersion);
        requestHeaders.add("x-rpc-client_type", xRpcClientType);
        requestHeaders.add("x-rpc-device_id", xRpcDeviceId);
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
    }

    /**
     *
     * @param post_id 帖子的id
     */
    public String 评论(String post_id){
        Map param=new HashMap<>();
        param.put("content","<p>_(恭贺新春)</p>");
        param.put("gids","2");
        param.put("post_id",post_id);
        param.put("structured_content","[{\"insert\":\"_(恭贺新春)\\n\"}]");
        String url="https://bbs-api.mihoyo.com/post/wapi/releaseReply";
        HttpEntity requestEntity = new HttpEntity(param, requestHeaders);
        ResponseEntity<String> stringResponseEntity = rt.postForEntity(url, requestEntity, String.class);
        System.out.println("评论成功"+stringResponseEntity.getBody());
        Map<String, Object> stringObjectMap = JacksonUtil.parseJsonToMap(stringResponseEntity.getBody());
        Map data = (Map) stringObjectMap.get("data");
        String reply_id = (String) data.get("reply_id");
        return reply_id;
    }

    public void 删除评论(String post_id,String reply_id){
        Map param=new HashMap<>();
        param.put("gids","2");
        param.put("post_id",post_id);
        param.put("reply_id",reply_id);
        String url="https://bbs-api.mihoyo.com/post/wapi/deleteReply";
        HttpEntity requestEntity = new HttpEntity(param, requestHeaders);
        ResponseEntity<String> stringResponseEntity = rt.postForEntity(url, requestEntity, String.class);
        System.out.println("删除成功"+stringResponseEntity.getBody());
    }



    public static void main(String[] args) {
        APItest apItest = new APItest();

        while (true){
            try {
                String 深渊post_id="4523686";
                String 评论id = apItest.评论(深渊post_id);
                try {
                    Thread.sleep(25000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                apItest.删除评论(深渊post_id,评论id);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String 魈教程post_id="4157785";
                String 魈评论id = apItest.评论(魈教程post_id);
                try {
                    Thread.sleep(25000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                apItest.删除评论(魈教程post_id,魈评论id);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } catch (Exception e) {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException ex) {
                    e.printStackTrace();
                }
            }
        }




    }
}
