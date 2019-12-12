package com.mall.listener;

import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * <pre>
 * make the " sys:log.path" can be used in the log file
 * and the value of "sys:log.path" is the logging.file.path in the application configuration file
 * </pre>
 *
 * @author: LCN
 * @date: 2019-12-11 09:26
 */
public class LoggingListener implements ApplicationListener, Ordered {

    /**
     * the key will be used in the log file, the "sys" must be add the front of the this key
     */
    private final static String LOG_PATH = "log.path";

    /**
     * the key in the application configuration file
     */
    private final static String SPRING_LOG_PATH_PROP = "logging.file.path";

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof ApplicationEnvironmentPreparedEvent) {
            ConfigurableEnvironment environment = ((ApplicationEnvironmentPreparedEvent) applicationEvent).getEnvironment();
            String filePath = environment.getProperty(SPRING_LOG_PATH_PROP);
            if (filePath != null && !filePath.isBlank()) {
                // set the property into system
                System.setProperty(LOG_PATH, filePath);
            }
        }
    }

    @Override
    public int getOrder() {
        // must implementation before LoggingApplicationListener
        return LoggingApplicationListener.DEFAULT_ORDER - 1;
    }
}
