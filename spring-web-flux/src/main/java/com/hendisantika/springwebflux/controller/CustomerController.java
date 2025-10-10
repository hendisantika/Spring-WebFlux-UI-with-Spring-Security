package com.hendisantika.springwebflux.controller;

import com.hendisantika.springwebflux.dto.Customer;
import com.hendisantika.springwebflux.service.CustomerService;
import com.hendisantika.springwebflux.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.thymeleaf.spring6.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 23/09/20
 * Time: 06.53
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerServcie;

    @Autowired
    private JWTUtils jwtUtils;

    @GetMapping("/findAllCustomers")
    public String findAll(Model model, Authentication authentication) {
        String token = jwtUtils.generateJwtToken(authentication);
        List<Customer> customers = this.customerServcie.findAllCustomers(token);
        model.addAttribute("customers", customers);
        return "customers";
    }

    @GetMapping("/findAllCustomersReactive")
    public String findAllReactive(Model model, Authentication authentication) {
        String token = jwtUtils.generateJwtToken(authentication);
        List<Customer> customers = this.customerServcie.findAllCustomers(token);
        // Introducing a delay of 200 ms to show the streaming behaviour
        IReactiveDataDriverContextVariable data = new ReactiveDataDriverContextVariable(Flux.fromIterable(customers).delayElements(Duration.ofMillis(200)));
        model.addAttribute("customers", data);

        return "customers-reactive";
    }
}
