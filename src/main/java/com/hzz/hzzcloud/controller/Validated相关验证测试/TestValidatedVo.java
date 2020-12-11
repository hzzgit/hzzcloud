package com.hzz.hzzcloud.controller.Validated相关验证测试;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/11 10:29
 */
@Data
public class TestValidatedVo {



    @NotNull(message = "车牌号不能为空")
    private String plateNo;
    @NotNull(message = "simNo号不能为空")
    private String simNo;

    @Min(value = 1,message = "取值只能1或者2")
    @Max(value = 2,message = "取值只能1或者2")
    private Integer sex;
    @Valid
    @NotNull(message = "集合不为空")
    @Size(min = 1,message = "集合起码有一个属性")
    private List<Popup> popupList=new ArrayList<>();


    @Email(message = "邮箱格式不正确")
    private String email;
}
