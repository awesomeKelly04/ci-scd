package com.ci.scdcoreapi.serviceImpl;

import com.ci.scdcoreapi.dao.BillingDetailsRepository;
import com.ci.scdcoreapi.dataModel.entity.BillingDetails;
import com.ci.scdcoreapi.dataModel.enumeration.GenericStatusConstant;
import com.ci.scdcoreapi.domain.dto.billingDetails.BillingDetailsDto;
import com.ci.scdcoreapi.domain.pojo.billingDetails.BillingDetailsPojo;
import com.ci.scdcoreapi.service.BillingDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BillingDetailsServiceImpl implements BillingDetailsService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    private final BillingDetailsRepository billingDetailsRepository;

    @Inject
    public BillingDetailsServiceImpl(BillingDetailsRepository billingDetailsRepository) {

        this.billingDetailsRepository = billingDetailsRepository;
    }

    @Override
    public BillingDetails createBillingDetails(BillingDetailsDto billingDetailsDto) {
        BillingDetails billingDetails = new BillingDetails();
        billingDetails.setAccountNumber(billingDetailsDto.getAccountNumber()+"-01");
        billingDetails.setTariff(billingDetailsDto.getTariff());
        billingDetails.setStatus(GenericStatusConstant.ACTIVE);
        return billingDetailsRepository.save(billingDetails);
    }

    @Transactional
    public List<BillingDetailsPojo> getBillingDetailsPojo(List<BillingDetails> billingDetails){
        if (billingDetails.isEmpty()){
            return Collections.emptyList();
        }

        return billingDetails.stream().map(customer -> {
            BillingDetailsPojo billingDetailsPojo = com.ci.scdcoreapi.domain.pojo.billingDetails.BillingDetailsPojo.from(customer);
            return billingDetailsPojo;
        }).collect(Collectors.toList());
    }


    public BillingDetailsPojo getBillingDetailsPojo(BillingDetails billingDetails) {
        return getBillingDetailsPojo(Collections.singletonList(billingDetails)).iterator().next();
    }

}
