package com.mall.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * <pre>
 *
 * </pre>
 *
 * @author LCN
 * @date 2019-12-10 22:13
 */
@ConfigurationProperties(prefix = "app")
public class AppProperty {

    @NestedConfigurationProperty
    private TransactionConfiguration transaction;

    public TransactionConfiguration getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionConfiguration transaction) {
        this.transaction = transaction;
    }
}
