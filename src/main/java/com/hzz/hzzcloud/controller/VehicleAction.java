package com.hzz.hzzcloud.controller;

import lombok.extern.slf4j.Slf4j;
import net.fxft.ascswebcommon.web.util.JsonMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@Slf4j
@RequestMapping("/test")
public class VehicleAction {


    @Autowired
    protected HttpServletRequest request;

    @ResponseBody
    @RequestMapping("/abc.action")
    public JsonMessage abc() {
        String userName = request.getHeader("userName");
        return  new JsonMessage(true,userName);
    }
}
