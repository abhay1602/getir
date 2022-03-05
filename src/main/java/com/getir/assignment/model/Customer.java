package com.getir.assignment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String first_name;
    private String email;
    private LocalDateTime created_date;
    @OneToMany(mappedBy = "customer")
    private List<Order> order;

}
