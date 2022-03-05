package com.getir.assignment.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int isbn;

    private double price;
    private LocalDateTime added_date;
    private String status;
    private String bookName;
    @ManyToOne(fetch = FetchType.LAZY)
    private Order order;

}
