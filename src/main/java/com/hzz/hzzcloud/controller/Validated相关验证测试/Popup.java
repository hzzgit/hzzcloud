package com.hzz.hzzcloud.controller.Validated相关验证测试;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/11 10:30
 */
@Data
public class Popup {

    @NotNull(message = "姓名不能为空")
    private String name;
}
