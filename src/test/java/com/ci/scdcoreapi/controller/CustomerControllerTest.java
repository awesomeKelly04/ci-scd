package com.ci.scdcoreapi.controller;

import com.ci.scdcoreapi.dao.BillingDetailsRepository;
import com.ci.scdcoreapi.dao.CustomerRepository;
import com.ci.scdcoreapi.dataModel.entity.Customer;
import com.ci.scdcoreapi.domain.dto.customer.CustomerDto;
import com.ci.scdcoreapi.domain.pojo.customer.CustomerPojo;
import com.ci.scdcoreapi.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.mockito.Mockito.*;

class CustomerControllerTest {
    @Mock
    CustomerRepository customerRepository;
    @Mock
    BillingDetailsRepository billingDetailsRepository;
    @Mock
    CustomerService customerService;
    @Mock
    Logger logger;
    @InjectMocks
    CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateCustomerRecord() {
        when(customerRepository.findCustomerByEmail(anyString())).thenReturn(null);
        when(billingDetailsRepository.findBillingDetailsByAccountNumber(anyString())).thenReturn(null);
        when(customerService.createCustomer(any())).thenReturn(new CustomerPojo());

        ResponseEntity<CustomerPojo> result = customerController.createCustomerRecord(new CustomerDto());
        Assertions.assertEquals(HttpStatus.CREATED, result.getStatusCode());
        Assertions.assertEquals(new CustomerDto().getEmail(), Objects.requireNonNull(result.getBody()).getEmail());
    }

    @Test
    void testSearchCustomerRecord() {
        when(customerService.getCustomerPojo((List<Customer>) any())).thenReturn((List<CustomerPojo>) Arrays.asList(new CustomerPojo()));

        ResponseEntity<List<CustomerPojo>> result = customerController.searchCustomerRecord();
        Assertions.assertEquals(HttpStatus.OK, result.getStatusCode());
    }

    @Test
    void testGetCustomerRecord() {
        when(customerService.getCustomerPojo((Customer) any())).thenReturn(new CustomerPojo());

        ResponseEntity<CustomerPojo> result = customerController.getCustomerRecord(1L);
        Assertions.assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }
}

