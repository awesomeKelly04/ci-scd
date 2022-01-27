package com.ci.scdcoreapi.dataModel.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "billingDetails", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "accountNumber"
        })
})
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BillingDetails extends BaseEntity {
    @Basic
    @Column(unique = true)
    @Size(max=13, min=13, message="Account Number should be 10 digits")
    private String accountNumber;
    @Basic
    private double tariff;

    public BillingDetails() {}

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getTariff() {
        return tariff;
    }

    public void setTariff(double tariff) {
        this.tariff = tariff;
    }
}
