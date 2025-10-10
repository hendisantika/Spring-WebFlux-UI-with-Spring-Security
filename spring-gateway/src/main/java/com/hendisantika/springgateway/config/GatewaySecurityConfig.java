package com.hendisantika.springgateway.config;

import com.hendisantika.springgateway.jwt.JWTAuthEntryPoint;
import com.hendisantika.springgateway.jwt.JWTTokenFilter;
import com.hendisantika.springgateway.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

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
@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class GatewaySecurityConfig {

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

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPoint))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                                .requestMatchers("/authenticate").permitAll()
//                        .requestMatchers("/findAll").permitAll()
                                .anyRequest().authenticated()
                );

        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encryptedPass1 = encoder.encode("user1");
        String encryptedPass2 = encoder.encode("user2");
        System.out.println("pass1 " + encryptedPass1);
        System.out.println("pass2 " + encryptedPass2);
    }
}
