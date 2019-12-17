package com.mall.config;

import com.auth0.jwt.algorithms.Algorithm;
import com.mall.property.AppProperty;
import com.mall.property.nest.SecurityConfigurationProperty;
import com.mall.web.security.auth.JwtAuthenticationEntryPoint;
import com.mall.web.security.filter.JwtAuthorizationTokenFilter;
import com.mall.web.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

import javax.annotation.Resource;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * <pre>
 * security by token
 * </pre>
 *
 * @author LCN
 * @date 2019-12-17 15:10
 */

@ConditionalOnBean(UserDetailsService.class)
@ConditionalOnProperty(value = "app.security.enable", havingValue = "true")
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    /**
     * security config
     */
    private SecurityConfigurationProperty securityProperty;

    @Autowired
    public SecurityConfiguration(AppProperty appProperty) {
        SecurityConfigurationProperty securityProperty = appProperty.getSecurity();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //  With this configured, the encrypted password in the database
        //  is automatically compared to the password entered by the user
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoderBean());
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        // base jwt, csrf no need
        httpSecurity.csrf().disable()
            // 认证失败的处理器
            // the handler to solve token authenticated fail
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
            // base token , session is not need
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            // request of preflight can access straight
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
            // the url under matchers can access the service login without cross the filter
            .antMatchers(
                "/open/**"
            ).permitAll()
            // 其他的请求都需要验证
            // other request must authenticated
            .anyRequest().authenticated();

        // before access the service logic, must can cross the filter
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(),
            UsernamePasswordAuthenticationFilter.class);

        // Disable Caching
        httpSecurity.headers().cacheControl();
    }



    /**
     * password encoder
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoderBean() {
        return new BCryptPasswordEncoder();
    }

    /**
     * verify manager
     *
     * @return
     * @throws Exception
     */
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return authenticationManagerBean();
    }

    /**
     * authentication fail handler
     *
     * @return
     */
    @Bean
    public AuthenticationEntryPoint createAuthenticationEntryPoint() {
        return new JwtAuthenticationEntryPoint();
    }

    /**
     * url TokenFilter
     *
     * @return
     * @throws Exception
     */
    @Bean
    public JwtAuthorizationTokenFilter authenticationTokenFilterBean() {
        return new JwtAuthorizationTokenFilter();
    }

    @Bean
    public JwtService createJwtService() throws Exception{
        Algorithm algorithm;
        switch (securityProperty.getTokenAlgorithmWay()) {
            case 1:
            case 2:
                algorithm = createJwtServiceByString(securityProperty.getPublicKey(), securityProperty.getPrivateKey());
                break;
            case 3:
                algorithm = createJwtServiceByFile(securityProperty.getPublicKey(), securityProperty.getPrivateKey());
                break;
            default:
                throw new UnsupportedOperationException("this way can't create JwtService");
        }
        JwtService service = new JwtService();
        service.setAlgorithm(algorithm);
        service.setIssuer(securityProperty.getIssuer());
        service.setSubject(securityProperty.getSubject());
        service.setTokenExpTime(securityProperty.getTokenExpTime());
        return service.build();
    }

    /**
     * generate Algorithm by public key String and private key String
     * @param publicKeyStr
     * @param privateKeyStr
     * @return
     * @throws Exception
     */
    private Algorithm createJwtServiceByString(String publicKeyStr, String privateKeyStr) throws Exception {

        // make the str to key object
        Base64.Decoder decoder = Base64.getDecoder();
        X509EncodedKeySpec bobPubKeySpec = new X509EncodedKeySpec(decoder.decode(publicKeyStr));
        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(decoder.decode(privateKeyStr));
        KeyFactory keyFactory  = KeyFactory.getInstance("RSA");
        RSAPublicKey publicKey = (RSAPublicKey)keyFactory.generatePublic(bobPubKeySpec);
        RSAPrivateKey privateKey  = (RSAPrivateKey)keyFactory.generatePrivate(priPKCS8);
        Algorithm algorithm = Algorithm.RSA256(publicKey, privateKey);
        return algorithm;
    }

    /**
     * generate Algorithm by public key file and private key file
     * @param publicKeyPath
     * @param privateKeyPath
     * @return
     */
    private Algorithm createJwtServiceByFile(String publicKeyPath, String privateKeyPath) throws Exception {
        // TODO 待实现
        return null;
    }

}
