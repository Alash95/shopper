package com.alash.shopper.repository;

import com.alash.shopper.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByEmail(String email);
    Customer findByEmail(String email);
}
