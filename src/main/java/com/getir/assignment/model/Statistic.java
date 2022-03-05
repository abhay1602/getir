package com.getir.assignment.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistic implements Serializable {

    private String month;
    private double total_price;
    private long total_order;
    private long total_book;
}

