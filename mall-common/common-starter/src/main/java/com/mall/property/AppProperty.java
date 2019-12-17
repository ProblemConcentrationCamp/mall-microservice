package com.mall.property;

import com.mall.property.nest.SecurityConfigurationProperty;
import com.mall.property.nest.TransactionProperty;
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
    private TransactionProperty transaction;

    @NestedConfigurationProperty
    private SecurityConfigurationProperty security;

    public TransactionProperty getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionProperty transaction) {
        this.transaction = transaction;
    }

    public SecurityConfigurationProperty getSecurity() {
        return security;
    }

    public void setSecurity(SecurityConfigurationProperty security) {
        this.security = security;
    }
}
