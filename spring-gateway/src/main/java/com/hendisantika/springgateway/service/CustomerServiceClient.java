package com.hendisantika.springgateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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
}
