package com.hzz.hzzcloud.test;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/8/31 17:36
 */
public class BeantoMao {
    public static void main(String[] args) throws IllegalAccessException {

        BeanToMapVo partitionTableVo =new BeanToMapVo();
        partitionTableVo.setAlarmsource(new String[]{"tem","part"});
        partitionTableVo.setAlarmType(Arrays.asList("1","2"));
        partitionTableVo.setCo(121);
        partitionTableVo.setName("name");
        partitionTableVo.setStartTime(new Date());
        Map<String, Object> map = new HashMap<String, Object>();
        Class<?> clazz = partitionTableVo.getClass();
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = field.get(partitionTableVo);
            map.put(fieldName, value);
        }
        System.out.println(1);
    }
}
