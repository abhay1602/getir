package com.getir.assignment.converter;

import com.getir.assignment.dtos.BookDto;
import com.getir.assignment.dtos.CustomerDto;
import com.getir.assignment.dtos.OrderDto;
import com.getir.assignment.model.Book;
import com.getir.assignment.model.Customer;
import com.getir.assignment.model.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerDtoConverter {

    public List<OrderDto> mapOrderListToOrderDto(List<Order> orderList) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        if (CollectionUtils.isEmpty(orderList)) {
            return orderDtoList;
        }
        return orderList.stream()
                .map(this::mapOrderToOrderDto)
                .collect(Collectors.toList());
    }

    public OrderDto mapOrderToOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(order.getCustomer().getId());
        orderDto.setAmount(order.getAmount());
        orderDto.setBookDto(mapBookListToBookDto(order.getBook()));
        return orderDto;
    }

    public List<BookDto> mapBookListToBookDto(List<Book> bookList) {
        return bookList.stream()
                .map(book -> mapBookToBookDto(book))
                .collect(Collectors.toList());
    }

    public BookDto mapBookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setPrice(book.getPrice());
        bookDto.setBookName(book.getBookName());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setStatus(book.getStatus());
        return bookDto;
    }

    public Customer mapCustomerDtoToCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirst_name(customerDto.getFirstName());
        customer.setEmail(customerDto.getEmail());
        customer.setCreated_date(LocalDateTime.now());
        return customer;
    }
}
