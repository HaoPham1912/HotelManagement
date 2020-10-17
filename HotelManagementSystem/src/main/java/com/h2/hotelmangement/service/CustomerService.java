package com.h2.hotelmangement.service;

import com.h2.hotelmangement.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAllCustomer();

    void saveOrUpdate(Customer customer);

    void deleteCustomer(Long id);
}
