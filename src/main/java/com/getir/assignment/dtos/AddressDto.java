package com.getir.assignment.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddressDto {

    private String address_line1;
    private String city;
    private String country;
    private int pinCode;
}
