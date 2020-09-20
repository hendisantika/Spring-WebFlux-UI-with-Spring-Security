package com.hendisantika.springgateway.service;

import com.hendisantika.springgateway.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/09/20
 * Time: 16.50
 */
@Service
public class CustomerServiceClient {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    private String customerServiceUrl;

    @PostConstruct
    public void setup() {
        List<ServiceInstance> instances = discoveryClient.getInstances("customer-service");
        if (instances != null && instances.size() > 0) {
            ServiceInstance serviceInstance = instances.get(0);
            this.customerServiceUrl = serviceInstance.getUri().toString();
        }
    }

    public Customer findCustomer(Long id) {
        ResponseEntity<Customer> response = restTemplate
                .getForEntity(this.customerServiceUrl + "/findCustomer?id=" + id, Customer.class);

        if (response.getStatusCode() == HttpStatus.OK) {
            Customer customer = response.getBody();
            return customer;
        }
        return null;
    }

    public List<Customer> findAll() {
        ResponseEntity<List<Customer>> record = restTemplate.exchange(this.customerServiceUrl + "/findAll",
                HttpMethod.GET, null, new ParameterizedTypeReference<List<Customer>>() {
                });
        if (record != null && record.hasBody()) {
            return record.getBody();
        }

        return null;
    }
}
