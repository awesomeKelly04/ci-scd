package com.ci.scdcoreapi.service;


import com.ci.scdcoreapi.dataModel.entity.Customer;
import com.ci.scdcoreapi.domain.dto.customer.CustomerDto;
import com.ci.scdcoreapi.domain.pojo.customer.CustomerPojo;

import java.util.List;

public interface CustomerService {

    CustomerPojo createCustomer(CustomerDto customerDto);
    List<CustomerPojo> getCustomerPojo(List<Customer> customers);
    CustomerPojo getCustomerPojo(Customer customer);
}
