package com.hendisantika.springweb.controller;

import com.hendisantika.springweb.dto.CustomerDTO;
import com.hendisantika.springweb.service.CustomerService;
import com.hendisantika.springweb.util.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/09/20
 * Time: 05.14
 */
@Controller
public class CustomerController {

    @Autowired
    private CustomerService customerServcie;

    @Autowired
    private JWTUtils jwtUtils;

    @GetMapping("/findAllCustomers")
    public String findAll(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String token = jwtUtils.generateJwtToken(authentication);
        List<CustomerDTO> customers = this.customerServcie.findAll(token);
        model.addAttribute("customers", customers);
        return "customers";
    }
}
