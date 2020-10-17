package com.h2.hotelmangement.service.impl;

import com.h2.hotelmangement.entity.CustomerType;
import com.h2.hotelmangement.repository.CustomerTypeRepository;
import com.h2.hotelmangement.service.CustomerTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerTypeServiceImpl implements CustomerTypeService {

    @Autowired
    private CustomerTypeRepository customerTypeRepository;

    @Override
    public List<CustomerType> findAllCustomerType() {
        return customerTypeRepository.findAll();
    }
}
