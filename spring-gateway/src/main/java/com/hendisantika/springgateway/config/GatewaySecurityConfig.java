package com.hendisantika.springgateway.config;

import com.hendisantika.springgateway.jwt.JWTAuthEntryPoint;
import com.hendisantika.springgateway.jwt.JWTTokenFilter;
import com.hendisantika.springgateway.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/09/20
 * Time: 17.00
 */
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class GatewaySecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JWTAuthEntryPoint authEntryPoint;

    @Bean
    public JWTTokenFilter authenticationJwtTokenFilter() {
        return new JWTTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

}
