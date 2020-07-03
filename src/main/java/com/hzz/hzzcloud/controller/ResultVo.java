package com.hzz.hzzcloud.controller;

import lombok.Data;

/**
 * 接口调用返回结果
 */
@Data
public class ResultVo {
    private  String msg;//返回错误的消息提示
    private Integer status;//0,失败,1成功

    @Override
    public String toString() {
        return "ResultVo{" +
                "msg='" + msg + '\'' +
                ", status=" + status +
                '}';
    }
}
