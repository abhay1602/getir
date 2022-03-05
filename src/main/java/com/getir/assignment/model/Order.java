package com.getir.assignment.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int order_id;

    private double amount;
    private LocalDateTime created_date;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id", nullable=false)
    private Customer customer;
    @OneToMany(mappedBy="order", cascade = CascadeType.ALL)
    private List<Book> book;

}
