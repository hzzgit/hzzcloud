package com.hzz.hzzcloud.config.refreshValue;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/3 9:46
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RefreshValueBeansMgr {
    private static final Logger log = LoggerFactory.getLogger(RefreshValueBeansMgr.class);
    private List<RefreshValueBeanItem> beanList = new ArrayList();
    private Map<String, List<RefreshValueBeanItem.RefreshValueFieldInfo>> prepNameMap = new ConcurrentHashMap();

    public RefreshValueBeansMgr() {
    }

    void init(ApplicationContext applicationContext) {
        this.findAutoRefreshBeans(applicationContext);
    }

    private void findAutoRefreshBeans(ApplicationContext applicationContext) {
        String[] beanNames = applicationContext.getBeanNamesForAnnotation(RefreshValue.class);
        String[] var3 = beanNames;
        int var4 = beanNames.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String bn = var3[var5];
            Object bean = applicationContext.getBean(bn);
            if (bean != null) {
                RefreshValueBeanItem item = new RefreshValueBeanItem(bn, bean);
                if (item.fieldSize() > 0) {
                    this.beanList.add(item);
                    item.getRefreshValueFieldList().forEach((f) -> {
                        List<RefreshValueBeanItem.RefreshValueFieldInfo> list = (List)this.prepNameMap.get(f.prepName);
                        if (list == null) {
                            list = new ArrayList();
                            this.prepNameMap.put(f.prepName, list);
                        }

                        ((List)list).add(f);
                    });
                }
            }
        }

        log.debug("已配置自动刷新的BeanList：" + this.beanList);
    }

    boolean containsPrepName(String prepName) {
        return this.prepNameMap.containsKey(prepName);
    }

    void firePreopertyChangeEvent(PreopertyChangeEvent event) {
        String prepName = event.getPrepName();
        List<RefreshValueBeanItem.RefreshValueFieldInfo> flist = (List)this.prepNameMap.get(prepName);
        if (flist != null) {
            Iterator var4 = flist.iterator();

            while(var4.hasNext()) {
                RefreshValueBeanItem.RefreshValueFieldInfo fi = (RefreshValueBeanItem.RefreshValueFieldInfo)var4.next();

                try {
                    fi.setValue(event.getNewValue());
                    log.debug("自动更新配置值！event=" + event + "; RefreshValueFieldInfo=" + fi);
                } catch (Exception var7) {
                    log.error("firePreopertyChangeEvent出错！event=" + event + "; RefreshValueFieldInfo=" + fi, var7);
                }
            }
        }

    }
}
