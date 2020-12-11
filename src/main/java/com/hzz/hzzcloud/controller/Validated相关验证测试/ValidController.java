package com.hzz.hzzcloud.controller.Validated相关验证测试;

import com.hzz.hzzcloud.OpenFeign.vo.JsonMessage;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/12/11 10:32
 */
@RestController
@RequestMapping("/validtest")
public class ValidController {


    @RequestMapping(value = "/test1.action",method = RequestMethod.POST)
    public JsonMessage valid(@RequestBody @Validated TestValidatedVo testValidatedVo){
            return  new JsonMessage(true,testValidatedVo);
    }
}
