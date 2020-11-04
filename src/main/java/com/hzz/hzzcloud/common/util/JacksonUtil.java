package com.hzz.hzzcloud.common.util;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/3 9:53
 */

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import net.fxft.cloud.http.NameSet;
import net.fxft.common.el.MapConvert;
import net.fxft.common.el.SpEL;
import net.fxft.common.function.ThrowableConsumer;
import net.fxft.common.log.AttrLog;
import net.fxft.common.util.JacksonUtilException;
import net.fxft.common.util.ThreadSafeDateFormat;

import java.io.IOException;
import java.io.StringWriter;
import java.util.LinkedHashMap;
import java.util.Map;

public class JacksonUtil {
    private static ObjectMapper defaultObjectMapper = createDefaultObjectMapper();
    private static ObjectMapper defaultSnakeObjectMapper = objectMapperBuilder().configDefaultDateFormat().configSnakeCaseObjectMapper().build();
    private static ObjectMapper defaultNonNullObjectMapper = objectMapperBuilder().configDefaultDateFormat().configSerializeNonNull().build();

    public JacksonUtil() {
    }

    public static ObjectMapperBuilder objectMapperBuilder() {
        return new ObjectMapperBuilder();
    }

    public static ObjectMapper createDefaultObjectMapper() {
        return objectMapperBuilder().configDefaultDateFormat().build();
    }

    public static ObjectMapper getDefaultObjectMapper() {
        return defaultObjectMapper;
    }

    public static ObjectMapper getDefaultSnakeObjectMapper() {
        return defaultSnakeObjectMapper;
    }

    public static ObjectMapper getDefaultNonNullObjectMapper() {
        return defaultNonNullObjectMapper;
    }

    public static String toJsonString(Object obj) {
        return toJsonString(defaultObjectMapper, obj);
    }

    public static String toJsonStringSnake(Object obj) {
        return toJsonString(defaultSnakeObjectMapper, obj);
    }

    public static String toJsonString(ObjectMapper ObjMapper, Object obj) {
        try {
            StringWriter sw = new StringWriter();
            ObjMapper.writeValue(sw, obj);
            return sw.toString();
        } catch (Exception var3) {
            throw new JacksonUtilException("JacksonUtil.toJsonString出错！", var3);
        }
    }

    public static String useJsonGenerator(ThrowableConsumer<JsonGenerator> consumer) {
        return useJsonGenerator(defaultObjectMapper, consumer);
    }

    public static String useJsonGeneratorSnake(ThrowableConsumer<JsonGenerator> consumer) {
        return useJsonGenerator(defaultSnakeObjectMapper, consumer);
    }

    public static String useJsonGenerator(ObjectMapper ObjMapper, ThrowableConsumer<JsonGenerator> consumer) {
        JsonFactory jsonFactory = ObjMapper.getFactory();
        StringWriter sw = new StringWriter();

        try {
            JsonGenerator jsonGenerator = jsonFactory.createGenerator(sw);

            try {
                consumer.accept(jsonGenerator);
            } finally {
                jsonGenerator.flush();
                jsonGenerator.close();
            }

            return sw.toString();
        } catch (Exception var9) {
            throw new JacksonUtilException("JacksonUtil.useJsonGenerator出错！", var9);
        }
    }

    public static void useJsonFactory(ThrowableConsumer<JsonFactory> consumer) {
        useJsonFactory(defaultObjectMapper, consumer);
    }

    public static void useJsonFactorySnake(ThrowableConsumer<JsonFactory> consumer) {
        useJsonFactory(defaultSnakeObjectMapper, consumer);
    }

    public static void useJsonFactory(ObjectMapper ObjMapper, ThrowableConsumer<JsonFactory> consumer) {
        JsonFactory jsonFactory = ObjMapper.getFactory();

        try {
            consumer.accept(jsonFactory);
        } catch (Exception var4) {
            throw new JacksonUtilException("JacksonUtil.useJsonFactory出错！", var4);
        }
    }

    public static void useJsonParser(String jsonstr, ThrowableConsumer<JsonParser> consumer) {
        useJsonParser(defaultObjectMapper, jsonstr, consumer);
    }

    public static void useJsonParserSnake(String jsonstr, ThrowableConsumer<JsonParser> consumer) {
        useJsonParser(defaultSnakeObjectMapper, jsonstr, consumer);
    }

    public static void useJsonParser(ObjectMapper ObjMapper, String jsonstr, ThrowableConsumer<JsonParser> consumer) {
        JsonFactory jsonFactory = ObjMapper.getFactory();

        try {
            JsonParser jp = jsonFactory.createParser(jsonstr);

            try {
                consumer.accept(jp);
            } finally {
                jp.close();
            }

        } catch (Exception var9) {
            throw new JacksonUtilException("JacksonUtil.useJsonParser出错！", var9);
        }
    }

    public static Map<String, Object> parseJsonToMap(String jsonStr) {
        return parseJsonToMap(defaultObjectMapper, jsonStr);
    }

    public static Map<String, Object> parseJsonToMapSnake(String jsonStr) {
        return parseJsonToMap(defaultSnakeObjectMapper, jsonStr);
    }

    public static Map<String, Object> parseJsonToMap(ObjectMapper ObjMapper, String jsonStr) {
        Object obj = parseJsonString(ObjMapper, jsonStr, Map.class);
        return (Map)obj;
    }

    public static SpEL parseJsonToSpEl(String jsonStr) {
        return parseJsonToSpEl(defaultObjectMapper, jsonStr);
    }

    public static SpEL parseJsonToSpElSnake(String jsonStr) {
        return parseJsonToSpEl(defaultSnakeObjectMapper, jsonStr);
    }

    public static SpEL parseJsonToSpEl(ObjectMapper ObjMapper, String jsonStr) {
        Object obj = parseJsonString(ObjMapper, jsonStr, Object.class);
        return SpEL.build(obj);
    }

    public static <T> T parseJsonString(String jsonStr, Class<T> objClass) {
        return parseJsonString(defaultObjectMapper, jsonStr, objClass);
    }

    public static <T> T parseJsonStringSnake(String jsonStr, Class<T> objClass) {
        return parseJsonString(defaultSnakeObjectMapper, jsonStr, objClass);
    }

    public static <T> T parseJsonString(ObjectMapper ObjMapper, String jsonStr, Class<T> objClass) {
        try {
            return ObjMapper.readValue(jsonStr, objClass);
        } catch (Exception var4) {
            throw new JacksonUtilException("JacksonUtil.parseJsonString出错！", var4);
        }
    }

    public static <T> T parseJsonString(String jsonStr, TypeReference<T> typeRef) {
        return parseJsonString(defaultObjectMapper, jsonStr, typeRef);
    }

    public static <T> T parseJsonStringSnake(String jsonStr, TypeReference<T> typeRef) {
        return parseJsonString(defaultSnakeObjectMapper, jsonStr, typeRef);
    }

    public static <T> T parseJsonString(ObjectMapper ObjMapper, String jsonStr, TypeReference<T> typeRef) {
        try {
            return ObjMapper.readValue(jsonStr, typeRef);
        } catch (IOException var4) {
            throw new JacksonUtilException("JacksonUtil.parseJsonString出错！", var4);
        }
    }

    public static <T> T parseJsonOnlyFirstLevel2(String jsonStr, Class<T> objClass) {
        Map<String, String> remap = parseJsonOnlyFirstLevel(jsonStr);
        return MapConvert.mapToObject(remap, objClass);
    }

    public static Map<String, String> parseJsonOnlyFirstLevel(String jsonStr) {
        return parseJsonOnlyFirstLevel(jsonStr, (NameSet)null);
    }

    public static String parseJsonOnlyOneKey(String jsonStr, String key) {
        String[] keyarr = key.split("\\.");
        String pstr = jsonStr;

        for(int i = 0; i < keyarr.length; ++i) {
            if (pstr == null) {
                return null;
            }

            Map<String, String> fmap = parseJsonOnlyFirstLevel(pstr, NameSet.of(new String[]{keyarr[i]}));
            pstr = (String)fmap.get(keyarr[i]);
        }

        return pstr;
    }

    public static Map<String, String> parseJsonOnlyFirstLevel(String jsonStr, NameSet fieldNameFilter) {
        Map<String, String> remap = new LinkedHashMap();
        if (jsonStr != null && !jsonStr.isEmpty()) {
            try {
                useJsonParser(jsonStr, (jp) -> {
                    JsonToken jsonToken = jp.nextToken();
                    if (jsonToken != null) {
                        boolean arrayMode = false;
                        if (jsonToken == JsonToken.START_ARRAY) {
                            arrayMode = true;
                        }

                        jsonToken = jp.nextToken();
                        int beginIndex = -1;
                        int startFlagCount = 0;
                        String fieldName = null;

                        for(int var9 = 0; jsonToken != null; jsonToken = jp.nextToken()) {
                            if (fieldName == null && jsonToken != JsonToken.END_ARRAY && jsonToken != JsonToken.END_OBJECT) {
                                if (arrayMode) {
                                    fieldName = String.valueOf(var9++);
                                } else {
                                    if (jsonToken != JsonToken.FIELD_NAME) {
                                        AttrLog alog = AttrLog.get("parseJsonOnlyFirstLevel出错！没有取到预期的fieldName！").log("currentIndex", jp.getCurrentLocation().getCharOffset()).log("currentToken", jp.getCurrentToken()).log("jsonStr", jsonStr);
                                        throw new JacksonUtilException(alog.toString(),null);
                                    }

                                    fieldName = jp.getText();
                                    jsonToken = jp.nextToken();
                                }

                                if (jsonToken != JsonToken.START_ARRAY && jsonToken != JsonToken.START_OBJECT) {
                                    String vx = jp.getText();
                                    if (fieldNameFilter == null || fieldNameFilter.contains(fieldName)) {
                                        remap.put(fieldName, vx);
                                    }

                                    fieldName = null;
                                    startFlagCount = 0;
                                    beginIndex = -1;
                                } else {
                                    ++startFlagCount;
                                    beginIndex = (int)jp.getCurrentLocation().getCharOffset();
                                }
                            } else if (jsonToken != JsonToken.START_ARRAY && jsonToken != JsonToken.START_OBJECT) {
                                if ((jsonToken == JsonToken.END_ARRAY || jsonToken == JsonToken.END_OBJECT) && fieldName != null) {
                                    --startFlagCount;
                                    if (startFlagCount == 0) {
                                        if (fieldNameFilter == null || fieldNameFilter.contains(fieldName)) {
                                            int endIndex = (int)jp.getCurrentLocation().getCharOffset();
                                            String v = jsonStr.substring(beginIndex - 1, endIndex);
                                            remap.put(fieldName, v);
                                        }

                                        fieldName = null;
                                        startFlagCount = 0;
                                        beginIndex = -1;
                                    }
                                }
                            } else if (fieldName != null) {
                                ++startFlagCount;
                            }
                        }

                    }
                });
                return remap;
            } catch (Exception var4) {
                throw new JacksonUtilException("JacksonUtil.parseJsonOnlyFirstLevel出错！", var4);
            }
        } else {
            return remap;
        }
    }

    public static class ObjectMapperBuilder {
        private ObjectMapper om;

        private ObjectMapperBuilder() {
            this.om = null;
            this.om = new ObjectMapper();
            this.om.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            this.om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            this.om.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            this.om.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
            this.om.configure(Feature.ALLOW_COMMENTS, true);
            this.om.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
            this.om.configure(Feature.ALLOW_SINGLE_QUOTES, true);
            this.om.configure(com.fasterxml.jackson.core.JsonGenerator.Feature.ESCAPE_NON_ASCII, false);
        }

        public ObjectMapperBuilder configDefaultDateFormat() {
            this.om.setDateFormat(new ThreadSafeDateFormat("yyyy-MM-dd HH:mm:ss"));
            return this;
        }

        public ObjectMapperBuilder configDateFormat(String pattern) {
            this.om.setDateFormat(new ThreadSafeDateFormat(pattern));
            return this;
        }

        public ObjectMapperBuilder configSnakeCaseObjectMapper() {
            this.om.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
            return this;
        }

        public ObjectMapperBuilder configSerializeNonNull() {
            this.om.setSerializationInclusion(Include.NON_NULL);
            return this;
        }

        public ObjectMapper build() {
            return this.om;
        }
    }
}

