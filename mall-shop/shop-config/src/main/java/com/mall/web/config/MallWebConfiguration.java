package com.mall.web.config;

import com.mall.web.undertow.UndertowConfig;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 *  the web config in the mall module
 * </pre>
 *
 * @author LCN
 * @date 2019-12-15 11:29
 */
@Configuration
public class MallWebConfiguration {

   // @Bean
    public UndertowConfig getUndertowConfig() {
        // TODO
        return new UndertowConfig();
    }
}
