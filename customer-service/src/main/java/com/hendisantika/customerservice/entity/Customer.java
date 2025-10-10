package com.hendisantika.customerservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 19/09/20
 * Time: 21.09
 */
@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotBlank
    @Size(max = 120)
    private String name;

    @NotBlank
    private Long membershipId;
}
