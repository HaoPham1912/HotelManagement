package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Customer;

import java.util.List;
import java.util.Set;

public interface CustomerService {

    List<Customer> findAllCustomer();

    List<Customer> getCusByCusType(String customerType);

    void save(Customer customer);

    void deleteCustomer(Long id);
}
