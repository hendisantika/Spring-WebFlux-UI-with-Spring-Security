package com.hendisantika.springweb.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/09/20
 * Time: 05.13
 */
@Service
public class CustomerService {

    @Value("${gateway.url}")
    private String gatewayUrl;

    @Autowired
    private RestTemplate restTemplate;
}
