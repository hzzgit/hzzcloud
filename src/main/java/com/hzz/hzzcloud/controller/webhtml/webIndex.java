package com.hzz.hzzcloud.controller.webhtml;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author ：hzz
 * @description：web跳转
 * @date ：2020/11/18 14:51
 */
@Controller
@RequestMapping("/index")
public class webIndex {

    @RequestMapping("/index")
    public String index(){
        return  "index";
    }

    @RequestMapping("/ce")
    public String ce(){
    return  "ces";
    }
}
