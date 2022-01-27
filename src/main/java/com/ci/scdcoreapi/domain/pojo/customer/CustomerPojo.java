package com.ci.scdcoreapi.domain.pojo.customer;


import com.ci.scdcoreapi.dataModel.entity.BillingDetails;
import com.ci.scdcoreapi.dataModel.entity.Customer;
import com.ci.scdcoreapi.dataModel.enumeration.GenericStatusConstant;
import lombok.Data;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Data
public class CustomerPojo {
    protected Long id;
    private GenericStatusConstant status;
    protected Date dateDeactivated;
    private Instant createdAt;
    private Instant updatedAt;
    private String firstName;
    private String lastName;
    private String email;
    private BillingDetails billingDetails;

    public static CustomerPojo from(Customer customer){
        CustomerPojo customerPojo = new CustomerPojo();
        customerPojo.setId(customer.getId());
        customerPojo.setStatus(customer.getStatus());
        customerPojo.setDateDeactivated(customer.getDateDeactivated());
        customerPojo.setCreatedAt(customer.getCreatedAt());
        customerPojo.setUpdatedAt(customer.getUpdatedAt());
        customerPojo.setFirstName(customer.getFirstName());
        customerPojo.setLastName(customer.getLastName());
        customerPojo.setEmail(customer.getEmail());
        customerPojo.setBillingDetails(customer.getBillingDetails());
        return customerPojo;
    }

}
