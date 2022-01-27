package com.ci.scdcoreapi.domain.dto.customer;


import com.ci.scdcoreapi.domain.dto.billingDetails.BillingDetailsDto;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CustomerDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String email;
    private BillingDetailsDto billingDetails;

}
