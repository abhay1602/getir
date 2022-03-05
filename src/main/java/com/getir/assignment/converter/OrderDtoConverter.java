package com.getir.assignment.converter;

import com.getir.assignment.dtos.BookDto;
import com.getir.assignment.dtos.OrderDto;
import com.getir.assignment.model.Book;
import com.getir.assignment.model.Customer;
import com.getir.assignment.model.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDtoConverter {

    public List<OrderDto> mapOrderToOrderDto(List<Order> orderList) {
        if (CollectionUtils.isEmpty(orderList)) {
            return List.of(new OrderDto());
        }
        return orderList.stream()
                .map(this::mapOrderToOrderDto)
                .collect(Collectors.toList());
    }

    public OrderDto mapOrderToOrderDto(Order order) {
        if (order == null || order.getCustomer() == null || order.getBook() == null) {
            return new OrderDto();
        }
        OrderDto orderDto = new OrderDto();
        orderDto.setAmount(order.getAmount());
        orderDto.setUserId(order.getCustomer().getId());
        orderDto.setBookDto(mapBookListToBookDto(order.getBook()));
        return orderDto;
    }

    public List<BookDto> mapBookListToBookDto(List<Book> bookList) {
        return bookList.stream()
                .map(this::mapBookToBookDto)
                .collect(Collectors.toList());
    }

    private BookDto mapBookToBookDto(Book book) {
        BookDto bookDto = new BookDto();
        bookDto.setBookName(book.getBookName());
        bookDto.setIsbn(book.getIsbn());
        bookDto.setStatus(book.getStatus());
        bookDto.setPrice(book.getPrice());
        return bookDto;
    }

    public Order mapDtoToOrder(OrderDto orderDto, Customer customer) {
        Order order = new Order();
        double amount = calculateOrderAmount(orderDto.getBookDto());
        order.setAmount(amount);
        order.setCustomer(customer);
        order.setCreated_date(LocalDateTime.now());
        return order;
    }

    private double calculateOrderAmount(List<BookDto> bookDto) {
        double amount = 0.0;
        if (CollectionUtils.isEmpty(bookDto)) {
            return amount;
        }
        for (BookDto book : bookDto) {
            amount += book.getPrice();
        }
        return amount;
    }

}
