package com.hzz.hzzcloud.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;


@Service
public class ProjectUrlConfig {

    private static final Logger logger = LoggerFactory.getLogger(ProjectUrlConfig.class);


    private Properties getpro(){
        Properties properties = new Properties();
        String realPath = this.getClass().getClassLoader().getResource("application.properties").getFile();
        try {
            properties.load(new FileInputStream(realPath));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  properties;
    }







}