package com.h2.hotelmangement.api;

import com.h2.hotelmangement.entity.Customer;
import com.h2.hotelmangement.model.dto.CustomerDTO;
import com.h2.hotelmangement.model.mapper.CustomerMapper;
import com.h2.hotelmangement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
@RequestMapping("/api")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    private CustomerMapper customerMapper = new CustomerMapper();

    @GetMapping("/customer")
    public ResponseEntity<List<CustomerDTO>> getAllCustomerInfo(){
        List<Customer> customerList = customerService.findAllCustomer();
        return new ResponseEntity<>(customerMapper.listCustomerEnittyToDto(customerList), HttpStatus.OK);
    }

}
