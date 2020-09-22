package com.hendisantika.springwebflux.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 22/09/20
 * Time: 22.19
 */
@EnableWebFluxSecurity
@Configuration
public class WebFluxSecurityConfig {

    @Autowired
    private WebFluxAuthManager authManager;

    @Bean
    protected SecurityWebFilterChain securityFilterChange(ServerHttpSecurity http) throws Exception {
        http.authorizeExchange()
                // URL that starts with / or /login/
                .pathMatchers("/", "/login", "/js/**", "/images/**", "/css/**", "/h2-console/**").permitAll()
                .anyExchange().authenticated().and().formLogin()
                .authenticationManager(authManager)
                .authenticationSuccessHandler(new RedirectServerAuthenticationSuccessHandler("/findAllCustomers"));
        return http.build();

    }

}
