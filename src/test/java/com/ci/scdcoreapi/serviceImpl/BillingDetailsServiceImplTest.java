package com.ci.scdcoreapi.serviceImpl;

import com.ci.scdcoreapi.dao.BillingDetailsRepository;
import com.ci.scdcoreapi.dataModel.entity.BillingDetails;
import com.ci.scdcoreapi.domain.dto.billingDetails.BillingDetailsDto;
import com.ci.scdcoreapi.domain.pojo.billingDetails.BillingDetailsPojo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import java.util.Collections;
import java.util.List;

class BillingDetailsServiceImplTest {
    @Mock
    Logger logger;
    @Mock
    BillingDetailsRepository billingDetailsRepository;
    @InjectMocks
    BillingDetailsServiceImpl billingDetailsServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testCreateBillingDetails() {
        BillingDetails result = billingDetailsServiceImpl.createBillingDetails(new BillingDetailsDto());
        Assertions.assertNull(result);
    }

    @Test
    void testGetBillingDetailsPojo() {
        List<BillingDetailsPojo> result = billingDetailsServiceImpl.getBillingDetailsPojo(Collections.singletonList(new BillingDetails()));
        Assertions.assertEquals(Collections.singletonList(new BillingDetailsPojo()), result);
    }

    @Test
    void testGetBillingDetailsPojo2() {
        BillingDetailsPojo result = billingDetailsServiceImpl.getBillingDetailsPojo(new BillingDetails());
        Assertions.assertEquals(new BillingDetailsPojo(), result);
    }
}
