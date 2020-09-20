package com.hendisantika.springweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/09/20
 * Time: 05.04
 */
public class GatewayAuthenticationProvider implements AuthenticationProvider {

    @Value("${gateway.url}")
    private String gatewayUrl;

    @Autowired
    private RestTemplate restTemplate;

}
