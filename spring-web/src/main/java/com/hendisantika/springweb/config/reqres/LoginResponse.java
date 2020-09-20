package com.hendisantika.springweb.config.reqres;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/09/20
 * Time: 05.07
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private String jwt;

    private String username;

    private List<String> roles;
}
