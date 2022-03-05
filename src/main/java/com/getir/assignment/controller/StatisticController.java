package com.getir.assignment.controller;

import com.getir.assignment.model.Statistic;
import com.getir.assignment.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = {"/api/v1/statistic"}, produces = APPLICATION_JSON_VALUE)
public class StatisticController {

    private final StatisticService statisticService;

    @Autowired
    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @GetMapping("/{customerId}")
    public List<Statistic> getStatisticForCustomer(@PathVariable Integer customerId) {
        return statisticService.getStatisticsForCustomer(customerId);
    }
}
