package com.hendisantika.customerservice.repository;

import com.hendisantika.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 19/09/20
 * Time: 21.11
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
