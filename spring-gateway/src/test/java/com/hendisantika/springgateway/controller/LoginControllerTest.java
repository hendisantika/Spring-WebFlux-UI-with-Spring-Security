package com.hendisantika.springgateway.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hendisantika.springgateway.SpringGatewayApplication;
import com.hendisantika.springgateway.reqres.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 21/09/20
 * Time: 04.52
 */
@SpringBootTest(classes = SpringGatewayApplication.class)
@WebAppConfiguration
class LoginControllerTest {
    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    private static String asJsonString(final Object obj) throws Exception {
        final ObjectMapper mapper = new ObjectMapper();
        final String jsonContent = mapper.writeValueAsString(obj);
        return jsonContent;
    }

    @BeforeEach
    public void setup() {
        this.mockMvc = webAppContextSetup(wac).build();
    }

    @Test
    public void authenticateValidUser() throws Exception {
        LoginRequest loginRequest = new LoginRequest("user1", "user1");
        mockMvc.perform(post("/authenticate").content(asJsonString(loginRequest))
                .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}