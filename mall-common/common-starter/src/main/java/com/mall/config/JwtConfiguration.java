package com.mall.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * <pre>
 *
 * </pre>
 *
 * @author LCN
 * @date 2019-12-17 15:10
 */

@ConditionalOnProperty(value = "app.jwt.enable", havingValue = "true")
public class JwtConfiguration {

}
