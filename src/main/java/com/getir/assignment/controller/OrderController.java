package com.getir.assignment.controller;

import com.getir.assignment.dtos.OrderDto;
import com.getir.assignment.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/api/v1/orders"}, produces = APPLICATION_JSON_VALUE)
public class OrderController {

    private final OrderService orderService;

    private static final Logger logger =
            LoggerFactory.getLogger(OrderController.class);

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/place")
    public void placeOrders(@RequestBody OrderDto orderDto) {
        orderService.placeOrder(orderDto);
    }

    @GetMapping("/get/{orderId}")
    public OrderDto getOrderByOrderId(@PathVariable Integer orderId) {
        return orderService.getOrderById(orderId);
    }

    @GetMapping("/get/range")
    public List<OrderDto> getOrdersByArrival(@RequestParam String startDate, @RequestParam String endDate) {
        return orderService.getOrderByArrival(startDate, endDate);
    }

}
