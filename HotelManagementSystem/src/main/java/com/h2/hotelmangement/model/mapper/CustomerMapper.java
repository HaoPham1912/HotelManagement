package com.h2.hotelmangement.model.mapper;

import com.h2.hotelmangement.entity.Customer;
import com.h2.hotelmangement.model.dto.CustomerDTO;

import java.util.ArrayList;
import java.util.List;

public class CustomerMapper {

    public CustomerDTO customerEntityToDto(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId().toString());
        customerDTO.setCustomerCode(customer.getCusCode());
        customerDTO.setEmail(customer.getEmail());
        customerDTO.setUserName(customer.getAccountCus().getUsername());
        customerDTO.setIdCard(customer.getIdCard());
        customerDTO.setName(customer.getName());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setCustomerType(customer.getCustomerType().getTypeName());

        return customerDTO;
    }

    public List<CustomerDTO> listCustomerEnittyToDto(List<Customer> customerList){
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        for (Customer customer:customerList) {
            CustomerDTO customerDTO = new CustomerDTO();
            customerDTO = customerEntityToDto(customer);
            customerDTOList.add(customerDTO);
        }
        return customerDTOList;
    }
}
