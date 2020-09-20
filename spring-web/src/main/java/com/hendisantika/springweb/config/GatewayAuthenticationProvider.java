package com.hendisantika.springweb.config;

import com.hendisantika.springweb.config.reqres.LoginRequest;
import com.hendisantika.springweb.config.reqres.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/09/20
 * Time: 05.04
 */
public class GatewayAuthenticationProvider implements AuthenticationProvider {

    @Value("${gateway.url}")
    private String gatewayUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();
        LoginRequest loginRequest = new LoginRequest(username, password);
        try {
            ResponseEntity<LoginResponse> response = restTemplate.postForEntity(this.gatewayUrl + "/authenticate",
                    loginRequest, LoginResponse.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                LoginResponse loginResponse = response.getBody();
                Collection<? extends GrantedAuthority> authorities = loginResponse.getRoles().stream()
                        .map(item -> new SimpleGrantedAuthority(item)).collect(Collectors.toList());
                return new UsernamePasswordAuthenticationToken(username, password, authorities);
            } else {
                throw new BadCredentialsException("Authentication Failed!!!");
            }
        } catch (RestClientException e) {
            throw new BadCredentialsException("Authentication Failed!!!", e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
