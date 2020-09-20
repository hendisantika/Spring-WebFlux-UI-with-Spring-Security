package com.hendisantika.springgateway.service;

import com.hendisantika.springgateway.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/09/20
 * Time: 16.49
 */
@FeignClient("customer-service")
public interface CustomerService {

    @GetMapping(value = "/findAll")
    List<Customer> findAll();

    @GetMapping(value = "/findCustomer")
    Customer findCustomer(@RequestParam Long id);
}
