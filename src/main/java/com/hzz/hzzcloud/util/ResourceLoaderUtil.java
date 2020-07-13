package com.hzz.hzzcloud.util;


import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.Properties;

public class ResourceLoaderUtil {
    private static ResourceLoaderUtil rlutil = new ResourceLoaderUtil();
    private DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
    private PathMatchingResourcePatternResolver patternLoader;

    private ResourceLoaderUtil() {
        this.patternLoader = new PathMatchingResourcePatternResolver(this.resourceLoader);
    }

    public static ResourceLoaderUtil getInstance() {
        return rlutil;
    }

    public Resource getResource(String location) {
        return this.patternLoader.getResource(location);
    }

    public Resource[] getResources(String locationPattern) throws IOException {
        return this.patternLoader.getResources(locationPattern);
    }

    public Properties loadProperties(Resource resource) throws IOException {
        if (resource != null && resource.exists()) {
            Properties prep = PropertiesLoaderUtils.loadProperties(new EncodedResource(resource, "UTF-8"));
            return prep;
        } else {
            return null;
        }
    }

    public Properties loadSiblingProperties(Resource resource, String srcPropertiesName, String targetPropertiesName) throws IOException {
        String versionUrl = resource.getURL().toString().replace(srcPropertiesName, targetPropertiesName);
        Resource versionRes = getInstance().getResource(versionUrl);
        if (versionRes.exists()) {
            Properties versionPrep = PropertiesLoaderUtils.loadProperties(new EncodedResource(versionRes, "UTF-8"));
            return versionPrep;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        try {
            Resource[] rarr = ResourceLoaderUtil.getInstance().getResources("classpath*:/module.properties");
            System.out.println(1);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
