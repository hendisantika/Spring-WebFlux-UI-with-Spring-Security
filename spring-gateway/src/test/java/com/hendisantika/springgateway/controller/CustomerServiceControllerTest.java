package com.hendisantika.springgateway.controller;

import com.hendisantika.springgateway.reqres.LoginRequest;
import com.hendisantika.springgateway.reqres.LoginResponse;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
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

    @Before
    public void setup() {

        LoginRequest loginRequest = new LoginRequest("user1", "user1");
        try {
            ResponseEntity<LoginResponse> response = restTemplate.postForEntity("http://localhost:8080/authenticate",
                    loginRequest, LoginResponse.class);
            LoginResponse loginResponse = response.getBody();
            token = loginResponse.getJwt();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}