package com.hzz.hzzcloud.freemarker.emun;

public enum WebEnum {
    entity("entity"),
    controller("controller"),
    service("service"),
    mapper("mapper"),
    exlvo("exlvo"),
    vo("protocol");

    WebEnum(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public static void main(String[] args) {
        System.out.println(WebEnum.entity.equals("entity"));
    }
}
