package com.hzz.hzzcloud.freemarker.util;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RequestUtil {

    /**
     * 获得Request的参数数据 之所以是Public方法，是为了单元测试时，可以直接拿到Map并注入模拟数据
     *
     * @return
     */
    public  static  Map getParams(HttpServletRequest request) {
        Map parameters = request.getParameterMap();
        Map paraMap = new HashMap();
        for (Object key : parameters.keySet()) {
            String strKey = "" + key;
            int index = strKey.indexOf("[]");
            if (index > 0) {
                strKey = strKey.replaceAll("\\[\\]", "");
            }
            String[] values = (String[]) parameters.get(key);
            if (values.length == 1) {
                String strValue = values[0];
                if (strValue != null && strValue.isEmpty() == false)
                    paraMap.put(strKey, strValue);
            } else
                paraMap.put(strKey, values);
        }
        return paraMap;
    }
}
