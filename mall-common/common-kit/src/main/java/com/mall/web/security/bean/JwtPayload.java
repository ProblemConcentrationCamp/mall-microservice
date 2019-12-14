package com.mall.web.security.bean;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * <pre>
 *
 * </pre>
 *
 * @author LCN
 * @date 2019-12-14 22:17
 */
public class JwtPayload {

    private String iss;

    private String sub;

    private String aud;

    private Date exp;

    private Date nbf;

    private Date iat;

    private String jti;

    /**
     * a default constant for customized param count
     */
    private final static int DEFAULT_CUSTOMIZED_PARAM_COUNT = 8;

    /**
     * customized  param
     */
    private Map<String, Object> customizedParamMap;

    private JwtPayload(JwtPayloadBuilder builder) {
        this.iss = builder.iss;
        this.sub = builder.sub;
        this.aud = builder.aud;
        this.exp = builder.exp;
        this.nbf = builder.nbf;
        this.iat = builder.iat;
        this.jti = builder.jti;
        this.customizedParamMap = builder.customizedParamMap;
    }

    /**
     *
     * create Jwt Builder that is used in generate token
     *
     * @return
     */
    public JWTCreator.Builder getJwtBuilder() {

        JWTCreator.Builder builder = JWT.create();
        Optional.ofNullable(iss).ifPresent(builder::withIssuer);
        Optional.ofNullable(sub).ifPresent(builder::withSubject);
        Optional.ofNullable(aud).ifPresent(builder::withAudience);
        Optional.ofNullable(exp).ifPresent(builder::withExpiresAt);
        Optional.ofNullable(nbf).ifPresent(builder::withNotBefore);
        Optional.ofNullable(iat).ifPresent(builder::withIssuedAt);
        Optional.ofNullable(jti).ifPresent(builder::withJWTId);
        Optional.ofNullable(customizedParamMap).ifPresent(map -> {
            map.forEach((k, v) -> {
                if (v instanceof Integer) {
                    builder.withClaim(k, (Integer) v);
                } else if (v instanceof Boolean) {
                    builder.withClaim(k, (Boolean)v);
                } else if (v instanceof Double) {
                    builder.withClaim(k, (Double)v);
                } else if (v instanceof Long) {
                    builder.withClaim(k,(Long)v);
                } else if (v instanceof String) {
                    builder.withClaim(k, (String)v);
                } else if (v instanceof Date) {
                    builder.withClaim(k, (Date)v);
                }
            });
        });
        return builder;
    }

    /**
     * jwt payload builder
     */
    public static class JwtPayloadBuilder {

        private String iss;

        private String sub;

        private String aud;

        private Date exp;

        private Date nbf;

        private Date iat;

        private String jti;

        /**
         * 自定义参数
         */
        private Map<String, Object> customizedParamMap;

        public JwtPayloadBuilder() {
        }

        public JwtPayloadBuilder setIss(String iss) {
            this.iss = iss;
            return this;
        }

        public JwtPayloadBuilder setSub(String sub) {
            this.sub = sub;
            return this;
        }

        public JwtPayloadBuilder setAud(String aud) {
            this.aud = aud;
            return this;
        }

        public JwtPayloadBuilder setExp(Date exp) {
            this.exp = exp;
            return this;
        }

        public JwtPayloadBuilder setNbf(Date nbf) {
            this.nbf = nbf;
            return this;
        }

        public JwtPayloadBuilder setIat(Date iat) {
            this.iat = iat;
            return this;
        }

        public JwtPayloadBuilder setJti(String jti) {
            this.jti = jti;
            return this;
        }

        public JwtPayloadBuilder setCustomParam(String key, Object value) {
            // can not support array current
            if (value.getClass().isArray()) {
                throw new UnsupportedOperationException("current unsupport Array");
            }
            if (customizedParamMap == null) {
                customizedParamMap = new HashMap<>(DEFAULT_CUSTOMIZED_PARAM_COUNT);
            }
            customizedParamMap.put(key, value);
            return this;
        }

        public JwtPayload builder() {
            return new JwtPayload(this);
        }
    }

}
