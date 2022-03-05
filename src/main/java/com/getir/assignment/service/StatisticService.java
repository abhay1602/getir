package com.getir.assignment.service;

import com.getir.assignment.model.Statistic;
import com.getir.assignment.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatisticService {

    private final OrderRepository orderRepository;

    @Autowired
    public StatisticService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Statistic> getStatisticsForCustomer(Integer customerId){
         return orderRepository.findStatistics(customerId);
    }
}
