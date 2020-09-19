package com.hendisantika.customerservice.controller;

import com.hendisantika.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

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
}
