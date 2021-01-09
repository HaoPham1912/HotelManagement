package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.Request.UserInfoUpdateDTO;
import com.h2.hotelmangement.common.util.ModelMapperUtil;
import com.h2.hotelmangement.entity.Customer;
import com.h2.hotelmangement.entity.Employee;
import com.h2.hotelmangement.model.dto.CustomerDTO;
import com.h2.hotelmangement.repository.CustomerRepository;
import com.h2.hotelmangement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Page<Customer> findAllCustomer(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Customer> customerPage = customerRepository.findAll(pageable);
        return customerPage;
    }

    @Override
    public Page<Customer> findAllCustomerByName(String name, int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return customerRepository.findAllByNameContains(name,pageable);
    }

    @Override
    public List<Customer> getCusByCusType(String customerType) {
        return customerRepository.findByCustomerType_TypeName(customerType);
    }

    @Override
    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public void deleteCustomer(Long id) throws Exception {
        Customer customer = customerRepository.getOne(id);
        if(customer != null){
            Boolean empStatus = customer.getStatus();
            customer.setStatus(!empStatus);
            customerRepository.save(customer);
        }else{
            throw new Exception("Can not find employee with id "+ id);
        }
    }

    @Override
    public Customer getCustomerById(Long id) {
        return customerRepository.getOne(id);
    }

    @Override
    public void updateCustomerInfo(UserInfoUpdateDTO userUnfoUpdate) {
        Optional<Customer> customer = customerRepository.findByEmail(userUnfoUpdate.getEmail());
        customer.get().setName(userUnfoUpdate.getUserName());
        customer.get().setPhone(userUnfoUpdate.getPhone());
        customerRepository.save(customer.get());
    }

    @Override
    public Optional<Customer> findCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);

    }
}
