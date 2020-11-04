package com.hzz.hzzcloud.config.refreshValue;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/3 9:47
 */

import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

public abstract class RefreshPropertiesMgr {
    private static final Logger log = LoggerFactory.getLogger(RefreshPropertiesMgr.class);
    static RefreshPropertiesMgr instance = null;
    protected Map<String, RefreshPropertiesMgr.PrepValue> prepValueMap = new ConcurrentHashMap();
    protected List<RefreshPropertiesMgr.PrepFile> prepFileList = new ArrayList();

    public RefreshPropertiesMgr() {
    }

    public static void setPropertiesMgr(RefreshPropertiesMgr propertiesMgr) {
        instance = propertiesMgr;
    }

    void init(Environment environment, RefreshValueBeansMgr refreshValueBeansMgr) {
        this.loadProperties(environment, refreshValueBeansMgr);
        log.debug("已添加自动刷新的配置项：" + this.prepValueMap.values());
    }

    protected abstract void loadProperties(Environment environment, RefreshValueBeansMgr refreshValueBeansMgr);

    protected void loadResourcesProperties(RefreshValueBeansMgr refreshValueBeansMgr, Resource... resources) {
        Resource[] var3 = resources;
        int var4 = resources.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            Resource r = var3[var5];
            String rname = null;

            try {
                rname = r.getURL().toString();
                if (rname.startsWith("http://") && rname.contains("dataId")) {
                    int i = rname.indexOf("dataId");
                    int j = rname.indexOf("&", i);
                    rname = "nacos://" + rname.substring(i, j);
                }

                Properties properties = ResourceLoaderUtil.getInstance().loadProperties(r);
                if (properties == null) {
                    log.debug("自动刷新功能尝试加载配置文件，但是文件不存在! resourcesName=" + rname);
                } else {
                    log.debug("自动刷新功能加载配置文件! resourcesName=" + rname);
                    RefreshPropertiesMgr.PrepFile pf = new RefreshPropertiesMgr.PrepFile();
                    pf.resName = rname;
                    pf.resource = r;
                    pf.md5 = DigestUtils.md5Hex(properties.toString());
                    pf.properties = properties;
                    AtomicBoolean added = new AtomicBoolean(false);
                    properties.forEach((k, v) -> {
                        String key = k.toString();
                        if (refreshValueBeansMgr.containsPrepName(key)) {
                            if (!this.prepValueMap.containsKey(key)) {
                                RefreshPropertiesMgr.PrepValue pv = new RefreshPropertiesMgr.PrepValue();
                                pv.name = key;
                                pv.value = v.toString();
                                pv.prepFile = pf;
                                this.prepValueMap.put(pv.name, pv);
                                added.getAndSet(true);
                            }

                        }
                    });
                    if (added.get()) {
                        this.prepFileList.add(pf);
                    }
                }
            } catch (Exception var11) {
                log.error("loadResourcesProperties出错！Resource=" + rname, var11);
            }
        }

    }

    void checkPropertiesChange(Consumer<PreopertyChangeEvent> valueChangedFun) {
        Iterator var2 = this.prepFileList.iterator();

        while(var2.hasNext()) {
            RefreshPropertiesMgr.PrepFile prepFile = (RefreshPropertiesMgr.PrepFile)var2.next();

            try {
                Properties newProperties = ResourceLoaderUtil.getInstance().loadProperties(prepFile.resource);
                String newMd5 = DigestUtils.md5Hex(newProperties.toString());
                if (!newMd5.equals(prepFile.md5)) {
                    Properties oldProperties = prepFile.properties;
                    prepFile.properties = newProperties;
                    prepFile.md5 = newMd5;
                    Iterator var7 = newProperties.stringPropertyNames().iterator();

                    while(var7.hasNext()) {
                        String name = (String)var7.next();
                        String value = newProperties.getProperty(name);
                        String oldv = oldProperties.getProperty(name);
                        if (value != null && oldv != null && !oldv.equals(value)) {
                            PreopertyChangeEvent en = new PreopertyChangeEvent(prepFile.resName, name, oldv, value);
                            valueChangedFun.accept(en);
                        }
                    }
                }
            } catch (Exception var12) {
                log.error("检测配置文件变化出错！resName=" + prepFile.resName, var12);
            }
        }

    }

    protected class PrepFile {
        String resName;
        Resource resource;
        Properties properties;
        String md5;

        protected PrepFile() {
        }
    }

    protected class PrepValue {
        String name;
        String value;
        RefreshPropertiesMgr.PrepFile prepFile;

        protected PrepValue() {
        }

        public String toString() {
            StringBuilder sb = new StringBuilder("{");
            sb.append("name=").append(this.name);
            sb.append(", prepFile=").append(this.prepFile.resName);
            sb.append('}');
            return sb.toString();
        }
    }
}

