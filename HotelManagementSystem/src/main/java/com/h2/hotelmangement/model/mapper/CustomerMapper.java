package com.h2.hotelmangement.model.mapper;

import com.h2.hotelmangement.entity.Account;
import com.h2.hotelmangement.entity.Customer;
import com.h2.hotelmangement.entity.CustomerType;
import com.h2.hotelmangement.entity.Role;
import com.h2.hotelmangement.model.dto.CustomerDTO;
import com.h2.hotelmangement.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import  com.h2.hotelmangement.service.CustomerTypeService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomerMapper {

    @Autowired
    CustomerTypeService customerTypeService;

    @Autowired
    RoleService roleService;

    public CustomerDTO customerEntityToDto(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setCustomerId(customer.getCustomerId().toString());
        customerDTO.setCustomerCode(customer.getCusCode());
        customerDTO.setEmail(customer.getEmail());
        if(customer.getAccountCus() != null){
            customerDTO.setUserName(customer.getAccountCus().getUsername());
        }else{
            customerDTO.setUserName("");
        }

        customerDTO.setIdCard(customer.getIdCard());
        customerDTO.setName(customer.getName());
        customerDTO.setPhone(customer.getPhone());
        customerDTO.setStatus(String.valueOf(customer.getStatus()));
        if(customer.getCustomerType() != null){
            customerDTO.setCustomerType(customer.getCustomerType().getTypeName());
        }else{
            customerDTO.setCustomerType("");
        }


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

    public Customer convertCusDtoToEntity(CustomerDTO customerDTO){
        Customer customer = new Customer();
        Account account = new Account();
        Role role = roleService.findByRoleName("CUSTOMER");

        CustomerType customerType = customerTypeService.findCustomerTypeByName(customerDTO.getCustomerType());
        customer.setCustomerId(Long.parseLong(customerDTO.getCustomerId()));
        customer.setCusCode(customerDTO.getCustomerCode());
        customer.setIdCard(customerDTO.getIdCard());
        customer.setName(customerDTO.getName());
        customer.setPhone(customerDTO.getPhone());
        customer.setEmail(customerDTO.getEmail());

        customer.setCustomerType(customerType);

        account.setUsername(customerDTO.getUserName());
        account.setPassword(customerDTO.getPassWord());
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        account.setRoles(roleSet);

        customer.setAccountCus(account);

        return customer;
    }
}
