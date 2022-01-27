package com.ci.scdcoreapi.dao;

import com.ci.scdcoreapi.dataModel.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query("SELECT c from Customer c where lower(c.email)  = lower(?1)")
    Optional<Customer> findCustomerByEmail(String email);
}
