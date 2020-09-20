package com.hendisantika.springweb.config.reqres;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/09/20
 * Time: 05.06
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    private String username;

    private String password;

}
