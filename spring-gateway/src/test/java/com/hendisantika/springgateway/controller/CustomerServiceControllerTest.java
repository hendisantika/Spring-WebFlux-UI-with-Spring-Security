package com.hendisantika.springgateway.controller;

import com.hendisantika.springgateway.reqres.LoginRequest;
import com.hendisantika.springgateway.reqres.LoginResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

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

    @BeforeEach
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

    @Test
    public void findAllCustomersTest() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/findAll", HttpMethod.GET,
                new HttpEntity<>(headers), String.class);

        System.out.println(response.getBody());
        assertThat(response.getStatusCode().equals(HttpStatus.OK));
    }
}