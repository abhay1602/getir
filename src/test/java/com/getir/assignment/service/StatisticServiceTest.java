package com.getir.assignment.service;

import com.getir.assignment.model.Statistic;
import com.getir.assignment.repository.OrderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class StatisticServiceTest {

    @Mock
    private OrderRepository orderRepository;

    StatisticService statisticService;

    @BeforeEach
    void initUseCase() {
        statisticService = new StatisticService(orderRepository);
    }

    @Test
    void getStatisticsForCustomerTest() {

        Mockito.when(orderRepository.findStatistics(1)).thenReturn(List.of(new Statistic()));
        Assertions.assertNotNull(statisticService.getStatisticsForCustomer(1));
    }
}