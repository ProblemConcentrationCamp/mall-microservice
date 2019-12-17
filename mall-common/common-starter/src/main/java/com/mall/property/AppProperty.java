package com.mall.property;

import com.mall.property.nest.JwtConfigurationProperty;
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
    private JwtConfigurationProperty jwt;

    public TransactionProperty getTransaction() {
        return transaction;
    }

    public void setTransaction(TransactionProperty transaction) {
        this.transaction = transaction;
    }

    public JwtConfigurationProperty getJwt() {
        return jwt;
    }

    public void setJwt(JwtConfigurationProperty jwt) {
        this.jwt = jwt;
    }
}
