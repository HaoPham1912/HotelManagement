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

import static com.h2.hotelmangement.common.util.CommonConstants.PREFIX_API;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping(PREFIX_API)
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    private CustomerMapper customerMapper = new CustomerMapper();

    @GetMapping("/customer")
    public ResponseEntity<List<CustomerDTO>> getAllCustomerInfo(){
        List<Customer> customerList = customerService.findAllCustomer();
        return new ResponseEntity<>(customerMapper.listCustomerEnittyToDto(customerList), HttpStatus.OK);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable String id){
        Long cusId = Long.valueOf(id);
        Customer customer = customerService.getCustomerById(cusId);

        CustomerDTO customerDTO = customerMapper.customerEntityToDto(customer);

        return new ResponseEntity<>(customerDTO, HttpStatus.OK);
    }

    @PutMapping("/customer/{id}")
    public ResponseEntity<HttpStatus> updateCustomerInfor(@PathVariable String id, @RequestBody CustomerDTO customerDTO){
        Long cusId = Long.valueOf(id);
        Customer customer = customerService.getCustomerById(cusId);
        if(customer != null){
            customer.setEmail(customerDTO.getEmail());
            customer.setIdCard(customerDTO.getIdCard());
            customer.setName(customerDTO.getName());
            customer.setPhone(customerDTO.getPhone());

            customerService.save(customer);
            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
