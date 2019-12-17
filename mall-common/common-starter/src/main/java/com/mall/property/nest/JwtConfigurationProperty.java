package com.mall.property.nest;

/**
 * <pre>
 * system jwt config
 * </pre>
 *
 * @author LCN
 * @date 2019-12-17 14:36
 */
public class JwtConfigurationProperty {

    /**
     * whether need open security
     */
    private boolean enable = false;

    /**
     * the default issuer for token
     */
    private String issuer = "";

    /**
     * the default subject for token
     */
    private String subject = "";

    /**
     * token expire time, default 30 days
     */
    private long tokenExpTime = 30 * 24 * 60 * 60;

    /**
     * the url can't token verity, urls separated by ","
     */
    private String antMatchers = "";

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public long getTokenExpTime() {
        return tokenExpTime;
    }

    public void setTokenExpTime(long tokenExpTime) {
        this.tokenExpTime = tokenExpTime;
    }

    public String getAntMatchers() {
        return antMatchers;
    }

    public void setAntMatchers(String antMatchers) {
        this.antMatchers = antMatchers;
    }
}
