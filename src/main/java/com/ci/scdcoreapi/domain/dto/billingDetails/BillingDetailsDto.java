package com.ci.scdcoreapi.domain.dto.billingDetails;


import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BillingDetailsDto {
    @NotNull
    private String accountNumber;
    private double tariff;
}
