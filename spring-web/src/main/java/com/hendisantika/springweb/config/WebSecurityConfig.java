package com.hendisantika.springweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/09/20
 * Time: 05.04
 */
@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Bean
    public GatewayAuthenticationProvider authenticationProvider() {
        GatewayAuthenticationProvider authProvider = new GatewayAuthenticationProvider();
        return authProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/favicon.ico", "/icons/**", "/images/**", "/css/**", "/h2-console/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .defaultSuccessUrl("/findAllCustomers", true)
                );

        http.authenticationProvider(authenticationProvider());
        return http.build();
    }
}
