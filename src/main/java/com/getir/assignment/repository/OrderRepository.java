package com.getir.assignment.repository;

import com.getir.assignment.model.Order;
import com.getir.assignment.model.Statistic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Integer> {

    @Query("select o from Order o where o.created_date between :startDate and :endDate")
    List<Order> findByDateOrder(LocalDateTime startDate, LocalDateTime endDate);

    @Query("select new com.getir.assignment.model.Statistic(monthname(o.created_date),sum(b.price),count(o.order_id),count(b.isbn)) FROM Order o ,Book b,Customer c where o.customer=c and c.id=:customerId group by o.created_date")
    List<Statistic> findStatistics(Integer customerId);
}
