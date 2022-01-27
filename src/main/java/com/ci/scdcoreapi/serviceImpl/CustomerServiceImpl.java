package com.ci.scdcoreapi.serviceImpl;

import com.ci.scdcoreapi.controller.response.pojo.CustomerTempPojo;
import com.ci.scdcoreapi.dao.CustomerRepository;
import com.ci.scdcoreapi.dataModel.entity.BillingDetails;
import com.ci.scdcoreapi.dataModel.entity.Customer;
import com.ci.scdcoreapi.dataModel.enumeration.GenericStatusConstant;
import com.ci.scdcoreapi.domain.dto.customer.CustomerDto;
import com.ci.scdcoreapi.domain.pojo.customer.CustomerPojo;
import com.ci.scdcoreapi.service.BillingDetailsService;
import com.ci.scdcoreapi.service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class CustomerServiceImpl implements CustomerService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final BillingDetailsService billingDetailsService;
    private final CustomerRepository customerRepository;

    @Inject
    public CustomerServiceImpl(BillingDetailsService billingDetailsService, CustomerRepository customerRepository) {

        this.billingDetailsService = billingDetailsService;
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public CustomerPojo createCustomer(CustomerDto customerDto) {
        Customer customer = new Customer();
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customer.setEmail(customerDto.getEmail());
        customer.setStatus(GenericStatusConstant.ACTIVE);
        if (customerDto.getBillingDetails() != null && customerDto.getBillingDetails().getAccountNumber() != null){
            BillingDetails billingDetails = billingDetailsService.createBillingDetails(customerDto.getBillingDetails());
            customer.setBillingDetails(billingDetails);
        }
        return getCustomerPojo(customerRepository.save(customer));
    }

    @Transactional
    @Override
    public List<CustomerPojo> getCustomerPojo(List<Customer> customers){
        if (customers.isEmpty()){
            return Collections.emptyList();
        }

        return customers.stream().map(customer -> {
            CustomerPojo customerPojo = com.ci.scdcoreapi.domain.pojo.customer.CustomerPojo.from(customer);
            return customerPojo;
        }).collect(Collectors.toList());
    }

    @Override
    public CustomerPojo getCustomerPojo(Customer customer) {
        return getCustomerPojo(Collections.singletonList(customer)).iterator().next();
    }


}
