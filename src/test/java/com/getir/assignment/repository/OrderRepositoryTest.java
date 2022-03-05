package com.getir.assignment.repository;

import com.getir.assignment.model.Book;
import com.getir.assignment.model.Customer;
import com.getir.assignment.model.Order;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class OrderRepositoryTest {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerRepository customerRepository;

    @BeforeEach
    void initUseCase() {
        Order order = new Order();
        Customer customer = new Customer();
        customerRepository.save(customer);
        order.setCustomer(customer);
        order.setBook(List.of(new Book()));
        List<Order> orderList = Arrays.asList(order);
        orderRepository.saveAll(orderList);
    }

    @Test
    void findByDateOrderTest() {
        Assertions.assertNotNull(orderRepository.findById(1));
    }
}