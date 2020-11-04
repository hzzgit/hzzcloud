package com.hzz.hzzcloud.config.refreshValue;

/**
 * @author ：hzz
 * @description：TODO
 * @date ：2020/11/3 9:45
 */

import com.hzz.hzzcloud.config.refreshValue.impl.DefaultRefreshPropertiesMgr;
import net.fxft.common.tpool.NamedThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RefreshValueProcesser implements CommandLineRunner, ApplicationContextAware, EnvironmentAware {
    private static final Logger log = LoggerFactory.getLogger(RefreshValueProcesser.class);
    private RefreshValueConfig refreshValueConfig;
    private ApplicationContext applicationContext;
    private Environment environment;
    private RefreshValueBeansMgr beansMgr;
    private RefreshPropertiesMgr propertiesMgr;
    private ScheduledExecutorService ses;

    public RefreshValueProcesser(RefreshValueConfig refreshValueConfig) {
        this.refreshValueConfig = refreshValueConfig;
    }

    @Override
    public void run(String... args) throws Exception {
        if (this.refreshValueConfig.isEnabled()) {
            this.beansMgr = new RefreshValueBeansMgr();
            if (RefreshPropertiesMgr.instance == null) {
                RefreshPropertiesMgr.instance = new DefaultRefreshPropertiesMgr();
            }

            this.propertiesMgr = RefreshPropertiesMgr.instance;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        RefreshValueProcesser.this.beansMgr.init(RefreshValueProcesser.this.applicationContext);
                        RefreshValueProcesser.this.propertiesMgr.init(RefreshValueProcesser.this.environment, RefreshValueProcesser.this.beansMgr);
                    } catch (Throwable var2) {
                        RefreshValueProcesser.log.error("RefreshValueProcesser.init执行出错！", var2);
                    }

                }
            }, "RefreshProcInit");
            t.start();
            this.ses = NamedThreadPoolExecutor.newScheduledThreadPool(1, "RefreshProc");
            this.ses.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    try {
                        RefreshValueProcesser.this.propertiesMgr.checkPropertiesChange((e) -> {
                            RefreshValueProcesser.log.debug("检测到配置值变化！" + e);
                            RefreshValueProcesser.this.beansMgr.firePreopertyChangeEvent(e);
                        });
                    } catch (Throwable var2) {
                        RefreshValueProcesser.log.error("RefreshValueProcesser定时检测更新执行出错！", var2);
                    }

                }
            }, (long)this.refreshValueConfig.getCheckInterval(), (long)this.refreshValueConfig.getCheckInterval(), TimeUnit.SECONDS);
            log.info("成功启动自动刷新配置功能！checkInterval=" + this.refreshValueConfig.getCheckInterval());
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public void destroy() {
        if (this.ses != null) {
            this.ses.shutdownNow();
        }

    }
}
