package com.mall.web.security.service;

import com.auth0.jwt.algorithms.Algorithm;

/**
 * <pre>
 *
 * </pre>
 *
 * @author LCN
 * @date 2019-12-14 22:15
 */
public class JwtService {

    /**
     * encryption algorithm
     */
    private Algorithm algorithm;

    public JwtService(Algorithm algorithm) {
        this.algorithm = algorithm;
    }


}
