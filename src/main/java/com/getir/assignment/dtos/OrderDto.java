package com.getir.assignment.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class OrderDto {

    private int userId;
    private double amount;
    private List<BookDto> bookDto;
}
