package com.getir.assignment.repository;

import com.getir.assignment.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends CrudRepository<Customer ,Integer> {

    Customer findByEmail(String email);
}
