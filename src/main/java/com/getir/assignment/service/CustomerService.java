package com.getir.assignment.service;

import com.getir.assignment.converter.CustomerDtoConverter;
import com.getir.assignment.dtos.CustomerDto;
import com.getir.assignment.dtos.OrderDto;
import com.getir.assignment.exception.CustomerValidationException;
import com.getir.assignment.model.Customer;
import com.getir.assignment.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerDtoConverter customerDtoConverter;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, CustomerDtoConverter customerDtoConverter) {
        this.customerRepository = customerRepository;
        this.customerDtoConverter = customerDtoConverter;
    }

    public Customer registerCustomer(CustomerDto customer) throws CustomerValidationException {
        if (validateCustomerDetails(customer)) {
            throw new CustomerValidationException("User already exist for this email address");
        }
        return customerRepository.save(customerDtoConverter.mapCustomerDtoToCustomer(customer));
    }

    private boolean validateCustomerDetails(CustomerDto customerDto) {
        Customer customer = customerRepository.findByEmail(customerDto.getEmail());
        if (customer != null && customer.getEmail().equals(customerDto.getEmail())) {
            return true;
        }
        return false;
    }

    public List<OrderDto> getCustomerOrders(Integer customerId) {
        Customer customer = customerRepository.findById(customerId).get();
        return customerDtoConverter.mapOrderListToOrderDto(customer.getOrder());
    }
}
