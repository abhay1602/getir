package com.getir.assignment.controller;

import com.getir.assignment.dtos.CustomerDto;
import com.getir.assignment.dtos.OrderDto;
import com.getir.assignment.exception.CustomerValidationException;
import com.getir.assignment.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/api/v1/customer"}, produces = APPLICATION_JSON_VALUE)
public class CustomerController {

    private final CustomerService customerService;

    private static final Logger logger =
            LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/get/order/{customerId}")
    public List<OrderDto> getCustomerOrders(@PathVariable Integer customerId) {
        logger.info("getting all orders for customer " + customerId);
        return customerService.getCustomerOrders(customerId);
    }

    @PostMapping("/register")
    public void registerCustomer(@RequestBody CustomerDto customerDto) throws CustomerValidationException {
        logger.info("register new customer with email " + customerDto.getEmail());
        customerService.registerCustomer(customerDto);
    }

}