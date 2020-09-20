package com.hendisantika.springweb.service;

import com.hendisantika.springweb.dto.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/09/20
 * Time: 05.13
 */
@Service
public class CustomerService {

    @Value("${gateway.url}")
    private String gatewayUrl;

    @Autowired
    private RestTemplate restTemplate;

    public List<CustomerDTO> findAll(String token) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<>("body", headers);
            ResponseEntity<List<CustomerDTO>> record = restTemplate.exchange(this.gatewayUrl + "/findAll",
                    HttpMethod.GET,
                    entity, new ParameterizedTypeReference<List<CustomerDTO>>() {
                    });
            if (record != null && record.hasBody()) {
                return record.getBody();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<CustomerDTO>();
    }
}
