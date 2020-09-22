package com.hendisantika.springwebflux.service;

import com.hendisantika.springwebflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 23/09/20
 * Time: 06.49
 */
@Service
public class CustomerService {

    @Value("${gateway.url}")
    private String gatewayUrl;

    @Autowired
    private WebClient webClient;

    public Flux<Customer> findAll(String token) {
        Flux<Customer> customerFlux = webClient.get().uri("/findAllReactive").accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token).retrieve().bodyToFlux(Customer.class);
        return customerFlux;
    }
}
