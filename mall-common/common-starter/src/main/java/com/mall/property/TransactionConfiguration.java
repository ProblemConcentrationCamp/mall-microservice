package com.mall.property;

import org.springframework.transaction.TransactionDefinition;

/**
 * <pre>
 * system transaction config
 * </pre>
 *
 * @author LCN
 * @date 2019-12-10 22:16
 */
public class TransactionConfiguration {

    /**
     * whether need open transaction
     */
    private boolean enable = false;

    /**
     * transaction propagation behavior
     */
    private int propagationBehavior = TransactionDefinition.PROPAGATION_REQUIRED;

    /**
     * transaction isolation level
     */
    private int isolationLevel = TransactionDefinition.ISOLATION_DEFAULT;

    /**
     * read only transaction
     */
    private boolean readOnly = false;

    /**
     * default advisor expression
     */
    private String advisorExpression;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public int getPropagationBehavior() {
        return propagationBehavior;
    }

    public void setPropagationBehavior(int propagationBehavior) {
        this.propagationBehavior = propagationBehavior;
    }

    public int getIsolationLevel() {
        return isolationLevel;
    }

    public void setIsolationLevel(int isolationLevel) {
        this.isolationLevel = isolationLevel;
    }

    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public String getAdvisorExpression() {
        return advisorExpression;
    }

    public void setAdvisorExpression(String advisorExpression) {
        this.advisorExpression = advisorExpression;
    }
}
