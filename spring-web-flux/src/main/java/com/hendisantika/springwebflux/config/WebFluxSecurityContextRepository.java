package com.hendisantika.springwebflux.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 22/09/20
 * Time: 22.19
 */
public class WebFluxSecurityContextRepository implements ServerSecurityContextRepository {

    @Autowired
    private WebFluxAuthManager authManager;

}
