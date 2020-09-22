package com.hendisantika.springwebflux.controller;

import com.hendisantika.springwebflux.service.CustomerService;
import com.hendisantika.springwebflux.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
}
