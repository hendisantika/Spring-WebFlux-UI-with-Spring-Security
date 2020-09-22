package com.hendisantika.springwebflux.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.stereotype.Component;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 22/09/20
 * Time: 22.14
 */
@Component
public class WebFluxAuthManager implements ReactiveAuthenticationManager {

    @Value("${gateway.url}")
    private String gatewayUrl;
}
