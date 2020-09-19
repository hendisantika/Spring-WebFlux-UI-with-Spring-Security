package com.hendisantika.customerservice.controller;

import com.hendisantika.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 19/09/20
 * Time: 21.12
 */
@RestController
public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;

    @GetMapping(value = "/data")
    public Mono<String> getData(ServerHttpRequest request, ServerHttpResponse response) {

        String path = request.getURI().getPath();
        Mono<String> data = Mono.just("Invoking " + path + " !!");
        return data;
    }

    @GetMapping(value = "/findCustomer")
    public src.main.java.com.hendisantika.customerservice.entity.Customer findCustomer(@RequestParam Long id) {
        Optional<src.main.java.com.hendisantika.customerservice.entity.Customer> record =
                customerRepository.findById(id);
        src.main.java.com.hendisantika.customerservice.entity.Customer data = record.get();
        return data;
    }
}
