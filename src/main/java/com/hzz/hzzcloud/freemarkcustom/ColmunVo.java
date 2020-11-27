package com.hzz.hzzcloud.freemarkcustom;

import lombok.Data;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/27 11:58
 */
@Data
public class ColmunVo {

    private String code ;
    private String message ;
    private String result ;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
