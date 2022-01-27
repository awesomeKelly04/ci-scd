package com.ci.scdcoreapi.service;


import com.ci.scdcoreapi.dataModel.entity.BillingDetails;
import com.ci.scdcoreapi.domain.dto.billingDetails.BillingDetailsDto;

public interface BillingDetailsService {

    BillingDetails createBillingDetails(BillingDetailsDto billingDetailsDto);
}
