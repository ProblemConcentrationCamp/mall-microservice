package com.mall.web.security.config;

import com.mall.web.security.auth .JwtAuthenticationEntryPoint;
import com.mall.web.security.filter.JwtAuthorizationTokenFilter;
import com.mall.web.security.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * <pre>
 *
 * </pre>
 *
 * @author LCN
 * @date 2019-12-17 11:38
 */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private UserDetailsService userDetailsService;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;


    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        // 由于使用的是JWT，我们这里不需要csrf
        httpSecurity.csrf().disable()
            // 认证失败的处理器
            .exceptionHandling().authenticationEntryPoint(authenticationEntryPoint).and()
            // 基于token，所以不需要session
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests()
            // preflight请求的话,直接允许
            .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
            // 下面的请求也可以直接通过
            .antMatchers(
                "/open/**"
            ).permitAll()
            // 其他的请求都需要验证
            .anyRequest().authenticated();

        // 在返回直接先进行过滤
        httpSecurity.addFilterBefore(authenticationTokenFilterBean(),
            UsernamePasswordAuthenticationFilter.class);

        // Disable Caching
        httpSecurity.headers().cacheControl();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        //  With this configured, the encrypted password in the database
        //  is automatically compared to the password entered by the user
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoderBean());
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
        return super.authenticationManagerBean();
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
      //  return new JwtAuthorizationTokenFilter();
        return null;
    }

    @Bean
    public JwtService createJwtService() {
        return new JwtService();
    }
}
