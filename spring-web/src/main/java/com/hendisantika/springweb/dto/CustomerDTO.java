package com.hendisantika.springweb.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/09/20
 * Time: 05.11
 */
@Data
@EqualsAndHashCode
public class CustomerDTO {
    private Long id;

    private String name;

    private Long membershipId;

    public CustomerDTO() {
        super();
    }

    public CustomerDTO(Long id, String name, Long membershipId) {
        super();
        this.id = id;
        this.name = name;
        this.membershipId = membershipId;
    }
}
