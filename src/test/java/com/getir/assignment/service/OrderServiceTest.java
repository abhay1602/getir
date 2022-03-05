package com.getir.assignment.service;

import com.getir.assignment.converter.OrderDtoConverter;
import com.getir.assignment.dtos.OrderDto;
import com.getir.assignment.model.Customer;
import com.getir.assignment.model.Order;
import com.getir.assignment.repository.CustomerRepository;
import com.getir.assignment.repository.OrderRepository;
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
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private BookService bookService;

    @Mock
    private OrderDtoConverter orderDtoConverter;

    OrderService orderService;

    @BeforeEach
    void initUseCase() {
        orderService = new OrderService(orderRepository, customerRepository, bookService, orderDtoConverter);
    }

    @Test
    void placeOrderTest() {
        OrderDto orderDto = new OrderDto();
        Order order = new Order();
        Customer customer = Mockito.mock(Customer.class);
        Mockito.when(orderRepository.save(ArgumentMatchers.any(Order.class))).thenReturn(order);
        Mockito.when(orderDtoConverter.mapDtoToOrder(orderDto, customer)).thenReturn(order);
        Mockito.when(customerRepository.findById(orderDto.getUserId())).thenReturn(Optional.of(customer));
        Assertions.assertNotNull(orderService.placeOrder(orderDto));
    }

    @Test
    void getOrderByIdTest() {
        Order order = Mockito.mock(Order.class);
        Mockito.when(orderRepository.findById(1)).thenReturn(Optional.of(order));
        Mockito.when(orderDtoConverter.mapOrderToOrderDto(order)).thenReturn(Mockito.mock(OrderDto.class));
        Assertions.assertNotNull(orderService.getOrderById(1));
    }
}