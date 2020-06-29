package com.hzz.hzzcloud.freemarker.emun;

public enum TemplateEnum {
    TEMPLATE_PATH1("带common的模板"),
    TEMPLATE_PATHNOCOMMON("不带common的模板");
    //TEMPLATE_TREE("机构车辆授权的模板,加列表");

    TemplateEnum(String value) {
        this.value = value;
    }

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
