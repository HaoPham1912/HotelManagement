package com.h2.hotelmangement.service;

import com.h2.hotelmangement.Request.UserInfoUpdateDTO;
import com.h2.hotelmangement.entity.Customer;
import com.h2.hotelmangement.model.dto.CustomerDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CustomerService {

    Page<Customer> findAllCustomer(int pageNo, int pageSize);

    Page<Customer> findAllCustomerByName(String name, int pageNo, int pageSize);

    List<Customer> getCusByCusType(String customerType);

    void save(Customer customer);

    void deleteCustomer(Long id) throws Exception;

    Customer getCustomerById(Long id);

    void updateCustomerInfo(UserInfoUpdateDTO userUnfoUpdate);

    Optional<Customer> findCustomerByEmail(String email);

}
