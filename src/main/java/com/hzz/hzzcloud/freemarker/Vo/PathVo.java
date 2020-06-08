package com.hzz.hzzcloud.freemarker.Vo;

import lombok.Data;

@Data
public class PathVo {

    private String allpath;
    private String entitypath;
    private String controllerpath;
    private String servicepath;
    private String vopath;
    private String exlVopath;

    public PathVo(String allpath, String entitypath, String controllerpath, String servicepath, String vopath,String exlVopath) {
        this.allpath = allpath;
        this.entitypath = entitypath;
        this.controllerpath = controllerpath;
        this.servicepath = servicepath;
        this.vopath = vopath;
        this.exlVopath = exlVopath;
    }
}
