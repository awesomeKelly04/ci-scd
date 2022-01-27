package com.ci.scdcoreapi.dao;

import com.ci.scdcoreapi.dataModel.entity.BillingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillingDetailsRepository extends JpaRepository<BillingDetails, Long> {
    @Query("SELECT b from BillingDetails b where lower(b.accountNumber)  = lower(?1)")
    Optional<BillingDetails> findBillingDetailsByAccountNumber(String accountNumber);
}
