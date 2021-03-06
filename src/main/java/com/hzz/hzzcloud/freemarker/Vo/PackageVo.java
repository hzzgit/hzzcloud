package com.hzz.hzzcloud.freemarker.Vo;

import lombok.Data;

@Data
public class PackageVo {
    private String entity;
    private String controller;
    private String service;
    private String vo;//vo路径
    private String exlvo;//exlvo路径
    private String otherpackname;//自定义模板的package路径

    public PackageVo(String entity, String controller, String service, String vo,String exlvo) {
        this.entity = entity;
        this.controller = controller;
        this.service = service;
        this.vo = vo;
        this.exlvo = exlvo;
    }
}
