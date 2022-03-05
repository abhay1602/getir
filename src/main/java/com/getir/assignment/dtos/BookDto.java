package com.getir.assignment.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BookDto {

    private int isbn;
    private String bookName;
    private String status;
    private double price;
}
