package com.getir.assignment.service;

import com.getir.assignment.converter.CustomerDtoConverter;
import com.getir.assignment.dtos.CustomerDto;
import com.getir.assignment.model.Customer;
import com.getir.assignment.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    CustomerDtoConverter customerDtoConverter;

    CustomerService customerService;

    @BeforeEach
    void initUseCase() {
        customerService = new CustomerService(customerRepository, customerDtoConverter);
    }

    @Test
    void registerCustomerTest() {
        CustomerDto customerDto = new CustomerDto();
        Customer customer = new Customer();
        Mockito.when(customerRepository.save(ArgumentMatchers.any(Customer.class))).thenReturn(customer);
        Mockito.when(customerDtoConverter.mapCustomerDtoToCustomer(customerDto)).thenReturn(customer);
        Assertions.assertNotNull(customerService.registerCustomer(customerDto));
    }

    @Test
    void getCustomerOrdersTest() {
        Customer customer = new Customer();
        Mockito.when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
        Assertions.assertNotNull(customerService.getCustomerOrders(1));
    }
}