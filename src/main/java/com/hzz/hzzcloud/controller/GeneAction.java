package com.hzz.hzzcloud.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class GeneAction {
    @Autowired
    private HttpServletRequest request;

    /**
     * 获得Request的参数数据 之所以是Public方法，是为了单元测试时，可以直接拿到Map并注入模拟数据
     *
     * @return
     */
    public Map getParams() {
        Map parameters = request.getParameterMap();
        Map paraMap = new HashMap();
        String rowdata = "";
        if (parameters.size() > 0) {
            for (Object key : parameters.keySet()) {
                String strKey = "" + key;
                int index = strKey.indexOf("[]");
                if (index > 0) {
                    strKey = strKey.replaceAll("\\[\\]", "");
                }
                String[] values = (String[]) parameters.get(key);
                if (values.length == 1) {
                    String strValue = values[0];
                    if (strValue != null && strValue.isEmpty() == false) {
                        paraMap.put(strKey, strValue);
                    }
                } else {
                    paraMap.put(strKey, values);
                }
            }
        } else {
            BufferedReader br = null;

            try {
                br = request.getReader();
                String line = br.readLine();
                if (line == null) {

                } else {
                    StringBuilder ret = new StringBuilder();
                    ret.append(line);

                    while ((line = br.readLine()) != null) {
                        ret.append('\n').append(line);
                    }

                    rowdata = ret.toString();
                }
                paraMap = (Map) JSON.parse(rowdata);
            } catch (IOException var4) {
                throw new RuntimeException(var4);
            }
        }
        return paraMap;
    }
}
