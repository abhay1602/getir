package com.getir.assignment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.assignment.dtos.BookDto;
import com.getir.assignment.dtos.OrderDto;
import com.getir.assignment.model.Customer;
import com.getir.assignment.model.Order;
import com.getir.assignment.service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    OrderService orderService;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    void placeOrdertest() throws Exception {

        String uri ="/api/v1/orders/place";

        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(123);
        orderDto.setBookDto(List.of(new BookDto()));

        Mockito.when(orderService.placeOrder(orderDto))
                .thenReturn(new Order());

        mvc.perform(MockMvcRequestBuilders.post(uri)
                .content(mapToJson(orderDto))
                .with(user("admin").password("password").roles("USER","ADMIN"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void getOrderByOrderIdTest() throws Exception {

        String uri ="/api/v1/orders/get/1";

        Mockito.when(orderService.getOrderById(1))
                .thenReturn(new OrderDto());
        mvc.perform(MockMvcRequestBuilders.get(uri)
                .with(user("admin").password("password").roles("USER","ADMIN"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}