package com.mall.config;

import com.mall.property.AppProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import javax.annotation.Resource;

/**
 * <pre>
 *
 * </pre>
 *
 * @author LCN
 * @date 2019-12-10 22:18
 */
@EnableConfigurationProperties(AppProperty.class)
public class AppAutoConfiguration {

    @Resource
    private AppProperty appProperty;
}
