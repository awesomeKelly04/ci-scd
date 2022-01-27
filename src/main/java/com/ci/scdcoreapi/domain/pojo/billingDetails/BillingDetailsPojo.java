package com.ci.scdcoreapi.domain.pojo.billingDetails;


import com.ci.scdcoreapi.dataModel.entity.BillingDetails;
import com.ci.scdcoreapi.dataModel.entity.Customer;
import com.ci.scdcoreapi.dataModel.enumeration.GenericStatusConstant;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

@Data
public class BillingDetailsPojo {
    protected Long id;
    private GenericStatusConstant status;
    protected Date dateDeactivated;
    private Instant createdAt;
    private Instant updatedAt;
    private String accountNumber;
    private double tariff;

    public static BillingDetailsPojo from(BillingDetails billingDetails){
        BillingDetailsPojo billingDetailsPojo = new BillingDetailsPojo();
        billingDetailsPojo.setId(billingDetails.getId());
        billingDetailsPojo.setStatus(billingDetails.getStatus());
        billingDetailsPojo.setDateDeactivated(billingDetails.getDateDeactivated());
        billingDetailsPojo.setCreatedAt(billingDetails.getCreatedAt());
        billingDetailsPojo.setUpdatedAt(billingDetails.getUpdatedAt());
        billingDetailsPojo.setAccountNumber(billingDetails.getAccountNumber());
        billingDetailsPojo.setTariff(billingDetails.getTariff());
        return billingDetailsPojo;
    }

}
