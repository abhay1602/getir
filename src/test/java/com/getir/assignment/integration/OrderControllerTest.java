package com.getir.assignment.integration;

import com.getir.assignment.AssignmentApplication;
import com.getir.assignment.dtos.OrderDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;

@SpringBootTest(classes = AssignmentApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration
@ActiveProfiles("test")
public class OrderControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final String baseUrl = "/api/v1/orders";

    @Sql(scripts = {"classpath:schema.sql"})
    @Test
    public void placeOrderTest() {
        ResponseEntity<Void> responseEntity = this.restTemplate.withBasicAuth("admin", "password")
                .postForEntity("http://localhost:" + port + baseUrl + "/place", new OrderDto(), Void.class);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

}
