package com.ci.scdcoreapi.controller;

import com.ci.scdcoreapi.controller.response.pojo.CustomerTempPojo;
import com.ci.scdcoreapi.dao.BillingDetailsRepository;
import com.ci.scdcoreapi.dao.CustomerRepository;
import com.ci.scdcoreapi.dataModel.entity.BillingDetails;
import com.ci.scdcoreapi.dataModel.entity.Customer;
import com.ci.scdcoreapi.domain.dto.customer.CustomerDto;
import com.ci.scdcoreapi.domain.pojo.customer.CustomerPojo;
import com.ci.scdcoreapi.infrastructure.exception.ErrorResponse;
import com.ci.scdcoreapi.service.CustomerService;
import com.google.gson.Gson;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final BillingDetailsRepository billingDetailsRepository;
    private final CustomerService customerService;
    private final Gson gson;
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @PostMapping("/create-customer")
    @Operation(summary = "Add Customer Record", description = "create a customer record with this API.")
    @ApiResponses({ @ApiResponse(responseCode = "201", description = "Create a customer record",
            content = @Content(schema = @Schema(implementation = Customer.class))),
            @ApiResponse(responseCode = "400", description = "Bad Request") })
    public ResponseEntity<CustomerPojo> createCustomerRecord(@RequestBody @Valid CustomerDto customerDto) {
        Optional<Customer> optionalCustomer = Optional.empty();
        if (customerDto != null && customerDto.getEmail() != null) {
            optionalCustomer = customerRepository.findCustomerByEmail(customerDto.getEmail().trim());
        }
        if (optionalCustomer.isPresent()) {
            return new ResponseEntity(new com.ci.scdcoreapi.controller.response.ApiResponse(false, "Customer is already exist with this email!"), HttpStatus.BAD_REQUEST);
        }

        if (customerDto != null && customerDto.getBillingDetails() != null && customerDto.getBillingDetails().getAccountNumber() != null && customerDto.getBillingDetails().getAccountNumber().length() != 10){
            return new ResponseEntity(new com.ci.scdcoreapi.controller.response.ApiResponse(false, "Billing account number must be 10 digits!"), HttpStatus.BAD_REQUEST);
        }

        if (customerDto != null && customerDto.getBillingDetails() != null){
            Optional<BillingDetails> optionalBillingDetails = billingDetailsRepository.findBillingDetailsByAccountNumber(customerDto.getBillingDetails().getAccountNumber()+"-01");
            if (optionalBillingDetails.isPresent()){
                return new ResponseEntity(new com.ci.scdcoreapi.controller.response.ApiResponse(false, "Billing account number already in use!"), HttpStatus.BAD_REQUEST);
            }
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.createCustomer(customerDto));
    }


    @GetMapping
    @Operation(summary = "get all customer records", description = "filter customer records")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "search all customer records"))
    public ResponseEntity<List<CustomerPojo>> searchCustomerRecord() {
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerPojo(customerRepository.findAll()));
    }

    @Transactional
    @GetMapping("/{id:\\d+}")
    @Operation(summary = "Get a customer record", description = "Call this API to get a single customer record")
    @ApiResponses(@ApiResponse(responseCode = "200", description = "retrieve a customer record"))
    public ResponseEntity<CustomerPojo> getProjectAccount(@PathVariable("id") Long id) {
        CustomerTempPojo customerTempPojo = new CustomerTempPojo();
        Optional<Customer> optionalCustomer  = customerRepository.findById(id);
        if (!optionalCustomer.isPresent()){
            return new ResponseEntity(new com.ci.scdcoreapi.controller.response.ApiResponse(false, "Customer record not found!"), HttpStatus.NOT_FOUND);
        } else {
            customerTempPojo.setCustomer(optionalCustomer.get());
        }
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerPojo(customerTempPojo.getCustomer())) ;
    }

}
