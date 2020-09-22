package com.hendisantika.springwebflux.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hendisantika.springwebflux.dto.Customer;
import org.apache.commons.codec.Charsets;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Project : Spring-WebFlux-UI-with-Spring-Security
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 23/09/20
 * Time: 06.49
 */
@Service
public class CustomerService {

    @Value("${gateway.url}")
    private String gatewayUrl;

    @Autowired
    private WebClient webClient;

    public Flux<Customer> findAll(String token) {
        Flux<Customer> customerFlux = webClient.get().uri("/findAllReactive").accept(MediaType.APPLICATION_JSON)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token).retrieve().bodyToFlux(Customer.class);
        return customerFlux;
    }

    public List<Customer> findAllCustomers(String token) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            // Using HttpClient to call to get the customer list. Not the best way to get
            // the data from restful apis but for now this will do fine.
            HttpGet httpGet = new HttpGet(this.gatewayUrl + "/findAll");
            httpGet.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
            httpGet.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);

            CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.OK.value()) {
                HttpEntity entity = httpResponse.getEntity();
                Header encodingHeader = entity.getContentEncoding();

                // you need to know the encoding to parse correctly
                Charset encoding = encodingHeader == null ? StandardCharsets.UTF_8
                        : Charsets.toCharset(encodingHeader.getValue());
                // use org.apache.http.util.EntityUtils to read json as string
                String jsonRes = EntityUtils.toString(entity, encoding);
                List<Customer> customers = convertJsonToResponse(jsonRes);
                return customers;
            } else {
                throw new RestClientException(httpResponse.getStatusLine().getReasonPhrase());
            }

        } catch (RestClientException | ParseException | IOException e) {
            throw new RestClientException("Retrieval Failed", e);
        } finally {
            try {
                if (httpClient != null)
                    httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static List<Customer> convertJsonToResponse(final String response)
            throws JsonMappingException, JsonProcessingException {
        final ObjectMapper mapper = new ObjectMapper();
        @SuppressWarnings("unchecked")
        List<Customer> customers = mapper.readValue(response, List.class);
        return customers;
    }

}
