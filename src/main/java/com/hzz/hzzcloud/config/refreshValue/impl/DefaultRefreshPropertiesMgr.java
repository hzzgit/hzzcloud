package com.hzz.hzzcloud.config.refreshValue.impl;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/3 9:47
 */

import com.hzz.hzzcloud.config.refreshValue.RefreshPropertiesMgr;
import com.hzz.hzzcloud.config.refreshValue.RefreshValueBeansMgr;
import com.hzz.hzzcloud.config.refreshValue.ResourceLoaderUtil;
import net.fxft.common.util.BasicUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DefaultRefreshPropertiesMgr extends RefreshPropertiesMgr {
    private static final Logger log = LoggerFactory.getLogger(DefaultRefreshPropertiesMgr.class);

    public DefaultRefreshPropertiesMgr() {
    }

    @Override
    protected void loadProperties(Environment environment, RefreshValueBeansMgr refreshValueBeansMgr) {
        try {
            String nacosAddr = environment.getProperty("nacos.addr");
            String nacosNamespace = environment.getProperty("nacos.namespace");
            String dataIds = environment.getProperty("spring.cloud.nacos.config.shared-dataids");
            String[] activeProfiles;
            int var9;
            String name;
            String p;
            if (BasicUtil.isNotEmpty(nacosAddr) && BasicUtil.isNotEmpty(dataIds)) {
                String[] darr = dataIds.split(",");
                activeProfiles = darr;
                int var8 = darr.length;

                for(var9 = 0; var9 < var8; ++var9) {
                    name = activeProfiles[var9];
                    name = name.trim();
                    if (name.length() > 0) {
                        p = String.format("http://%s/nacos/v1/cs/configs?tenant=%s&dataId=%s&group=DEFAULT_GROUP", nacosAddr, nacosNamespace, name);
                        this.loadResourcesProperties(refreshValueBeansMgr, new Resource[]{ResourceLoaderUtil.getInstance().getUrlResource(p)});
                    }
                }
            }

            List<String> filesList = new ArrayList();
            activeProfiles = environment.getActiveProfiles();
            String[] var19 = activeProfiles;
            var9 = activeProfiles.length;

            int var22;
            for(var22 = 0; var22 < var9; ++var22) {
                p = var19[var22];
                filesList.add("bootstrap-" + p + ".properties");
            }

            filesList.add("bootstrap.properties");
            var19 = activeProfiles;
            var9 = activeProfiles.length;

            for(var22 = 0; var22 < var9; ++var22) {
                p = var19[var22];
                filesList.add("application-" + p + ".properties");
            }

            filesList.add("application.properties");
            Iterator var20 = filesList.iterator();

            while(var20.hasNext()) {
                String fileName = (String)var20.next();
                name = "classpath*:/" + fileName;
                Resource[] resources = ResourceLoaderUtil.getInstance().getResources(name);
                Resource[] var12 = resources;
                int var13 = resources.length;

                for(int var14 = 0; var14 < var13; ++var14) {
                    Resource res = var12[var14];
                    String resurl = res.getURL().toString();
                    if (resurl.startsWith("file:")) {
                        this.loadResourcesProperties(refreshValueBeansMgr, new Resource[]{ResourceLoaderUtil.getInstance().getUrlResource(resurl)});
                    }
                }
            }
        } catch (Exception var17) {
            log.error("DefaultRefreshPropertiesMgr.loadProperties出错！", var17);
        }

    }
}

