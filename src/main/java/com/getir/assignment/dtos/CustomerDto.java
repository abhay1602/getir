package com.getir.assignment.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerDto {
    
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private AddressDto addressDto;
}
