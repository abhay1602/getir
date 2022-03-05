package com.getir.assignment.service;

import com.getir.assignment.converter.OrderDtoConverter;
import com.getir.assignment.dtos.BookDto;
import com.getir.assignment.dtos.OrderDto;
import com.getir.assignment.model.Book;
import com.getir.assignment.model.Customer;
import com.getir.assignment.model.Order;
import com.getir.assignment.repository.CustomerRepository;
import com.getir.assignment.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final BookService bookService;
    private final OrderDtoConverter orderDtoConverter;

    @Autowired
    public OrderService(OrderRepository orderRepository, CustomerRepository customerRepository, BookService bookService, OrderDtoConverter orderDtoConverter) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
        this.bookService = bookService;
        this.orderDtoConverter = orderDtoConverter;
    }

    @Transactional
    public Order placeOrder(OrderDto orderDto) {
        Customer customer = customerRepository.findById(orderDto.getUserId()).get();
        Order order = orderRepository.save(orderDtoConverter.mapDtoToOrder(orderDto, customer));
        if (orderDto.getBookDto() != null) {
            updateBookStock(mapDtoListToBook(orderDto.getBookDto()), order);
        }
        return order;
    }

    private List<Book> mapDtoListToBook(List<BookDto> bookDtoList) {
        return bookDtoList.stream().map(bookService::mapBookDtoToBook)
                .collect(Collectors.toList());
    }

    private void updateBookStock(List<Book> bookList, Order order) {
        for (Book book : bookList) {
            book.setOrder(order);
            bookService.updateBookStock(book);
        }
    }

    public OrderDto getOrderById(Integer orderId) {
        Order order = orderRepository.findById(orderId).get();
        return orderDtoConverter.mapOrderToOrderDto(order);
    }

    public List<OrderDto> getOrderByArrival(String startdate, String enddate) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime startDate = LocalDateTime.parse(startdate, formatter);
        LocalDateTime endDate = LocalDateTime.parse(enddate, formatter);
        List<Order> order = orderRepository.findByDateOrder(startDate, endDate);
        return orderDtoConverter.mapOrderToOrderDto(order);
    }
}
