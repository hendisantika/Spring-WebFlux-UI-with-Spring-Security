package com.hendisantika.springgateway.controller;

import com.hendisantika.springgateway.dto.Customer;
import com.hendisantika.springgateway.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/09/20
 * Time: 17.10
 */
@RestController
public class CustomerServiceController {

    @Autowired
    private CustomerService customerService;

    @GetMapping(value = "/findCustomer")
    public Customer findCustomer(@RequestParam Long id) {
        Customer record = customerService.findCustomer(id);
        return record;
    }

    @GetMapping(value = "/findAll")
    public List<Customer> findAll() {
        List<Customer> customers = customerService.findAll();
        return customers;
    }
}
