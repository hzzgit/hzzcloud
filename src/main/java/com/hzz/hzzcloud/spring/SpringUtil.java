package com.hzz.hzzcloud.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;

import java.util.Iterator;
import java.util.Map;

public class SpringUtil {
    private static final Logger log = LoggerFactory.getLogger(SpringUtil.class);

    public SpringUtil() {
    }

    public static void invokeAfterStartedRunner(ApplicationContext ctx) {
        Map<String, AfterStartedRunner> map = ctx.getBeansOfType(AfterStartedRunner.class);
        Iterator var2 = map.values().iterator();

        while(var2.hasNext()) {
            AfterStartedRunner runner = (AfterStartedRunner)var2.next();
            String clsname = runner.getClass().getName();
            log.info("执行AfterStartedRunner类：" + clsname);

            try {
                runner.run();
            } catch (Exception var6) {
                log.error("执行AfterStartedRunner类异常，系统退出！cls=" + clsname, var6);
                System.exit(0);
            }
        }

    }
}
