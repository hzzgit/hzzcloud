package com.hzz.hzzcloud.config;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.text.SimpleDateFormat;

/**
 * @description: 转换null对象为空字符串
 */
public class JsonObjectMapper extends ObjectMapper {
    private static final long serialVersionUID = 1L;

    public JsonObjectMapper() {
        super();
        // 空值处理为空串
//        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
//            @Override
//            public void serialize(Object value, JsonGenerator jg, SerializerProvider sp) throws IOException, JsonProcessingException {
//                jg.writeString("");
//            }
//        });
        this.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }
}