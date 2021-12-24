package com.hendisantika.springwebflux.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.RedirectServerAuthenticationSuccessHandler;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.tcp.TcpClient;

import java.util.concurrent.TimeUnit;

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
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
    public static final int TIMEOUT = 1000;

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

    @Bean
    public WebClient webClientWithTimeout() {
        final var tcpClient = TcpClient
                .create()
                .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, TIMEOUT)
                .doOnConnected(connection -> {
                    connection.addHandlerLast(new ReadTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
                    connection.addHandlerLast(new WriteTimeoutHandler(TIMEOUT, TimeUnit.MILLISECONDS));
                });

        return WebClient.builder()
                .baseUrl(BASE_URL)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.from(tcpClient)))
                .build();
    }


}
