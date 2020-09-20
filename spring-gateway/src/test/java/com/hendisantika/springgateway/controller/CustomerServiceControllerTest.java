package com.hendisantika.springgateway.controller;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/09/20
 * Time: 04.56
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerServiceControllerTest {

    private final TestRestTemplate restTemplate = new TestRestTemplate();
    private String token;

}