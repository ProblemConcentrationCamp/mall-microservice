package com.mall.config;

import com.mall.property.AppProperty;
import com.mall.property.TransactionConfiguration;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.interceptor.DefaultTransactionAttribute;
import org.springframework.transaction.interceptor.NameMatchTransactionAttributeSource;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * <pre>
 *
 * </pre>
 *
 * @author LCN
 * @date 2019-12-12 23:14
 */

@Aspect
@AutoConfigureAfter(DataSourceTransactionManager.class)
@ConditionalOnProperty(value = "lcn.transaction.enable", havingValue = "true")
public class TransactionAdviceConfiguration {

    @Resource
    private DataSourceTransactionManager transactionManager;

    private int propagationBehavior;

    private int isolationLevel;

    private boolean readOnly;

    private String advisorExpression;

    private final static int[] NO_ALLOW_ISOLATION_LEVEL = {0, 3, 5};

    @Autowired
    public TransactionAdviceConfiguration(AppProperty appProperty) {

        TransactionConfiguration transactionConfig = appProperty.getTransaction();
        if (Objects.isNull(transactionConfig.getAdvisorExpression())) {
            throw new UnsupportedOperationException("the 'lcn.transaction.advisorExpression' must be set !");
        }
        this.propagationBehavior = propagationBehaviorHandler(transactionConfig.getPropagationBehavior());
        this.isolationLevel = isolationLevelHandler(transactionConfig.getIsolationLevel());
        this.readOnly = transactionConfig.isReadOnly();
        this.advisorExpression = transactionConfig.getAdvisorExpression();
    }

    @Bean
    public Advisor txAdviceAdvisor() {
        AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
        pointcut.setExpression(advisorExpression);
        return new DefaultPointcutAdvisor(pointcut, txAdvice());
    }

    private TransactionInterceptor txAdvice() {

        DefaultTransactionAttribute txAttrRequired = new DefaultTransactionAttribute();
        txAttrRequired.setPropagationBehavior(propagationBehavior);
        txAttrRequired.setIsolationLevel(isolationLevel);
        txAttrRequired.setReadOnly(readOnly);
        NameMatchTransactionAttributeSource source = new NameMatchTransactionAttributeSource();
        source.addTransactionalMethod("*", txAttrRequired);
        return new TransactionInterceptor(transactionManager, source);
    }

    /**
     * if the curValue > 6 or curValue < 0, return 0;
     *
     * @param curValue handler value
     * @return
     */
    private int propagationBehaviorHandler(int curValue) {
        if (curValue >= TransactionDefinition.PROPAGATION_REQUIRED &&
            propagationBehavior <= TransactionDefinition.PROPAGATION_NESTED) {
            return curValue;
        }
        return TransactionDefinition.PROPAGATION_REQUIRED;
    }

    /**
     * if the curValue > 8 or curValue < 0, return -1;
     * or the curValue = 0, 3, 5 return - ;
     *
     * @param curValue handler value
     * @return
     */
    private int isolationLevelHandler(int curValue) {
        for (int value : NO_ALLOW_ISOLATION_LEVEL) {
            if (isolationLevel == value) {
                return TransactionDefinition.ISOLATION_DEFAULT;
            }
        }
        if (curValue >= TransactionDefinition.ISOLATION_DEFAULT &&
            propagationBehavior <= TransactionDefinition.ISOLATION_SERIALIZABLE) {
            return curValue;
        }
        return TransactionDefinition.ISOLATION_DEFAULT;
    }
}
