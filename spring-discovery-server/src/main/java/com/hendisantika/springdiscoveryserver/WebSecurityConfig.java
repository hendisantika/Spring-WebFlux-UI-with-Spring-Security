package com.hendisantika.springdiscoveryserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 19/09/20
 * Time: 20.43
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    /**
     * Since, spring security is enabled, the Cross site request origin calls will
     * not be allowed. We are disabling csrf calls and requiring authentication.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

}