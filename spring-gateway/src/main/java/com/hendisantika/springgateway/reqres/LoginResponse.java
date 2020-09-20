package com.hendisantika.springgateway.reqres;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 20/09/20
 * Time: 17.08
 */
public class LoginResponse {
    private String jwt;

    private String username;

    private List<String> roles;

    public LoginResponse() {
    }

    public LoginResponse(String jwt, String username, List<String> roles) {
        this.jwt = jwt;
        this.username = username;
        this.roles = roles;
    }

    public String getJwt() {
        return jwt;
    }

    public String getUsername() {
        return username;
    }

    public List<String> getRoles() {
        return roles;
    }
}
