package com.getir.assignment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.getir.assignment.dtos.CustomerDto;
import com.getir.assignment.dtos.OrderDto;
import com.getir.assignment.model.Customer;
import com.getir.assignment.service.CustomerService;
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

@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    protected MockMvc mvc;

    @MockBean
    CustomerService customerService;

    protected String mapToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

    @Test
    void getCustomerOrderTest() throws Exception {
        String uri ="/api/v1/customer/get/order/1";
        Mockito.when(customerService.getCustomerOrders(1))
                .thenReturn(List.of(new OrderDto()));
        mvc.perform(MockMvcRequestBuilders.get(uri)
                .with(user("admin").password("password").roles("USER","ADMIN"))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void registerCustomerTest() throws Exception {
        String uri ="/api/v1/customer/register";

        CustomerDto customerDto = new CustomerDto();
        customerDto.setEmail("a@b.com");
        customerDto.setFirstName("a");
        customerDto.setLastName("b");
        customerDto.setPhoneNumber("123");

        Mockito.when(customerService.registerCustomer(customerDto))
                .thenReturn(new Customer());

        mvc.perform(MockMvcRequestBuilders.post(uri)
                .with(user("admin").password("password").roles("USER","ADMIN"))
                .content(mapToJson(customerDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}